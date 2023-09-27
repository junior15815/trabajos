package pe.edu.upeu.asistencia.services;

import java.util.Map;
import java.util.List;
import pe.edu.upeu.asistencia.models.Asistenciapa;

/**
 *
 * @author DELL
 */
public interface AsistenciapaService {
    Asistenciapa save(Asistenciapa asistenciapa);

    List<Asistenciapa> findAll();

    Map<String, Boolean> delete(Long id);

    Asistenciapa getActividadById(Long id);

    Asistenciapa update(Asistenciapa asistenciapa, Long id); 
}
