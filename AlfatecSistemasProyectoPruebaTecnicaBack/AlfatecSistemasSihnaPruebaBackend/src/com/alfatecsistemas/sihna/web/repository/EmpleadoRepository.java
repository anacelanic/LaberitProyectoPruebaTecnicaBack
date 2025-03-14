package com.alfatecsistemas.sihna.web.repository;

import com.alfatecsistemas.sihna.bean.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}