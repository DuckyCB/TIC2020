package uy.edu.um.tic1.entitites.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entitites.users.ClientDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {

    private Integer id;

    private Set<CartItemDTO> items;

    private Boolean toDeliver;

    private ClientDTO client;

    private Boolean purchased;

    private LocalTime time;

    private LocalDate date;

    public void addItem(CartItemDTO cartItem) {
        AtomicReference<Boolean> alreadyInCart = new AtomicReference<>(false);

        this.getItems().stream().forEach(item ->{
            if (item.getProduct().equals(cartItem.getProduct()) &&
                    item.getSizeAndColor().getColor().equals(cartItem.getSizeAndColor().getColor()) &&
                    item.getSizeAndColor().getSize().equals(cartItem.getSizeAndColor().getSize())
            ){
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                alreadyInCart.set(true);
            }
                }
        );

        if (!alreadyInCart.get())
            items.add(cartItem);
    }

    public boolean decreaseQuantity(CartItemDTO cartItem){
        AtomicBoolean deleted = new AtomicBoolean(false);

        this.setItems(this.getItems().stream().filter (item ->{
            if (item.equals(cartItem)){
                if (item.getQuantity() > 1){
                    item.decreaseQuantity();
                    return true;
                } else{
                    deleted.set(true);
                    return false;
                }

            }
            return true;
        }).collect(Collectors.toSet()));

        return deleted.get();
    }

}