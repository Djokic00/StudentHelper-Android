package rs.raf.projekat2.aleksa_djokic_rn1619.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubjectResponseList (
    val subject: List<SubjectResponseItem>
        )