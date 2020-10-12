package uy.edu.um.tic1.repositories.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uy.edu.um.tic1.entities.Product;
import uy.edu.um.tic1.entities.products.Shirt;
import uy.edu.um.tic1.repositories.product.ProductRepository;

import java.util.List;

@Repository
public interface ProductShirtRepository extends ProductRepository<Shirt> {


    List<Shirt> findByBrand(String brand);

    @Query("select p from Shirt sh, Product p, Stock s where sh.id = p.id and p.id = s.product.id group by (p.id) having sum(s.stock) >= 1 ")
    List<Product> findAllWithStock();

}
