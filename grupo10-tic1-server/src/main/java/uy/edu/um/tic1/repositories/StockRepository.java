package uy.edu.um.tic1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uy.edu.um.tic1.entities.Stock;
import uy.edu.um.tic1.entities.StockId;

@Repository
public interface StockRepository extends CrudRepository<Stock, StockId> {
}
