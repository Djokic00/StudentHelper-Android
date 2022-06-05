package rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteEntity
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteResponse

interface NoteRepository {
    fun getAll(): Observable<List<NoteResponse>>
    fun edit(id : Int, newTitle: String, newContent: String): Completable
    fun save(note: NoteEntity): Completable
    fun delete(id: Int): Completable
    fun changeState(id: Int): Completable
    fun filter(text: String): Observable<List<NoteResponse>>
}