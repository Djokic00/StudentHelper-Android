package rs.raf.projekat2.aleksa_djokic_rn1619.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val title: String,
    val content: String,
    val archive: Boolean,
    @ColumnInfo (name = "date_of_creation") var date: Date = Date()
)