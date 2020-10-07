package uy.edu.um.tic1.entities;


import com.mysql.cj.exceptions.StreamingNotifiable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50)
    private String address;

    @Embedded
    private TelephoneNumber telephoneNumber;

    @ManyToMany
        @JoinTable(name = "store_brand",
            joinColumns = @JoinColumn(name = "store_id"),
            foreignKey = @ForeignKey(name = "fk_storebrand_store"),
            inverseJoinColumns = @JoinColumn(name = "brand_id"),
            inverseForeignKey = @ForeignKey(name = "fk_storebrand_brand"))
    private Set<Brand> brands;




}
