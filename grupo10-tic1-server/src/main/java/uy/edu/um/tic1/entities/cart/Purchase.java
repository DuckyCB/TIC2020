package uy.edu.um.tic1.entities.cart;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entities.users.Client;
import uy.edu.um.tic1.entitites.cart.PurchaseDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
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



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item",
    foreignKey = @ForeignKey(name = "fk_purchase_purchaseitem"))
    private Set<PurchaseItem> purchaseItems;

    public PurchaseDTO toDTO(){

        return PurchaseDTO.builder()
                .id(this.id)
                .purchaseItems(this.purchaseItems.stream().map(PurchaseItem::toDTO).collect(Collectors.toSet()))
                .client(this.client.toDTO())
                .delivered(this.delivered)
                .deliveryDate(this.deliveryDate)
                .deliveryTime(this.deliveryTime)
                .build();

    }


    public void addPurchaseItem(PurchaseItem purchaseItem){
        this.purchaseItems.add(purchaseItem);
    }

}
