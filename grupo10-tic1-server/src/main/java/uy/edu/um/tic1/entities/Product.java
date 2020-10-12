package uy.edu.um.tic1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import uy.edu.um.tic1.entities.sizecolor.SizeAndColor;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="product_type",
        discriminatorType = DiscriminatorType.STRING)

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class Product {





    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 50)
    private String name;

    private Double price;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
            @JoinTable(name = "product_size_color",
            joinColumns = @JoinColumn(name = "product_id"),
            foreignKey = @ForeignKey(name = "fk_productsize_product"),
            inverseJoinColumns = @JoinColumn(name = "size_color"),
            inverseForeignKey = @ForeignKey(name = "fk_productsizecolor_sizecolor"))

    Set<SizeAndColor> sizeAndColor;


    private Character gender;

    @OneToOne
    @JoinColumn(name = "brand_id",
        foreignKey = @ForeignKey(name = "fk_product_brand"))
    private Brand brand;

    @Lob
    private byte[] image;

}