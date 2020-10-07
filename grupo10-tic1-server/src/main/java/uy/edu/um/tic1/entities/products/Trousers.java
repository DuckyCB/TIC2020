package uy.edu.um.tic1.entities.products;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import uy.edu.um.tic1.entities.Product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("trousers")
@AllArgsConstructor

@SuperBuilder
public class Trousers extends Product {
}
