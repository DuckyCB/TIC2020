package uy.edu.um.tic1.entities.cart;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entities.SizeAndColor;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entities.products.Product;
import uy.edu.um.tic1.entities.users.Client;
import uy.edu.um.tic1.entitites.cart.CartDTO;
import uy.edu.um.tic1.entitites.users.ClientDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart",
            foreignKey = @ForeignKey(name = "fk_cart_item_cart")
    )
    private Set<CartItem> items;


    private Boolean toDeliver;

    @ManyToOne
    @JoinColumn(name = "client",
            foreignKey = @ForeignKey(name = "fk_cart_client")
    )
    private Client client;


    private Boolean purchased;

    @Basic
    private LocalTime time;
    @Basic
    private LocalDate date;



    public void addToCart(Product product, SizeAndColor sizeAndColor, Integer quantity){

        items.add(CartItem.builder()
                .product(product)
                .sizeAndColor(sizeAndColor)
                .quantity(quantity)
                .build());

    }


    public void processCart(Boolean toDeliver) {
        time = LocalTime.now();
        date = LocalDate.now();
        purchased = true;
        this.toDeliver = toDeliver;

        HashMap<Store, Purchase> purchases = new HashMap<>();

        Store store = null;
        Purchase purchase = null;
        for (CartItem item : items){

            store = item.getStore();
            purchase = purchases.get(store);

            if(purchase != null){
                purchase.addPurchaseItem(item.toPurchaseItem());
            } else{
                purchase = Purchase.builder().purchaseItems(new LinkedHashSet<>()).client(this.client).build();
                purchase.addPurchaseItem(item.toPurchaseItem());
                purchases.put(store, purchase);
            }



//            item.getStore().addPurchase(item.generatePurchase(this.client));
        }

        purchases.forEach(Store::addPurchase);



    }

//    public void storeAcceptedCart(){
//        this.acceptedByStore = true;
//    }


//    public Cart returnProduct(Product product, SizeAndColor sizeAndColor, Integer quantity){
//
//        if(date.compareTo(LocalDate.now().minusDays(3)) > 0) {
//
//            Set<CartItem> itemsSet = new LinkedHashSet<>();
//            items.stream().
//                    forEach(cartItem -> {
//                        if (cartItem.getProduct().equals(product) && cartItem.getSizeAndColor().equals(sizeAndColor)) {
//                            if (cartItem.getQuantity() >= quantity) {
//                                itemsSet.add(CartItem.builder().product(product).sizeAndColor(sizeAndColor).quantity(-quantity).build());
//                            } else {
//                                itemsSet.add((CartItem.builder().product(product).sizeAndColor(sizeAndColor).quantity(-cartItem.getQuantity()).build()));
//                            }
//                        }
//                    });
//
//
//            return Cart.builder()
//                    .client(this.client)
//                    .items(items)
//                    .build();
//        }
//        else{
//            return null;
//        }
//
//    }


    public CartDTO toDTO(){

        CartDTO cart =  CartDTO.builder()
                .id(this.id)
                .purchased(this.purchased)
                .items(this.items.stream().map(CartItem::toDTO).collect(Collectors.toSet()))
                .date(this.date)
                .time(this.time)
                .toDeliver(this.toDeliver)
                .build();

        if (client != null)
            cart.setClient(this.client.toDTO());

        return cart;

    }

}
