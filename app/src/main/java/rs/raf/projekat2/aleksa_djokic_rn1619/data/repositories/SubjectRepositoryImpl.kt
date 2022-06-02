package rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories

import androidx.compose.runtime.sourceInformationMarkerEnd
import io.reactivex.Observable
import rs.raf.projekat2.aleksa_djokic_rn1619.data.datasource.local.StudentDao
import rs.raf.projekat2.aleksa_djokic_rn1619.data.datasource.remote.StudentService
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.*
import timber.log.Timber
import java.util.regex.Pattern

class SubjectRepositoryImpl (
    private val localDataSource: StudentDao,
    private val remoteDataSource: StudentService
) : SubjectRepository {

    override fun fetchAll(): Observable<Resource<Unit>> {
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

    override fun getAll(): Observable<List<Subject>> {
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

    override fun getGroupAndDay(): Observable<List<SubjectGroupAndDay>> {
        return localDataSource
            .getAllGroups()
            .map{ it ->
                it.map{
                    SubjectGroupAndDay(
                        grupe = it.grupe,
                        dan = it.dan
                    )
                }
            }
    }

}
