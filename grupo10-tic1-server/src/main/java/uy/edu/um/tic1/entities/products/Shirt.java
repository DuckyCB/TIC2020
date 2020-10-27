package uy.edu.um.tic1.entities.products;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("shirt")
@AllArgsConstructor

@SuperBuilder
public class Shirt extends Product {
    @Getter
    private final static List<String> sizes = Lists.newArrayList(
            "s", "m", "l", "xl");

}
