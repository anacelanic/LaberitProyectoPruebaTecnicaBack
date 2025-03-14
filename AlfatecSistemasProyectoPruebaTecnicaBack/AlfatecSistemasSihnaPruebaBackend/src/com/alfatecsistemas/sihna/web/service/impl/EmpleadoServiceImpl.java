package com.alfatecsistemas.sihna.web.service.impl;

import com.alfatecsistemas.sihna.bean.Departamento;
import com.alfatecsistemas.sihna.web.mapper.EmpleadoMapper;
import com.alfatecsistemas.sihna.web.model.EmpleadoRequestDTO;
import com.alfatecsistemas.sihna.web.model.FiltroEmpleadoDTO;
import com.alfatecsistemas.sihna.web.repository.DepartamentoRepository;
import com.alfatecsistemas.sihna.bean.Empleado;
import com.alfatecsistemas.sihna.web.model.EmpleadoDTO;
import com.alfatecsistemas.sihna.web.repository.EmpleadoRepository;
import com.alfatecsistemas.sihna.web.service.EmpleadoService;
import com.alfatecsistemas.sihna.web.specification.EmpleadoSpecification;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final DepartamentoRepository departamentoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    @PersistenceContext
    private final EntityManager entityManager;

    public EmpleadoServiceImpl(DepartamentoRepository departamentoRepository, EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper, EntityManager entityManager){
        this.empleadoRepository = empleadoRepository;
        this.departamentoRepository = departamentoRepository;
        this.empleadoMapper = empleadoMapper;
        this.entityManager = entityManager;
    }

    @Override
    public EmpleadoDTO crearEmpleado(EmpleadoRequestDTO empleadoRequestDTO) throws NotFoundException {

        Empleado empleado = empleadoMapper.mapToEmpleado(empleadoRequestDTO);

        if(empleado.getDepartamento() != null && empleado.getDepartamento().getId() != null) {
            Departamento departamento = departamentoRepository.findOne(empleado.getDepartamento().getId());

            if(departamento == null) {
                throw new NotFoundException("Departamento inexistente");
            }

            empleado.setDepartamento(departamento);
        } else {
            empleado.setDepartamento(null);
        }

        Empleado empleadoGuardado = empleadoRepository.save(empleado);

        return empleadoMapper.mapToEmpleadoDTO(empleadoGuardado);
    }

    @Override
    public EmpleadoDTO getEmpleadoById(Long id) throws NotFoundException {
        Empleado empleado = empleadoRepository.findOne(id);
        if(empleado == null) {
            throw new NotFoundException("Empleado inexistente");
        }

        return empleadoMapper.mapToEmpleadoDTO(empleado);
    }

    @Override
    public void borrarEmpleado(Long id) throws NotFoundException {
        Empleado empleado = empleadoRepository.findOne(id);

        if(empleado == null) {
            throw new NotFoundException("Empleado inexistente");
        }

        empleadoRepository.delete(empleado.getId());
    }

    @Override
    public EmpleadoDTO modificarEmpleado(Long id, EmpleadoRequestDTO empleadoRequestDTO) throws NotFoundException {
        Empleado empleado = empleadoRepository.findOne(id);

        if(empleado == null) {
            throw new NotFoundException("Empleado inexistente");
        }

        empleadoMapper.mapToEmpleado(empleadoRequestDTO, empleado);

        if(empleado.getDepartamento() != null && empleado.getDepartamento().getId() != null) {
            Departamento departamento = departamentoRepository.findOne(empleado.getDepartamento().getId());
            if(departamento == null) {
                throw new NotFoundException("Departamento inexistente");
            }
        } else {
            empleado.setDepartamento(null);
        }

        Empleado empleadoModificado = empleadoRepository.save(empleado);

        return empleadoMapper.mapToEmpleadoDTO(empleadoModificado);
    }

    @Override
    public List<EmpleadoDTO> listarEmpleados(FiltroEmpleadoDTO filtroEmpleadoDTO) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Empleado> query = criteriaBuilder.createQuery(Empleado.class);
        Root<Empleado> root = query.from(Empleado.class);

        Predicate predicate = criteriaBuilder.conjunction();

        if (filtroEmpleadoDTO.getDepartamentoId() != null) {
            predicate = criteriaBuilder.and(predicate, EmpleadoSpecification.conDepartamento(filtroEmpleadoDTO.getDepartamentoId()).toPredicate(root, query, criteriaBuilder));
        }

        if (filtroEmpleadoDTO.getName() != null && !filtroEmpleadoDTO.getName().isEmpty()) {
            predicate = criteriaBuilder.and(predicate, EmpleadoSpecification.conNombre(filtroEmpleadoDTO.getName()).toPredicate(root, query, criteriaBuilder));
        }

        if (filtroEmpleadoDTO.getLastname() != null && !filtroEmpleadoDTO.getLastname().isEmpty()) {
            predicate = criteriaBuilder.and(predicate, EmpleadoSpecification.conApellido(filtroEmpleadoDTO.getLastname()).toPredicate(root, query, criteriaBuilder));
        }

        query.where(predicate);

        List<Empleado> empleados = entityManager.createQuery(query).getResultList();

        return empleadoMapper.mapToEmpleadosDTO(empleados);
    }

}