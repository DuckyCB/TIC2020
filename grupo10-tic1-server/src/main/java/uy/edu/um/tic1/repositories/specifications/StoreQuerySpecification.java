package uy.edu.um.tic1.repositories.specifications;


import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;
import uy.edu.um.tic1.entities.*;
import uy.edu.um.tic1.entities.contact.TelephoneNumber;
import uy.edu.um.tic1.entities.products.Product;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Builder
public class StoreQuerySpecification implements Specification<Store> {

    private Integer id;
    private String address;
    private TelephoneNumber telephoneNumber;
    private Brand brand;
    private Product product;


    @Override
    public Predicate toPredicate(Root<Store> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {





        List<Predicate> predicates = new ArrayList<>();

        if (id != null)
            predicates.add(criteriaBuilder.equal(root.get("id"), id));

        if (address != null)
            predicates.add(criteriaBuilder.like(root.get("address"), "%"+address+"%"));

        if (telephoneNumber != null)
            predicates.add(criteriaBuilder.equal(root.get("telephoneNumber"), telephoneNumber));

        if (brand != null)
            predicates.add(criteriaBuilder.isMember(brand, root.get("brandSet")));

        if (product != null) {
            Join<Store, Stock> storeStockJoin = root.joinSet("stockSet");
            predicates.add(criteriaBuilder.equal(storeStockJoin.get("product"), product));
        }


        query.groupBy(root.get("id"));
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
