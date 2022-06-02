package rs.raf.projekat2.aleksa_djokic_rn1619.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubjectResponseItem(
    val predmet: String,
    val tip: String,
    val nastavnik: String,
    val grupe: String,
    val dan: String,
    val termin: String,
    val ucionica: String
    )