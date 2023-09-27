package pe.edu.upeu.asistenciaupeujc.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import pe.edu.upeu.asistenciaupeujc.modelo.Asistenciapa

@Dao
interface AsistenciapaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAsistenciapa(asistenciapa: Asistenciapa)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAsistenciapas(asistenciapa: List<Asistenciapa>)

    @Update
    suspend fun modificarAsistenciapa(asistenciapa: Asistenciapa)

    @Query("delete  from asistenciapa where id=:asistenciapa")
    suspend fun eliminarAsistenciapa(asistenciapa: Long)

    @Transaction
    @Query("select m.*, a.nombreActividad from asistenciapa m, actividad a where m.actividadId=a.id")
    fun reportatAsistenciapa(): LiveData<List<Asistenciapa>>

    @Query("select * from asistenciapa where id=:idx")
    fun buscarAsistenciapa(idx: Long): LiveData<Asistenciapa>
}