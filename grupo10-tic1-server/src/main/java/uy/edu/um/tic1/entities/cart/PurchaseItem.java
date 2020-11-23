package uy.edu.um.tic1.entities.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entities.SizeAndColor;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entities.products.Product;
import uy.edu.um.tic1.entitites.cart.PurchaseItemDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseItem {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product",
            foreignKey = @ForeignKey(name = "fk_purchase_item_product")
    )
    @NotNull(message = "Product may not be null")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sizeAndColor",
            foreignKey = @ForeignKey(name = "fk_purchase_item_size_color")
    )
    @NotNull(message = "Size and color may not be null")
    private SizeAndColor sizeAndColor;

    private Double price;

    private Integer quantity;



    public PurchaseItemDTO toDTO(){
        return PurchaseItemDTO.builder()
                .id(this.id)
                .price(this.price)
                .product(this.product.toDTO())
                .quantity(this.quantity)
                .sizeAndColor(this.sizeAndColor.toDTO())
                .build();
    }
}
