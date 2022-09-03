package com.alkemy.disney.disney.repository.specification;


import com.alkemy.disney.disney.dto.MovieFilterDTO;
import com.alkemy.disney.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    public Specification<MovieEntity> getByFilters(MovieFilterDTO filterDTO){
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filterDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filterDTO.getName().toLowerCase() + "%")
                );
            }

            if (StringUtils.hasLength(filterDTO.getGenre())) {
                Long genre = Long.valueOf(filterDTO.getGenre());
                predicates.add(
                        criteriaBuilder.equal(root.get("genreId"), genre)
                );
            }

            query.distinct(true);

            String orderByField = "creationDate";

            query.orderBy(
                    filterDTO.isAsc() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


        };
    }

}