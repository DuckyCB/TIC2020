package uy.edu.um.tic1.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entities.products.Shirt;
import uy.edu.um.tic1.entities.products.Trousers;

import javax.persistence.*;
import java.awt.Color;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="product_type",
        discriminatorType = DiscriminatorType.STRING)

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {





    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 50)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
            @JoinTable(name = "product_size",
            joinColumns = @JoinColumn(name = "product_id"),
            foreignKey = @ForeignKey(name = "fk_prodsize_prod"),
            inverseJoinColumns = @JoinColumn(name = "size"),
            inverseForeignKey = @ForeignKey(name = "fk_prodsize_size"))
    List<Size> size;

    private Color color;

    private Character gender;

    @OneToOne
    private Brand brand;

    @Lob
    private Byte[] image;

}