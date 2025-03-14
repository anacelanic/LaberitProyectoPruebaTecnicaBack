package com.alfatecsistemas.sihna.bean;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100, unique = true)
    private String name;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Empleado> empleados;

    public Departamento() {
    }

    public Departamento(Long id, String name, List<Empleado> empleados) {
        this.id = id;
        this.name = name;
        this.empleados = empleados;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpelados(List<Empleado> empelados) {
        this.empleados = empelados;
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