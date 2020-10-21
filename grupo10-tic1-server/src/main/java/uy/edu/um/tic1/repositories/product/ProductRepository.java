package uy.edu.um.tic1.repositories.product;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uy.edu.um.tic1.entities.Product;
import uy.edu.um.tic1.entities.SizeAndColor;

import java.util.List;

@Repository
public interface ProductRepository<T extends Product> extends CrudRepository<T, Integer>, JpaSpecificationExecutor {




}
