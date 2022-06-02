package rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories

import io.reactivex.Observable
import rs.raf.projekat2.aleksa_djokic_rn1619.data.datasource.local.StudentDao
import rs.raf.projekat2.aleksa_djokic_rn1619.data.datasource.remote.StudentService
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Resource
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Subject
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.SubjectEntity
import timber.log.Timber

class StudentRepositoryImpl (
    private val localDataSource: StudentDao,
    private val remoteDataSource: StudentService
) : StudentRepository {

    override fun fetchAllSubjects(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAllSubjects()
            .doOnNext {
                Timber.e("Upis u bazu")
//                val subjectResponse = it.subject.
//                val subjectEntity = SubjectEntity(
//                    id = subjectResponse.
//                )
                val entities: List<SubjectEntity> = it.map {
                    SubjectEntity(
                        id = 0,
                        predmet = it.predmet,
                        tip = it.tip,
                        nastavnik = it.nastavnik,
                        grupe = it.grupe,
                        dan = it.dan,
                        termin = it.termin,
                        ucionica = it.ucionica
                    )
                }

                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAllSubjects(): Observable<List<Subject>> {
        return localDataSource
            .getAllSubjects()
            .map {
                it.map {
                    Subject(
                        id = 0,
                        predmet =  it.predmet,
                        tip = it.tip,
                        nastavnik = it.nastavnik,
                        grupe = it.grupe,
                        dan = it.dan,
                        termin = it.termin,
                        ucionica = it.ucionica
                    )
                }
            }
    }

    override fun getAllNotes(): Observable<List<Note>> {
        TODO("Not yet implemented")
    }

}
