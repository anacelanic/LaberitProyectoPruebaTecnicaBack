package com.alfatecsistemas.sihna.web.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmpleadoRequestDTO {

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String lastname;

    private Long departamentoId;

    public EmpleadoRequestDTO() {
    }

    public EmpleadoRequestDTO(Long departamentoId, String lastname, String name) {
        this.departamentoId = departamentoId;
        this.lastname = lastname;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }
}
