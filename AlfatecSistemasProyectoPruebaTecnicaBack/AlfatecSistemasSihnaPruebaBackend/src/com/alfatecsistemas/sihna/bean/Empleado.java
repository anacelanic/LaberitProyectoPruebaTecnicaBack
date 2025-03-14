package com.alfatecsistemas.sihna.bean;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 50)
    private String name;

    @NotBlank
    @Column(length = 50)
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    public Empleado() {
    }

    public Empleado(String name, Departamento departamento, String lastname, Long id) {
        this.name = name;
        this.departamento = departamento;
        this.lastname = lastname;
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
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
}