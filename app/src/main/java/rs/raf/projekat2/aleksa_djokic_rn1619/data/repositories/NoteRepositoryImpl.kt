package rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.aleksa_djokic_rn1619.data.datasource.local.StudentDao
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteEntity

class NoteRepositoryImpl(
    private val localDataSource: StudentDao,
) : NoteRepository {


    override fun getAll(): Observable<List<Note>> {
        TODO("Not yet implemented")
    }

    override fun edit(id: String): Observable<Note> {
        TODO("Not yet implemented")
    }

    override fun save(note: NoteEntity): Completable {
        TODO("Not yet implemented")
    }
}