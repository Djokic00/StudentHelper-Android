package rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.aleksa_djokic_rn1619.data.datasource.local.StudentDao
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteEntity
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteResponse
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Subject

class NoteRepositoryImpl(
    private val localDataSource: StudentDao,
) : NoteRepository {

    override fun getAll(): Observable<List<NoteResponse>> {
        return localDataSource
            .getAllNotes()
            .map {
                it.map {
                    NoteResponse(
                        id = it.id,
                        title = it.title,
                        content = it.content,
                        archive = it.archive,
                        date_of_creation = it.date_of_creation
                    )
                }
            }
    }

    override fun edit(id: Int, newTitle: String, newContent: String): Completable {
        return localDataSource.editNote(id, newTitle, newContent)
    }

    override fun save(note: NoteEntity): Completable {
        return localDataSource.saveNote(note)
    }

    override fun delete(id: Int): Completable {
        return localDataSource.deleteNote(id)
    }

    override fun changeState(id: Int): Completable {
        return localDataSource.changeNoteState(id)
    }

    override fun filter(text: String): Observable<List<NoteResponse>> {
        return localDataSource
            .filterNote(text)
            .map {
                it.map {
                    NoteResponse(it.id, it.title, it.content, it.archive, it.date_of_creation)
                }
            }
    }


}