package uy.edu.um.tic1.repositories.specifications;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import uy.edu.um.tic1.entities.products.Product;
import uy.edu.um.tic1.entities.SizeAndColor;
import uy.edu.um.tic1.entities.Stock;
import uy.edu.um.tic1.entities.products.Hoodie;
import uy.edu.um.tic1.entities.products.Shirt;
import uy.edu.um.tic1.entities.products.Trousers;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class StockQuerySpecification implements Specification<Stock> {


    private Integer id;
    private Integer product_id;
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
    public Predicate toPredicate(Root<Stock> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Join<Stock, Product> stockProductJoin = root.join("product");
        Join<Stock, SizeAndColor> stockSizeAndColorJoin = root.join("sizeAndColor");

        Class<? extends Product> shirtClass = Shirt.class;
        Class<? extends Product> trousersClass = Trousers.class;
        Class<? extends Product> hoodieClass = Hoodie.class;


        List<Predicate> predicates = new ArrayList<>();



        if (id != null)
            predicates.add(criteriaBuilder.equal(root.get("id"), id));

        if (product_id != null)
            predicates.add(criteriaBuilder.equal(stockProductJoin.get("product_id"), product_id));
        if (name != null)
            predicates.add(criteriaBuilder.like(stockProductJoin.get("name"), "%"+name+"%"));
        if (gender != null)
            predicates.add(criteriaBuilder.equal(stockProductJoin.get("gender"), gender));
        if (brand_id != null)
            predicates.add(criteriaBuilder.equal(stockProductJoin.get("brand").get("id"), brand_id));
        if (priceFrom != null)
            predicates.add(criteriaBuilder.greaterThan(stockProductJoin.get("price"), priceFrom));
        if (priceTo != null)
            predicates.add(criteriaBuilder.lessThan(stockProductJoin.get("price"), priceTo));


        if (color != null)
            predicates.add(criteriaBuilder.equal(stockSizeAndColorJoin.get("color"), color));
        if (size != null)
            predicates.add(criteriaBuilder.equal(stockSizeAndColorJoin.get("size"), size));


        if (clothType != null){
            if(clothType.equals("trousers"))
                predicates.add(criteriaBuilder.equal(stockProductJoin.type(), trousersClass));
            if(clothType.equals("shirt"))
                predicates.add(criteriaBuilder.equal(stockProductJoin.type(), shirtClass));
            if(clothType.equals("hoodie"))
                predicates.add(criteriaBuilder.equal(stockProductJoin.type(), hoodieClass));
        }

        if (clothSubtype != null)
            predicates.add(criteriaBuilder.equal(stockSizeAndColorJoin.get("subtype"), clothSubtype));


        if (desiredStock != null)
            query.having(criteriaBuilder.greaterThan(criteriaBuilder.sum(root.get("stock")), desiredStock));

        query.groupBy(root.get("product"));







        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
