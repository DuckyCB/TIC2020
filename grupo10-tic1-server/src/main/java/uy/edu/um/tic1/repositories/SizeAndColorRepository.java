package uy.edu.um.tic1.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uy.edu.um.tic1.entities.SizeAndColor;

@Repository
public interface SizeAndColorRepository extends CrudRepository<SizeAndColor, Integer> , JpaSpecificationExecutor {


}
