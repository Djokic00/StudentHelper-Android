package rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories

import io.reactivex.Observable
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Resource
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Subject
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.SubjectGroupAndDay

interface SubjectRepository {

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Subject>>
    fun getGroupAndDay(): Observable<List<SubjectGroupAndDay>>
    fun filterByGroup(group: String): Observable<List<Subject>>
    fun filterByDay(day: String): Observable<List<Subject>>
    fun filterByGroupAndDay(group: String, day: String): Observable<List<Subject>>
}