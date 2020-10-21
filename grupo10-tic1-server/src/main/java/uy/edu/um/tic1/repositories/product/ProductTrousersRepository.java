package uy.edu.um.tic1.repositories.product;

import org.springframework.data.jpa.repository.Query;
import uy.edu.um.tic1.entities.Product;
import uy.edu.um.tic1.entities.products.Shirt;
import uy.edu.um.tic1.entities.products.Trousers;

import java.util.List;

public interface ProductTrousersRepository extends ProductRepository<Trousers> {


}
