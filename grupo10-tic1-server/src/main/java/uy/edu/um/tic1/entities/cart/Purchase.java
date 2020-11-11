package uy.edu.um.tic1.entities.cart;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entities.users.Client;
import uy.edu.um.tic1.entitites.cart.CartDTO;
import uy.edu.um.tic1.entitites.cart.PurchaseDTO;
import uy.edu.um.tic1.entitites.users.ClientDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Boolean delivered;

    @Basic
    private LocalTime deliveryTime;
    @Basic
    private LocalDate deliveryDate;


    @ManyToOne
    @JoinColumn(name = "client",
            foreignKey = @ForeignKey(name = "fk_purchase_client")
    )
    private Client client;



    @OneToOne
    @JoinColumn(name = "item",
    foreignKey = @ForeignKey(name = "fk_purchase_cartitem"))
    private CartItem cartItem;

    public PurchaseDTO toDTO(){

        return PurchaseDTO.builder()
                .id(this.id)
                .cartItem(this.cartItem.toDTO())
                .client(this.client.toDTO())
                .delivered(this.delivered)
                .deliveryDate(this.deliveryDate)
                .deliveryTime(this.deliveryTime)
                .build();

    }



}
