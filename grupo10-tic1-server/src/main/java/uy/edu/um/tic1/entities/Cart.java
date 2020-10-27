package uy.edu.um.tic1.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entities.users.Client;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
//
//    @OneToMany
//    @JoinColumn(name = "cart",
//            foreignKey = @ForeignKey(name = "fk_cart_item_cart")
//    )
//    private Set<CartItem> items;
//
//
//    private boolean toDeliver;
//
//    private Client client;


}
