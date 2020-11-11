package uy.edu.um.tic1.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uy.edu.um.tic1.entities.cart.Purchase;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Integer>, JpaSpecificationExecutor {

}
