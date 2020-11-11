package uy.edu.um.tic1.repositories.specifications;

import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;
import uy.edu.um.tic1.entities.Brand;
import uy.edu.um.tic1.entities.products.Product;
import uy.edu.um.tic1.entities.SizeAndColor;
import uy.edu.um.tic1.entities.products.Hoodie;
import uy.edu.um.tic1.entities.products.Shirt;
import uy.edu.um.tic1.entities.products.Trousers;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


@Builder
public class ProductQuerySpecification implements Specification<Product> {



    private Integer id;
    private String name;
    private Character gender;
    private Integer brand_id;
    private String color;
    private String size;
    private Double priceFrom;
    private Double priceTo;
    private Integer desiredStock;
    private String clothType;
    private Integer clothSubtype;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {


        Join<Product, SizeAndColor> productSizeAndColorJoin = root.joinSet("sizeAndColor");


        Class<? extends Product> shirtClass = Shirt.class;
        Class<? extends Product> trousersClass = Trousers.class;
        Class<? extends Product> hoodieClass = Hoodie.class;


        List<Predicate> predicates = new ArrayList<>();


        if (id != null)
            predicates.add(criteriaBuilder.equal(root.get("id"), id));
        if (name != null)
            predicates.add(criteriaBuilder.like(root.get("name"), "%"+name+"%"));
        if (gender != null)
            predicates.add(criteriaBuilder.equal(root.get("gender"), gender));
        if (brand_id != null)
            predicates.add(criteriaBuilder.equal(root.get("brand").get("id"), brand_id));
        if (priceFrom != null)
            predicates.add(criteriaBuilder.greaterThan(root.get("price"), priceFrom));
        if (priceTo != null)
            predicates.add(criteriaBuilder.lessThan(root.get("price"), priceTo));

        if (color != null)
            predicates.add(criteriaBuilder.equal(productSizeAndColorJoin.get("color"), color));
        if (size != null)
            predicates.add(criteriaBuilder.equal(productSizeAndColorJoin.get("size"), size));


        if (clothType != null){
            if(clothType.equals("trousers"))
                predicates.add(criteriaBuilder.equal(root.type(), trousersClass));
            else if(clothType.equals("shirt"))
                predicates.add(criteriaBuilder.equal(root.type(), shirtClass));
            else if(clothType.equals("hoodie"))
                predicates.add(criteriaBuilder.equal(root.type(), hoodieClass));

        }

        if (clothSubtype != null)
            predicates.add(criteriaBuilder.equal(root.get("subtype"), clothSubtype));



        query.groupBy(root.get("id"));




        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }




}