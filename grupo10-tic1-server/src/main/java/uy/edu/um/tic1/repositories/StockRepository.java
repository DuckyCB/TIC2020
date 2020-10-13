package uy.edu.um.tic1.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uy.edu.um.tic1.entities.Product;
import uy.edu.um.tic1.entities.Stock;

import java.util.List;


@Repository
public interface StockRepository extends CrudRepository<Stock, Integer> {



}
