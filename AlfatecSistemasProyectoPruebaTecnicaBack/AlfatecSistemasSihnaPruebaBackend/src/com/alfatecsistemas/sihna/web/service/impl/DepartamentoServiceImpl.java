package com.alfatecsistemas.sihna.web.service.impl;

import com.alfatecsistemas.sihna.bean.Departamento;
import com.alfatecsistemas.sihna.bean.Empleado;
import com.alfatecsistemas.sihna.web.mapper.DepartamentoMapper;
import com.alfatecsistemas.sihna.web.mapper.EmpleadoMapper;
import com.alfatecsistemas.sihna.web.model.DepartamentoDTO;
import com.alfatecsistemas.sihna.web.model.EmpleadoDTO;
import com.alfatecsistemas.sihna.web.repository.DepartamentoRepository;
import com.alfatecsistemas.sihna.web.service.DepartamentoService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final DepartamentoMapper departamentoMapper;
    private final EmpleadoMapper empleadoMapper;

    @PersistenceContext
    private final EntityManager entityManager;

    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository, DepartamentoMapper departamentoMapper, EntityManager entityManager, EmpleadoMapper empleadoMapper){
        this.departamentoRepository = departamentoRepository;
        this.departamentoMapper = departamentoMapper;
        this.entityManager = entityManager;
        this.empleadoMapper = empleadoMapper;
    }

    @Override
    public DepartamentoDTO crearDepartamento(DepartamentoDTO departamentoDTO) {

        Optional<Departamento> departamento = departamentoRepository.findByName(departamentoDTO.getName());

        if (departamento.isPresent()){
            throw new RuntimeException("El nombre del departamento ya existe: " + departamentoDTO.getName());
        }

        Departamento dpto = departamentoMapper.mapToDepartamento(departamentoDTO);
        Departamento dptoGuardado = departamentoRepository.save(dpto);

        return departamentoMapper.mapToDepartamentoDTO(dptoGuardado);
    }

    @Override
    public DepartamentoDTO getDepartamentoById(Long id) throws NotFoundException {
        Departamento departamento = departamentoRepository.findOne(id);
        if(departamento == null) {
            throw new NotFoundException("Departamento inexistente");
        }

        return departamentoMapper.mapToDepartamentoDTO(departamento);
    }
    @Override
    public List<EmpleadoDTO> listarEmpleado(Long id, Long idEmpleado) throws NotFoundException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Empleado> query = criteriaBuilder.createQuery(Empleado.class);

        Root<Empleado> root = query.from(Empleado.class);

        Predicate predicate = criteriaBuilder.conjunction();

        predicate = criteriaBuilder.and(predicate,
                criteriaBuilder.equal(root.get("departamento").get("id"), id),
                criteriaBuilder.equal(root.get("id"), idEmpleado)
        );

        query.where(predicate);
        List<Empleado> empleados = entityManager.createQuery(query).getResultList();

        if (empleados.isEmpty()) {
            throw new NotFoundException("Empleado no encontrado en el departamento especificado");
        }

        return empleadoMapper.mapToEmpleadosDTO(empleados);
    }
}