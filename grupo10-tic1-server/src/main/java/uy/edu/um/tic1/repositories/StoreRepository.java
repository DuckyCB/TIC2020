package uy.edu.um.tic1.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uy.edu.um.tic1.entities.Store;

@Repository
public interface StoreRepository extends CrudRepository<Store, Integer> {




}
