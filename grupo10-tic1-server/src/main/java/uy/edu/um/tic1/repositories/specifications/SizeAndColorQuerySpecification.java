package uy.edu.um.tic1.repositories.specifications;

import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;
import uy.edu.um.tic1.entities.SizeAndColor;
import uy.edu.um.tic1.entities.Stock;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Builder
public class SizeAndColorQuerySpecification implements Specification<SizeAndColor> {


    private String size;
    private String color;


    @Override
    public Predicate toPredicate(Root<SizeAndColor> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if (size != null)
            predicates.add(criteriaBuilder.equal(root.get("size"), size));

        if (color != null)
            predicates.add(criteriaBuilder.equal(root.get("color"), color));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
