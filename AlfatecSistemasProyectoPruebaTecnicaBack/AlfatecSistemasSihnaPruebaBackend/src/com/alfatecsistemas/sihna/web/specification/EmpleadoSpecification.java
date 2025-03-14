package com.alfatecsistemas.sihna.web.specification;

import com.alfatecsistemas.sihna.bean.Departamento;
import com.alfatecsistemas.sihna.bean.Empleado;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

public class EmpleadoSpecification {

    public static Specification<Empleado> conDepartamento(final Long departamentoId) {
        return new Specification<Empleado>() {
            @Override
            public Predicate toPredicate(Root<Empleado> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (departamentoId == null) {
                    return criteriaBuilder.conjunction();
                }
                return criteriaBuilder.equal(root.get("departamento").get("id"), departamentoId);
            }
        };
    }

    public static Specification<Empleado> conNombre(final String name) {
        return new Specification<Empleado>() {
            @Override
            public Predicate toPredicate(Root<Empleado> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (StringUtils.isEmpty(name)) {
                    return criteriaBuilder.conjunction();
                }
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
            }
        };
    }

    public static Specification<Empleado> conApellido(final String lastname) {
        return new Specification<Empleado>() {
            @Override
            public Predicate toPredicate(Root<Empleado> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (StringUtils.isEmpty(lastname)) {
                    return criteriaBuilder.conjunction();
                }
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("lastname")), "%" + lastname.toLowerCase() + "%");
            }
        };
    }

}