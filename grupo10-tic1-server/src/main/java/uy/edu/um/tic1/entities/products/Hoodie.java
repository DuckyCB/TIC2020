package uy.edu.um.tic1.entities.products;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import uy.edu.um.tic1.entities.SizeAndColor;
import uy.edu.um.tic1.entities.cart.CartItem;
import uy.edu.um.tic1.entitites.product.HoodieDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("hoodie")
@AllArgsConstructor

@SuperBuilder
public class Hoodie extends Product {

    @Getter
    private final static List<String> sizes = Arrays.asList(
            "s", "m", "l", "xl");


    @Override
    public ProductDTO toDTO() {
        return HoodieDTO.builder()
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
