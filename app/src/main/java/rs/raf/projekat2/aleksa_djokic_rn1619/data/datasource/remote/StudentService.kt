package rs.raf.projekat2.aleksa_djokic_rn1619.data.datasource.remote

import io.reactivex.Observable
import retrofit2.http.GET
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.SubjectResponseItem

interface StudentService {

    @GET("raspored/json.php")
    fun getAllSubjects(): Observable<List<SubjectResponseItem>>

}