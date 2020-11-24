package uy.edu.um.tic1.repositories.specifications;

import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entities.cart.CartItem;
import uy.edu.um.tic1.entities.cart.Purchase;
import uy.edu.um.tic1.entities.users.Client;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Builder
public class PurchaseQuerySpecification implements Specification<Purchase> {

    private Integer id;
    private Boolean delivered;
    private LocalTime deliveryTime;
    private LocalDate deliveryDate;
    private LocalTime purchaseTime;
    private LocalDate purchaseDate;
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


        List<Order> order = new LinkedList<>();

        order.add(criteriaBuilder.desc(root.get("deliveryDate")));
        order.add(criteriaBuilder.desc(root.get("deliveryTime")));
        order.add(criteriaBuilder.desc(root.get("purchaseDate")));
        order.add(criteriaBuilder.desc(root.get("purchaseTime")));

        query.orderBy(order);





        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

