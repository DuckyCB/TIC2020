package uy.edu.um.tic1.entitites.product;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;




import uy.edu.um.tic1.entitites.BrandDTO;
import uy.edu.um.tic1.entitites.SizeAndColorDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Set;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "product_type")

@JsonSubTypes({
        @JsonSubTypes.Type(value = HoodieDTO.class, name = "hoodie"),
        @JsonSubTypes.Type(value = ShirtDTO.class, name = "shirt"),
        @JsonSubTypes.Type(value = TrousersDTO.class, name = "trousers"),
})

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public abstract class ProductDTO {

    @Getter
    private final static List<String> colors = Arrays.asList(
            "FDFEFE", "17202A", "6E2C00", "6C3483", "154360", "3498DB",
            "145A32", "58D68D", "F4D03F", "E67E22", "E74C3C", "F5B7B1","5D6D7E");

//    private static final String white = "FDFEFE";
//    private static final String black = "17202A";
//    private static final String brown = "6E2C00";
//
//    private static final String purple = "6C3483";
//    private static final String blue = "154360";
//    private static final String lightBlue = "3498DB";
//
//    private static final String darkGreen = "145A32";
//    private static final String lightGreen = "58D68D";
//    private static final String yellow = "F4D03F";
//
//    private static final String orange = "E67E22";
//    private static final String red = "E74C3C";
//    private static final String pink = "F5B7B1";
//
//    private static final String grey = "5D6D7E";


    private Integer id;

    private String name;

    private Double price;

    private Set<SizeAndColorDTO> sizeAndColor;

    private Character gender;

    private BrandDTO brand;

    private byte[] image;

}