package com.alfatecsistemas.sihna.web.model;

import javax.validation.constraints.Size;

public class FiltroEmpleadoDTO {

    @Size(max = 50)
    private String name;
    @Size(max = 50)
    private String lastname;
    private Long departamentoId;

    public FiltroEmpleadoDTO(Long departamentoId, String lastname, String name) {
        this.departamentoId = departamentoId;
        this.lastname = lastname;
        this.name = name;
    }

    public FiltroEmpleadoDTO() {
    }

    public Long getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
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
}