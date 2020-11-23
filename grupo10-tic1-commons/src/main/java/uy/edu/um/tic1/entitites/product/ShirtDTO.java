package uy.edu.um.tic1.entitites.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Data
@AllArgsConstructor

@SuperBuilder
public class ShirtDTO extends ProductDTO {

    @Getter
    private final static List<String> sizes = Arrays.asList(
            "XS", "S", "M", "L", "XL", "XXL");


    public List<String> allSizes(){
        return ShirtDTO.getSizes();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ShirtDTO) o;
        return Objects.equals(this.getId(), that.getId());
    }

}
