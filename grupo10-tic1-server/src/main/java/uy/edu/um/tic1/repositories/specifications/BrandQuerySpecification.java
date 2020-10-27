package uy.edu.um.tic1.repositories.specifications;

import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;
import uy.edu.um.tic1.entities.Brand;
import uy.edu.um.tic1.entities.contact.Email;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Builder
public class BrandQuerySpecification implements Specification<Brand> {


    private Integer id;
    private String name;
    private Email email;


    @Override
    public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if (id != null)
            predicates.add(criteriaBuilder.equal(root.get("id"), id));

        if (name != null)
            predicates.add(criteriaBuilder.like(root.get("name"), "%"+id+"%"));

        if (email != null)
            predicates.add(criteriaBuilder.equal(root.get("email"), email));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
