package uy.edu.um.tic1.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entities.contact.TelephoneNumber;
import uy.edu.um.tic1.entities.sizecolor.ColorImpl;
import uy.edu.um.tic1.entities.sizecolor.Size;

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
    private Set<Brand> brandSet;



    @OneToMany
        @JoinColumn(name = "store",
                foreignKey = @ForeignKey(name = "fk_stock_store")
        )
    private Set<Stock> stockSet;



    public Stock getStock(Integer productId, ColorImpl color, Size size){

        for (Stock stock : stockSet){
            if ((stock.getProduct().getId()).equals(productId)
                    && (stock.getSizeAndColor().getColor()).equals(color)
                    && (stock.getSizeAndColor().getSize()).equals(size)){
                return stock;
            }
        }

        return null;
    }
}
