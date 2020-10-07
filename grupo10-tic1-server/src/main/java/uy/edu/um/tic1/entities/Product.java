package uy.edu.um.tic1.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import javax.persistence.*;
import java.awt.Color;
import java.util.List;

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
            @JoinTable(name = "product_size",
            joinColumns = @JoinColumn(name = "product_id"),
            foreignKey = @ForeignKey(name = "fk_prodsize_prod"),
            inverseJoinColumns = @JoinColumn(name = "size"),
            inverseForeignKey = @ForeignKey(name = "fk_prodsize_size"))
    List<Size> size;

    @Column(length = 6)
    private String color;

    private Character gender;

    @OneToOne
    @JoinColumn(name = "brand_id",
        foreignKey = @ForeignKey(name = "fk_product_brand"))
    private Brand brand;

    @Lob
    private byte[] image;

}