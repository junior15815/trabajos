package pe.edu.upeu.asistencia.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upeu.asistencia.exceptions.AppException;
import pe.edu.upeu.asistencia.mappers.AsistenciapaMapper;
import pe.edu.upeu.asistencia.models.Asistenciapa;
import pe.edu.upeu.asistencia.repositories.AsistenciapaRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class AsistenciapaServiceImp implements AsistenciapaService {

    @Autowired
    private AsistenciapaRepository asistenciapaRepo;

    @Autowired
    private ActividadService asistenciapaService;

    private final AsistenciapaMapper AsistenciapaMapper;

    @Override
    public Asistenciapa save(AsistenciapaDto.AsistenciapaCrearDto Asistenciapa) {

        Asistenciapa matEnt=AsistenciapaMapper.asistenciapaCrearDtoToAsistenciapa(asistenciapa);
        matEnt.setActividadId(actividadService.getActividadById(Asistenciapa.actividadId()));
        System.out.println(Asistenciapa.fecha());
        System.out.println(Asistenciapa.horaReg());
        try {
            return asistenciapaRepo.save(matEnt);
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Asistenciapa> findAll() {
        try {
            return asistenciapaRepo.findAll();
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Asistenciapa asistenciapax = asistenciapaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistenciapa not exist with id :" + id));

        asistenciapaRepo.delete(asistenciapax);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);

        return response;
    }

    @Override
    public Asistenciapa getAsistenciapaById(Long id) {
        Asistenciapa findAsistenciapa = asistenciapaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistenciapa not exist with id :" + id));
        return findAsistenciapa;
    }

    @Override
    public Asistenciapa update(AsistenciapaDto.AsistenciapaCrearDto asistenciapa, Long id) {
        Asistenciapa asistenciapax = asistenciapaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Periodo not exist with id :" + id));
            System.out.println("IMPRIME:"+asistenciapa.modFh());
        asistenciapax.setFecha(asistenciapa.fecha());
        asistenciapax.setHoraReg(asistenciapa.horaReg());
        asistenciapax.setOfflinex(asistenciapa.offlinex());
        return asistenciapaRepo.save(asistenciapax);
    }

}
