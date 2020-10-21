package uy.edu.um.tic1.repositories.product;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uy.edu.um.tic1.entities.Product;
import uy.edu.um.tic1.entities.products.Shirt;
import uy.edu.um.tic1.repositories.product.ProductRepository;

import java.util.List;

@Repository
public interface ProductShirtRepository extends ProductRepository<Shirt>, JpaSpecificationExecutor {





}
