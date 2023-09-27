package pe.edu.upeu.asistenciaupeujc.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import pe.edu.upeu.asistenciaupeujc.data.local.dao.AsistenciapaDao
import pe.edu.upeu.asistenciaupeujc.data.remote.RestAsistenciapa
import pe.edu.upeu.asistenciaupeujc.modelo.Asistenciapa
import pe.edu.upeu.asistenciaupeujc.modelo.AsistenciapaConActividad
import pe.edu.upeu.asistenciaupeujc.modelo.Materialesx
import pe.edu.upeu.asistenciaupeujc.utils.TokenUtils
import javax.inject.Inject

interface AsistenciapaRepository {
    suspend fun deleteAsistenciapa(asistenciapa: AsistenciapaConActividad)
    fun reportarAsistenciapas(): LiveData<List<Asistenciapa>>

    fun buscarAsistenciapaId(id:Long): LiveData<Asistenciapa>

    suspend fun insertarAsistenciapa(asistenciapa: Asistenciapa):Boolean

    suspend fun modificarRemoteAsistenciapa(asistenciapa: Asistenciapa):Boolean
}

class AsistenciapaRepositoryImp @Inject constructor(
    private val restAsistenciapa: RestAsistenciapa,
    private val asistenciapaDao: AsistenciapaDao
): AsistenciapaRepository {
    override suspend fun deleteAsistenciapa(asistenciapa: AsistenciapaConActividad){
        CoroutineScope(Dispatchers.IO).launch {
            restAsistenciapa.deleteAsistenciapa(TokenUtils.TOKEN_CONTENT,asistenciapa.id)
        }
        asistenciapaDao.eliminarAsistenciapa(asistenciapa.id)
    }


    override fun reportarAsistenciapas(): LiveData<List<AsistenciapaConActividad>> {
        try {
            CoroutineScope(Dispatchers.IO).launch{
                delay(3000)
                val data=restAsistenciapa.reportarAsistenciapa(TokenUtils.TOKEN_CONTENT).body()!!
                val dataxx = data.map {
                    Materialesx(it.id,it.actividadId, it.fecha,it.horaReg, it.latituda,it.longituda, it.tipo, it.calificacion, it.cui, it.tipoCui, it.entsal, it.subactasisId.id, it.offlinex)
                }
                asistenciapaDao.insertarAsistenciapas(data)
            }
        }catch (e:Exception){
            Log.i("ERROR", "Error: ${e.message}")
        }
        return asistenciapaDao.reportatAsistenciapa()
    }

    override fun buscarAsistenciapaId(id:Long): LiveData<Asistenciapa> {
        return  asistenciapaDao.buscarAsistenciapa(id)
    }


    override suspend fun insertarAsistenciapa(asistenciapa: Asistenciapa):Boolean{
        return restAsistenciapa.insertarAsistenciapa(TokenUtils.TOKEN_CONTENT, asistenciapa).body()!=null
    }

    override suspend fun modificarRemoteAsistenciapa(asistenciapa: Asistenciapa):Boolean{
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("VER", TokenUtils.TOKEN_CONTENT)
        }
        return restAsistenciapa.actualizarAsistenciapa(TokenUtils.TOKEN_CONTENT, asistenciapa.id, asistenciapa).body()!=null
    }

}