package uy.edu.um.tic1.repositories.product;

import org.springframework.data.jpa.repository.Query;
import uy.edu.um.tic1.entities.Product;
import uy.edu.um.tic1.entities.products.Shirt;
import uy.edu.um.tic1.entities.products.Trousers;

import java.util.List;

public interface ProductTrousersRepository extends ProductRepository<Trousers> {


    @Query("select p from Trousers t, Product p, Stock s where t.id = p.id and p.id = s.id.productId group by (t.id) having sum(s.stock) >= 1 ")
    List<Product> findAllTrousersWithStock();

}
