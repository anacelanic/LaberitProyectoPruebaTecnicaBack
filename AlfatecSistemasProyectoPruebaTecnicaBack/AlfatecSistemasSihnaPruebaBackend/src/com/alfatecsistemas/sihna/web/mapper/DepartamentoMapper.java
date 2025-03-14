package com.alfatecsistemas.sihna.web.mapper;

import com.alfatecsistemas.sihna.bean.Departamento;
import com.alfatecsistemas.sihna.web.model.DepartamentoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DepartamentoMapper {

    DepartamentoDTO mapToDepartamentoDTO(Departamento departamento);

    Departamento mapToDepartamento(DepartamentoDTO departamentoDTO);
}