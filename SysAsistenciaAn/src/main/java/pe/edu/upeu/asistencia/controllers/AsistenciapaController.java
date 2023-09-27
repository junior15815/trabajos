package pe.edu.upeu.asistencia.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upeu.asistencia.dtos.AsistenciapaDto;
import pe.edu.upeu.asistencia.models.Asistenciapa;
import pe.edu.upeu.asistencia.services.AsistenciapaService;





public class AsistenciapaController {
    @Autowired
    private AsistenciapaService asistenciapaService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Asistenciapa>> listAsistenciapa() {
        List<Asistenciapa> actDto = asistenciapaService.findAll();

        // Gson gson = new Gson();
        // String jsonCartList = gson.toJson(actDto);
        // System.out.println("Ver Aqui: "+jsonCartList);
        return ResponseEntity.ok(actDto);
        // return new ResponseEntity<>(actDto, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Asistenciapa> createAsistenciapa(@RequestBody AsistenciapaDto.AsistenciapaCrearDto asistenciapa) {

        
        Asistenciapa data = asistenciapaService.save(asistenciapa);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Asistenciapa> getAsistenciapaById(@PathVariable Long id) {
        Asistenciapa asistenciapa = asistenciapaService.getAsistenciapaById(id);
        return ResponseEntity.ok(asistenciapa);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAsistenciapa(@PathVariable Long id) {
        Asistenciapa asistenciapa = asistenciapaService.getAsistenciapaById(id);
        return ResponseEntity.ok(asistenciapaService.delete(asistenciapa.getId()));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Asistenciapa> updateAsistenciapa(@PathVariable Long id,
            @RequestBody AsistenciapaDto.AsistenciapaCrearDto asistenciapaDetails) {
        Asistenciapa updatedAsistenciapa = asistenciapaService.update(asistenciapaDetails, id);
        return ResponseEntity.ok(updatedAsistenciapa);
    }
}
