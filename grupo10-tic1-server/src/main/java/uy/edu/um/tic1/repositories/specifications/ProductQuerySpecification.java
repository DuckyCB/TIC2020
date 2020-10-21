package uy.edu.um.tic1.repositories.specifications;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import uy.edu.um.tic1.entities.Brand;
import uy.edu.um.tic1.entities.Product;
import uy.edu.um.tic1.entities.SizeAndColor;
import uy.edu.um.tic1.entities.Stock;
import org.hibernate.criterion.Order;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


@Builder
public class ProductQuerySpecification implements Specification<Product> {



    private Integer id;
    private String name;
    private Character gender;
    private Brand brand;
    private String color;
    private String size;
    private Double priceFrom;
    private Double priceTo;



    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Join<Product, SizeAndColor> productSizeAndColorJoin = root.joinSet("sizeAndColor");

        List<Predicate> predicates = new ArrayList<>();


        if (id != null)
            predicates.add(criteriaBuilder.equal(root.get("id"), id));
        if (name != null)
            predicates.add(criteriaBuilder.like(root.get("name"), "%"+name+"%"));
        if (gender != null)
            predicates.add(criteriaBuilder.equal(root.get("gender"), gender));
        if (brand != null)
            predicates.add(criteriaBuilder.equal(root.get("brand"), brand));
        if (priceFrom != null)
            predicates.add(criteriaBuilder.greaterThan(root.get("price"), priceFrom));
        if (priceTo != null)
            predicates.add(criteriaBuilder.lessThan(root.get("price"), priceTo));
//        if (color != null)
//            predicates.add(criteriaBuilder.equal(productSizeAndColorJoin.get("color"), color));
//        if (size != null)
//            predicates.add(criteriaBuilder.equal(productSizeAndColorJoin.get("size"), size));


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }




}