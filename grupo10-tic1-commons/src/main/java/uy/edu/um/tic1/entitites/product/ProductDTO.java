package uy.edu.um.tic1.entitites.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import uy.edu.um.tic1.entitites.BrandDTO;
import uy.edu.um.tic1.entitites.SizeAndColorDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Set;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = HoodieDTO.class, name = "hoodie"),
        @JsonSubTypes.Type(value = ShirtDTO.class, name = "shirt"),
        @JsonSubTypes.Type(value = TrousersDTO.class, name = "trousers"),
})

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class ProductDTO {

    @Getter
    private final static List<String> colors = Arrays.asList(
            "white", "black", "red", "blue");




    private Integer id;

    private String name;

    private Double price;

    private Set<SizeAndColorDTO> sizeAndColor;

    private Character gender;

    private BrandDTO brand;

    private byte[] image;

}