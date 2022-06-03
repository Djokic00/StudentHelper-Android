package rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.aleksa_djokic_rn1619.data.datasource.local.StudentDao
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteEntity
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Subject

class NoteRepositoryImpl(
    private val localDataSource: StudentDao,
) : NoteRepository {

    override fun getAll(): Observable<List<Note>> {
        return localDataSource
            .getAllNotes()
            .map {
                it.map {
                    Note(
                        id = 0,
                        title = it.title,
                        content = it.content
                    )
                }
            }
    }

    override fun edit(id: Int): Observable<Note> {
        TODO("Not yet implemented")
    }

    override fun save(note: NoteEntity): Completable {
        return localDataSource.saveNote(note)
    }

    override fun delete(id: Int): Completable {
        return localDataSource.deleteNote(id)
    }


}