package uy.edu.um.tic1.repositories.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uy.edu.um.tic1.entities.Product;
import uy.edu.um.tic1.entities.sizecolor.ColorImpl;
import uy.edu.um.tic1.entities.sizecolor.Size;
import uy.edu.um.tic1.entities.sizecolor.SizeAndColor;

import java.util.List;

@Repository
public interface ProductRepository<T extends Product> extends CrudRepository<T, Integer> {

    List<T> findByName(String name);

    List<T> findByBrand(String name);


    @Query("select p from Product p, Stock s where p.id = s.product.id group by (p.id) having sum(s.stock) >= 1 ")
    Iterable<Product> findAllWithStock();

    @Query("select p from Product p, Stock s where p.id = s.product.id and p.name = ?1 group by (p.id) having sum(s.stock) >= 1 ")
    Iterable<Product> findAllWithStockByName();


    @Query("select p from Product p, Stock s where p.id = s.product.id and :sizeAndColor member of p.sizeAndColor group by (p.id) having sum(s.stock) >= 1 ")
    Iterable<Product> findAllWithStockBySizeAndColor(SizeAndColor sizeAndColor);

    @Query("select p from Product p, Stock s where p.id = s.product.id and :size = s.sizeAndColor.size  group by (p.id) having sum(s.stock) >= 1 ")
    Iterable<Product> findAllWithStockBySize(Size size);

    @Query("select p from Product p, Stock s where p.id = s.product.id and :color = s.sizeAndColor.color  group by (p.id) having sum(s.stock) >= 1 ")
    Iterable<Product> findAllWithStockByColor(ColorImpl color);



}
