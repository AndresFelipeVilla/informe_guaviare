package com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.springdata;

import com.informeguaviare.mi_informe_guaviare.domain.model.filter.ReportFilter;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.entities.ReportEntity;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.entities.UserEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;

@Component
public class ReportSpecification {

    public static Specification<ReportEntity> byFilter(ReportFilter filters) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            UUID authenticatedUserUuid = null;
            if (filters.getAuthenticatedUserId() != null && !filters.getAuthenticatedUserId().isBlank()) {
                try {
                    authenticatedUserUuid = UUID.fromString(filters.getAuthenticatedUserId());
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("ID de usuario autenticado no es un UUID válido");
                }
            }

            if (filters.isEmployee() && authenticatedUserUuid != null) {
                predicates.add(cb.equal(root.get("employee").get("userId"), authenticatedUserUuid));
            } else if (filters.isBoss() && authenticatedUserUuid != null) {
                Join<ReportEntity, UserEntity> employeeJoin = root.join("employee", JoinType.LEFT);
                Join<UserEntity, UserEntity> managerJoin = employeeJoin.join("manager", JoinType.LEFT);

                Predicate reportsFromSubordinates = cb.equal(managerJoin.get("userId"), authenticatedUserUuid);
                Predicate ownReports = cb.equal(root.get("employee").get("userId"), authenticatedUserUuid);

                predicates.add(cb.or(reportsFromSubordinates, ownReports));

                predicates.add(cb.equal(root.get("status"), "ENVIADO"));
            }

            if (filters.hasStatus()) {
                predicates.add(cb.equal(root.get("status"), filters.getStatus()));
            }

            if (filters.hasQuery()) {
                String query = "%" + filters.getQuery().toLowerCase() + "%";
                Predicate titleLike = cb.like(cb.lower(root.get("title")), query);
                Predicate descriptionLike = cb.like(cb.lower(root.get("description")), query);
                predicates.add(cb.or(titleLike, descriptionLike));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}