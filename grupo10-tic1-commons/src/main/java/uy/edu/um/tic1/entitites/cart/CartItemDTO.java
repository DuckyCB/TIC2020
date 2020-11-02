package uy.edu.um.tic1.entitites.cart;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entitites.SizeAndColorDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDTO {



    private Integer id;

    private ProductDTO product;

    private SizeAndColorDTO sizeAndColor;

    private Double price;

    private Integer quantity;

}
