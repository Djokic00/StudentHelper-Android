package rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories

import io.reactivex.Observable
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Resource
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Subject

interface StudentRepository {

    fun fetchAllSubjects(): Observable<Resource<Unit>>
    fun getAllSubjects(): Observable<List<Subject>>
    fun getAllNotes(): Observable<List<Note>>
}