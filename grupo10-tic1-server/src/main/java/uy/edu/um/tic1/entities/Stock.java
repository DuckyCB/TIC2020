package uy.edu.um.tic1.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entities.products.Product;
import uy.edu.um.tic1.entitites.StockDTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stock {


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;


    private Integer stock;

    @ManyToOne
        @JoinColumn(name = "product_id",
        foreignKey = @ForeignKey(name = "fk_stock_product"))
    private Product product;



    @ManyToOne
        @JoinColumn(name = "sizecolor",
        foreignKey =@ForeignKey(name = "fk_stock_sizecolor") )
    private SizeAndColor sizeAndColor;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(id, stock.id);
    }


    public StockDTO toDTO(){
        return StockDTO.builder()
                .id(this.id)
                .product(this.product.toDTO())
                .sizeAndColor(this.sizeAndColor.toDTO())
                .stock(this.stock)
                .build();
    }
}
