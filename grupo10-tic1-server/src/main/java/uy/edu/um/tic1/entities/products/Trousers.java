package uy.edu.um.tic1.entities.products;


import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import uy.edu.um.tic1.entities.SizeAndColor;
import uy.edu.um.tic1.entitites.product.HoodieDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;
import uy.edu.um.tic1.entitites.product.TrousersDTO;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("TROUSERS")
@AllArgsConstructor
@Data

@SuperBuilder

public class Trousers extends Product {

    @Getter
    private final static List<String> sizes = Arrays.asList(
            "30", "32", "34", "36", "38", "40", "42", "44", "46");


    @Override
    public ProductDTO toDTO() {
        return TrousersDTO.builder()
                .id(this.getId())
                .brand(this.getBrand().toDTO())
                .gender(this.getGender())
                .subcategory(this.getSubcategory())
                .name(this.getName())
                .price(this.getPrice())
                .sizeAndColor(this.getSizeAndColor().stream().map(SizeAndColor::toDTO).collect(Collectors.toSet()))
                .image(this.getImage())
                .build();

    }



}
