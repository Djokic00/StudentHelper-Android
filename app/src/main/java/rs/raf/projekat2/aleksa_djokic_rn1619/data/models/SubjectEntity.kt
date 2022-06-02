package rs.raf.projekat2.aleksa_djokic_rn1619.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "subjects")
data class SubjectEntity(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val predmet: String,
    val tip: String,
    val nastavnik: String,
    val grupe: String,
    val dan: String,
    val termin: String,
    val ucionica: String
        )