package uy.edu.um.tic1.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uy.edu.um.tic1.entities.sizecolor.ColorImpl;

import java.util.Optional;

@Repository
public interface ColorRepository extends CrudRepository<ColorImpl, Integer> {

    Optional<ColorImpl> findByColor(String color);


}
