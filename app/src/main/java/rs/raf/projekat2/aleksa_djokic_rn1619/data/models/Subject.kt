package rs.raf.projekat2.aleksa_djokic_rn1619.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Subject (
    val id: Int,
    val predmet: String,
    val tip: String,
    val nastavnik: String,
    val grupe: String,
    val dan: String,
    val termin: String,
    val ucionica: String
    )
