package rs.raf.projekat2.aleksa_djokic_rn1619.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteEntity
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.SubjectEntity
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.SubjectGroupAndDay

@Dao
abstract class StudentDao {

    // SUBJECTS

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: SubjectEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<SubjectEntity>): Completable

    @Query("SELECT * FROM subjects")
    abstract fun getAllSubjects(): Observable<List<SubjectEntity>>

    @Query("SELECT grupe, dan FROM subjects")
    abstract fun getAllGroups(): Observable<List<SubjectGroupAndDay>>

    @Query("DELETE FROM subjects")
    abstract fun deleteAll(): Completable

    @Query("SELECT * FROM subjects WHERE grupe LIKE  '% ' || :group || '%' ")
    abstract fun filterByGroup(group: String): Observable<List<SubjectEntity>>

    @Query("SELECT * FROM subjects WHERE dan = :day")
    abstract fun filterByDay(day: String): Observable<List<SubjectEntity>>

    @Query("SELECT * FROM subjects WHERE grupe LIKE  '% ' || :group || '%'  AND dan = :day")
    abstract fun filterByGroupAndDay(group: String, day: String): Observable<List<SubjectEntity>>

//    @Query("SELECT predmet, nastavnik FROM subjects WHERE predmet LIKE  '%' || :text || '%' OR nastavnik LIKE '%' || :text || '%')
//    abstract fun filterByText(text: String): Observable<List<SubjectEntity>>

    @Transaction
    open fun deleteAndInsertAll(entities: List<SubjectEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    // NOTES

    @Query("SELECT * FROM notes")
    abstract fun getAllNotes(): Observable<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun saveNote(note: NoteEntity): Completable

    @Query("UPDATE notes SET title=:new_title AND content=:new_content WHERE id = :id")
    abstract fun editNote(id: Int, new_title: String, new_content: String)

    @Query("DELETE FROM notes WHERE id = :id")
    abstract fun deleteNote(id: Int): Completable

}