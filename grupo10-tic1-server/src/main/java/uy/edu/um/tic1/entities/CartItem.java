package uy.edu.um.tic1.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entities.products.Product;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
//
//    @ManyToOne
//        @JoinColumn(name = "product")
//    private Product product;
//
//    @ManyToOne
//        @JoinColumn(name = "sizeAndColor")
//    private SizeAndColor sizeAndColor;
//
//    private Integer quantity;

}
