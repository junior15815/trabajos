package pe.edu.upeu.asistenciaupeujc.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asistenciapa")
data class Asistenciapa(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var actividadId: String,
    var fecha: String,
    var horaReg: String,
    var latituda: String,
    var longituda: String,
    var tipo: String,
    var calificacion: String,
    var cui: String,
    var tipoCui: String,
    var entsal: String,
    var subactasisId: Long,
    var offlinex: String

)
data class AsistenciapaConActividad(
    var id: Long,
    var actividadId: String,
    var fecha: String,
    var horaReg: String,
    var latituda: String,
    var longituda: String,
    var tipo: String,
    var calificacion: String,
    var cui: String,
    var tipoCui: String,
    var entsal: String,
    var subactasisId: Long,
    var offlinex: String,
    var nombreActividad: String
)

data class AsistenciapaReport(
    var id: Long,
    var actividadId: String,
    var fecha: String,
    var horaReg: String,
    var latituda: String,
    var longituda: String,
    var tipo: String,
    var calificacion: String,
    var cui: String,
    var tipoCui: String,
    var entsal: String,
    var subactasisId: Long,
    var offlinex: String
)

