package pe.edu.upeu.asistencia.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upeu.asistencia.models.Asistenciapa;
/**
 *
 * @author EP-Ing_Sist.-CALIDAD
 */
@Repository
public interface AsistenciapaRepository extends JpaRepository<Asistenciapa, Long>{
   
}
