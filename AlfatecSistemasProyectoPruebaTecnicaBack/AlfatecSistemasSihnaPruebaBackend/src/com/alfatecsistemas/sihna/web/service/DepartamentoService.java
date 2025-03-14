package com.alfatecsistemas.sihna.web.service;

import com.alfatecsistemas.sihna.web.model.DepartamentoDTO;
import com.alfatecsistemas.sihna.web.model.EmpleadoDTO;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartamentoService {

    DepartamentoDTO crearDepartamento(DepartamentoDTO departamentoDTO);

    DepartamentoDTO getDepartamentoById(Long id) throws NotFoundException;

    List<EmpleadoDTO> listarEmpleado(Long id, Long idEmpleado) throws NotFoundException;

}
