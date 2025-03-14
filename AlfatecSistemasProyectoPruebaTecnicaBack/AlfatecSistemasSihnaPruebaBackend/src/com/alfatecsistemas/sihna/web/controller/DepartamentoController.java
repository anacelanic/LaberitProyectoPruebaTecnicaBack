package com.alfatecsistemas.sihna.web.controller;

import com.alfatecsistemas.sihna.web.model.DepartamentoDTO;
import com.alfatecsistemas.sihna.web.model.EmpleadoDTO;
import com.alfatecsistemas.sihna.web.service.impl.DepartamentoServiceImpl;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/departamento")
public class DepartamentoController {

    private final DepartamentoServiceImpl departamentoService;

    public DepartamentoController(DepartamentoServiceImpl departamentoService){
        this.departamentoService = departamentoService;
    }

    @PostMapping
    public ResponseEntity<DepartamentoDTO> crearDepartamento(@RequestBody DepartamentoDTO departamentoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departamentoService.crearDepartamento(departamentoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartamento(@PathVariable Long id) throws NotFoundException {
        try {
            return ResponseEntity.ok(departamentoService.getDepartamentoById(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}/empleado/{idEmpleado}")
    public ResponseEntity<List<EmpleadoDTO>> listarEmpleado(@PathVariable Long id, @PathVariable Long idEmpleado) throws NotFoundException {
        try {
            return ResponseEntity.ok(departamentoService.listarEmpleado(id, idEmpleado));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

}