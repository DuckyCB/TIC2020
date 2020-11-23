package uy.edu.um.tic1.entitites.product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor

@SuperBuilder

public class HoodieDTO extends ProductDTO {

    @Getter
    private final static List<String> sizes = Arrays.asList(
            "XS", "S", "M", "L", "XL", "XXL");




    public List<String> allSizes(){
        return HoodieDTO.getSizes();
    }
}
