package com.alfatecsistemas.sihna.web.service;

import com.alfatecsistemas.sihna.web.model.EmpleadoDTO;
import com.alfatecsistemas.sihna.web.model.EmpleadoRequestDTO;
import com.alfatecsistemas.sihna.web.model.FiltroEmpleadoDTO;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmpleadoService {

    EmpleadoDTO crearEmpleado(EmpleadoRequestDTO empleadoRequestDTO) throws NotFoundException;

    EmpleadoDTO getEmpleadoById(Long id) throws NotFoundException;

    void borrarEmpleado(Long id) throws NotFoundException;

    EmpleadoDTO modificarEmpleado(Long id, EmpleadoRequestDTO empleadoRequestDTO) throws NotFoundException;

    List<EmpleadoDTO> listarEmpleados(FiltroEmpleadoDTO filtroEmpleadoDTO);

}
