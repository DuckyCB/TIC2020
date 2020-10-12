package uy.edu.um.tic1.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entities.sizecolor.SizeAndColor;

import javax.persistence.*;

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





}
