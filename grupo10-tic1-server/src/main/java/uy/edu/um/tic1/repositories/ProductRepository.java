package uy.edu.um.tic1.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uy.edu.um.tic1.entities.Product;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findByName(String name);

    @Query("select p from Product p is in(select h from ?h) ")


}
