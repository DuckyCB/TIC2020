package uy.edu.um.tic1.entities.products;

import uy.edu.um.tic1.entities.Product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("hoodie")
public class Hoodie extends Product {

}
