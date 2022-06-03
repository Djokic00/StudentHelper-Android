package rs.raf.projekat2.aleksa_djokic_rn1619.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2.aleksa_djokic_rn1619.data.datasource.local.converters.ListConverter
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteEntity
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.SubjectEntity

@Database(
    entities = [SubjectEntity::class, NoteEntity::class],
    version = 1,
    exportSchema = false
)
//@TypeConverters(ListConverter::class)
abstract class StudentDB : RoomDatabase() {
    abstract fun getStudentDao() : StudentDao
}