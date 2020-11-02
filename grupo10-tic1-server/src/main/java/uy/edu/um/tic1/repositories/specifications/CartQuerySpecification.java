package uy.edu.um.tic1.repositories.specifications;


import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;
import uy.edu.um.tic1.entities.cart.Cart;
import uy.edu.um.tic1.entities.cart.CartItem;
import uy.edu.um.tic1.entities.products.Product;
import uy.edu.um.tic1.entities.users.Client;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Builder
public class CartQuerySpecification implements Specification<Cart> {


    private Integer id;
    private Product product;
    private String size;
    private String color;
    private Boolean toDeliver;
    private Client client;
    private LocalDate from;
    private LocalDate to;



    @Override
    public Predicate toPredicate(Root<Cart> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Join<Cart, CartItem> cartCartItemJoin = root.joinSet("items");

        Join<Cart, Client> cartClientJoin = root.join("client");

        List<Predicate> predicates = new ArrayList<>();


        if (id != null)
            predicates.add(criteriaBuilder.equal(root.get("id"), id));

        if (product != null)
            predicates.add(criteriaBuilder.isMember(product,cartCartItemJoin.get("product")));
        if (size != null)
            predicates.add(criteriaBuilder.isMember(size,cartCartItemJoin.get("sizeAndColor").get("size")));
        if (size != null)
            predicates.add(criteriaBuilder.isMember(size,cartCartItemJoin.get("sizeAndColor").get("size")));

        if (toDeliver != null)
            predicates.add(criteriaBuilder.equal(root.get("toDeliver"), toDeliver));

        if (client != null)
            predicates.add(criteriaBuilder.equal(root.get("client"), client));

        if (from != null)
            predicates.add(criteriaBuilder.greaterThan(root.get("date"), id));

        if (to != null)
            predicates.add(criteriaBuilder.lessThan(root.get("date"), id));


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
