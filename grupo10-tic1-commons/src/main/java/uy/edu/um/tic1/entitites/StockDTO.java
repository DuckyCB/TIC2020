package uy.edu.um.tic1.entitites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entitites.product.ProductDTO;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockDTO {


    private Integer id;

    private Integer stock;

    private ProductDTO product;

    private SizeAndColorDTO sizeAndColor;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockDTO stock = (StockDTO) o;
        return Objects.equals(id, stock.id);
    }


}
