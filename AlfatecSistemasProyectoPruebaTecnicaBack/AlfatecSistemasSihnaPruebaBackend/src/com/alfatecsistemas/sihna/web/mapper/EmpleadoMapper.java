package com.alfatecsistemas.sihna.web.mapper;

import com.alfatecsistemas.sihna.bean.Empleado;
import com.alfatecsistemas.sihna.web.model.EmpleadoDTO;
import com.alfatecsistemas.sihna.web.model.EmpleadoRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

    @Mapping(source = "departamento", target = "departamentoDTO")
    EmpleadoDTO mapToEmpleadoDTO(Empleado empleado);

    @Mapping(source = "departamentoId", target = "departamento.id")
    Empleado mapToEmpleado(EmpleadoRequestDTO empleadoRequestDTO);

    @Mapping(source = "departamentoId", target = "departamento.id")
    void mapToEmpleado(EmpleadoRequestDTO empleadoRequestDTO, @MappingTarget Empleado empleado);

    List<EmpleadoDTO> mapToEmpleadosDTO(List<Empleado> empleados);
}