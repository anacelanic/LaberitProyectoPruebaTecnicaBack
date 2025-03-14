package com.alfatecsistemas.sihna.web.controller;

import com.alfatecsistemas.sihna.web.model.EmpleadoDTO;
import com.alfatecsistemas.sihna.web.model.EmpleadoRequestDTO;
import com.alfatecsistemas.sihna.web.model.FiltroEmpleadoDTO;
import com.alfatecsistemas.sihna.web.service.impl.EmpleadoServiceImpl;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {


    private final EmpleadoServiceImpl empleadoService;

    public EmpleadoController(EmpleadoServiceImpl empleadoService){
        this.empleadoService = empleadoService;
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO> crearEmpleado(@RequestBody EmpleadoRequestDTO empleadoRequestDTO) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoService.crearEmpleado(empleadoRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpleado(@PathVariable Long id) throws NotFoundException {
        try {
            return ResponseEntity.ok(empleadoService.getEmpleadoById(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEmpleado(@PathVariable Long id) throws NotFoundException {
        try {
            empleadoService.borrarEmpleado(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarEmpleado(@PathVariable Long id, @RequestBody EmpleadoRequestDTO empleadoRequestDTO) throws NotFoundException {
        try {
            return ResponseEntity.ok(empleadoService.modificarEmpleado(id, empleadoRequestDTO));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> listarEmpleados(@RequestBody FiltroEmpleadoDTO filtroEmpleadoDTO) {
        return ResponseEntity.ok(empleadoService.listarEmpleados(filtroEmpleadoDTO));
    }
}