package uy.edu.um.tic1.repositories.product;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import uy.edu.um.tic1.entities.Product;
import uy.edu.um.tic1.entities.products.Shirt;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ProductRepository<T extends Product> extends CrudRepository<T, Integer> {

    List<T> findByName(String name);

    List<T> findByBrand(String name);

    @Query("select p from Product p, Stock s where p.id = s.id.productId group by (p.id) having sum(s.stock) >= 1 ")
    List<Product> findAllWithStock();



}
