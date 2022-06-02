package rs.raf.projekat2.aleksa_djokic_rn1619.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.SubjectEntity
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.SubjectGroupAndDay

@Dao
abstract class StudentDao {

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

    @Transaction
    open fun deleteAndInsertAll(entities: List<SubjectEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }
}