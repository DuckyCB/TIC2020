package uy.edu.um.tic1.entities.products;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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
@DiscriminatorValue("trousers")
@AllArgsConstructor
@Data
@SuperBuilder
public class Trousers extends Product {

    @Getter
    private final static List<String> sizes = Arrays.asList(
            "s", "m", "l", "xl", "38", "42", "46", "50");


    @Override
    public ProductDTO toDTO() {
        return TrousersDTO.builder()
                .id(this.getId())
                .brand(this.getBrand().toDTO())
                .gender(this.getGender())
                .name(this.getName())
                .price(this.getPrice())
                .sizeAndColor(this.getSizeAndColor().stream().map(SizeAndColor::toDTO).collect(Collectors.toSet()))
                .image(this.getImage())
                .build();

    }
}
