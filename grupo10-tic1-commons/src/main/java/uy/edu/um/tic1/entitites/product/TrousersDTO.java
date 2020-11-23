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
public class TrousersDTO extends ProductDTO {

    @Getter
    private final static List<String> sizes = Arrays.asList(
            "30", "32", "34", "36", "38", "40", "42", "44", "46");



    public List<String> allSizes(){
        return TrousersDTO.getSizes();
    }
}
