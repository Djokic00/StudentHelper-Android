package rs.raf.projekat2.aleksa_djokic_rn1619.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Note (
    val id: Int,
    val title: String,
    val content: String,
    val archive: Boolean,
    val date: Date = Date()
        ): Parcelable