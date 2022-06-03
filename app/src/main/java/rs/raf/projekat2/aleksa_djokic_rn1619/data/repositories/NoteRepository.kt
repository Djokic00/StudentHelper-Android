package rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteEntity

interface NoteRepository {
    fun getAll(): Observable<List<Note>>
    fun edit(id : Int): Observable<Note>
    fun save(note: NoteEntity): Completable
    fun delete(id: Int): Completable
}