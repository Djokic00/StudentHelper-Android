package rs.raf.projekat2.aleksa_djokic_rn1619.data.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.util.*

@JsonClass(generateAdapter = true)
@Parcelize
data class NoteResponse(
    val id: Int,
    val title: String,
    val content: String,
    val archive: Boolean,
    val date_of_creation: Date
) : Parcelable

