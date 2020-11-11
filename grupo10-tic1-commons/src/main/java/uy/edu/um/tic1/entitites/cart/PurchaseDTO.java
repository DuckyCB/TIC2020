package uy.edu.um.tic1.entitites.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entitites.users.ClientDTO;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseDTO {


    private Integer id;

    private Boolean delivered;

    private LocalTime deliveryTime;

    private LocalDate deliveryDate;

    private ClientDTO client;

    private CartItemDTO cartItem;
}
