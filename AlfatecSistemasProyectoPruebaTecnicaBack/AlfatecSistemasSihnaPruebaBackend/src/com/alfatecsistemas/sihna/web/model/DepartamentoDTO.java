package com.alfatecsistemas.sihna.web.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DepartamentoDTO {

    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    public DepartamentoDTO(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public DepartamentoDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}