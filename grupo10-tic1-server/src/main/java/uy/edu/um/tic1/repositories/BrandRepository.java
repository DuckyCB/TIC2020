package uy.edu.um.tic1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uy.edu.um.tic1.entities.Brand;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Integer> {
}
