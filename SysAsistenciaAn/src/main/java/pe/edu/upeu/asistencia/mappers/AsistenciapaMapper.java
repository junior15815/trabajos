package pe.edu.upeu.asistencia.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import pe.edu.upeu.asistencia.dtos.AsistenciapaDto;
import pe.edu.upeu.asistencia.models.Asistenciapa;
/**
 *
 * @author DELL
 */
@Mapper(componentModel = "spring")
public class AsistenciapaMapper {
    AsistenciapaDto toAsistenciapaDto(Asistenciapa entidad);

    //@Mapping(target = "id", ignore = true)
    @Mapping(target = "actividadId", ignore = true)
    Asistenciapa asistenciapaCrearDtoToAsistenciapa(AsistenciapaDto.AsistenciapaCrearDto entidadCrearDto);    
}
