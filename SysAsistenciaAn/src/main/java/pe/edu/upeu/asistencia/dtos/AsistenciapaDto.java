package pe.edu.upeu.asistencia.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.asistencia.models.Actividad;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AsistenciapaDto {
    Long id;
    @JsonIgnoreProperties({"asistenciaxs", "inscritos", "subactasisxs", "asistenciapas"})
    Actividad actividadId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate fecha;
    @JsonFormat(pattern = "HH:mm:ss")
    LocalTime horaReg;
    String latituda;
    String longituda;
    String tipo;
    String calificacion;
    String cui;
    String tipoCui;
    String entsal;
    Long subactasisId;
    String offlinex;
    
    public record AsistenciapaCrearDto(Long id, String actividadId,LocalDate fecha, LocalTime horaReg, String latituda,
        String longituda, String tipo, String calificacion, String cui, String tipoCui, String entsal, Long subactasisId, String offlinex) {
    }
}
