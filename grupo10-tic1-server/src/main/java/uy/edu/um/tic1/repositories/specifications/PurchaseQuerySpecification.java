package uy.edu.um.tic1.repositories.specifications;

import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entities.cart.CartItem;
import uy.edu.um.tic1.entities.cart.Purchase;
import uy.edu.um.tic1.entities.users.Client;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public class PurchaseQuerySpecification implements Specification<Purchase> {

    private Integer id;
    private Boolean delivered;
    private LocalTime deliveryTime;
    private LocalDate deliveryDate;
    private Client client;
    private CartItem cartItem;

    @Override
    public Predicate toPredicate(Root<Purchase> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();



        if (id != null)
            predicates.add(criteriaBuilder.equal(root.get("id"), id));
        if (delivered != null)
            predicates.add(criteriaBuilder.equal(root.get("delivered"), delivered));
        if (client != null)
            predicates.add(criteriaBuilder.equal(root.get("client"), client));



        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

