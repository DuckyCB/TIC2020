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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


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


    private Integer id;

    private String name;

    private Double price;

    private Set<SizeAndColorDTO> sizeAndColor;

    private Character gender;

    private Integer subcategory;

    private BrandDTO brand;

    private byte[] image;



    public void removeSizeAndColorBySize(String size){
        this.setSizeAndColor(this.getSizeAndColor().stream().filter(sc -> !sc.getSize().equals(size)).collect(Collectors.toSet()));

    }

    public void removeSizeAndColorByColor(String color){
        this.setSizeAndColor(this.getSizeAndColor().stream().filter(sc -> !sc.getColor().equals(color)).collect(Collectors.toSet()));

    }

    public void removeSizeAndColorBySizeAndColor(String size, String color){
        this.setSizeAndColor(this.getSizeAndColor().stream().filter(sc -> !(sc.getColor().equals(color) && sc.getSize().equals(size))).collect(Collectors.toSet()));

    }
    
    public void addSizeAndColor(SizeAndColorDTO sizeAndColor){
        this.getSizeAndColor().add(sizeAndColor);
    }


    public SizeAndColorDTO getSizeAndColorBySizeAndColor(String size, String color){

        Optional<SizeAndColorDTO> optionalSizeAndColorDTO =  this.getSizeAndColor().stream().filter(sc -> sc.getColor().equals(color) && sc.getSize().equals(size)).findFirst();

        if (optionalSizeAndColorDTO.isPresent()){
            return optionalSizeAndColorDTO.get();
        }
        return null;

    }
}