package uy.edu.um.tic1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uy.edu.um.tic1.entities.sizecolor.ColorImpl;
import uy.edu.um.tic1.entities.sizecolor.Size;
import uy.edu.um.tic1.entities.sizecolor.SizeAndColor;

@Repository
public interface SizeAndColorRepository extends CrudRepository<SizeAndColor, Integer> {


    SizeAndColor findBySizeAndColor(Size size, ColorImpl color);

}
