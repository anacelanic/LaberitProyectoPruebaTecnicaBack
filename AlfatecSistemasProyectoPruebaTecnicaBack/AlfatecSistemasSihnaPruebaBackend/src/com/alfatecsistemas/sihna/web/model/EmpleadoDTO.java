package com.alfatecsistemas.sihna.web.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmpleadoDTO {

    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String lastname;

    private DepartamentoDTO departamentoDTO;

    public EmpleadoDTO(String lastname, String name, Long id, DepartamentoDTO departamentoDTO) {
        this.lastname = lastname;
        this.name = name;
        this.id = id;
        this.departamentoDTO = departamentoDTO;
    }

    public EmpleadoDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartamentoDTO getDepartamentoDTO() {
        return departamentoDTO;
    }

    public void setDepartamentoDTO(DepartamentoDTO departamentoDTO) {
        this.departamentoDTO = departamentoDTO;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}