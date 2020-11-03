package uy.edu.um.tic1.entitites.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entitites.StoreDTO;
import uy.edu.um.tic1.entitites.users.ClientDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {


    private Integer id;


    private Set<CartItemDTO> items;


    private Boolean toDeliver;


    private ClientDTO client;


    private StoreDTO store;

    private Boolean acceptedByStore;

    private LocalTime time;

    private LocalDate date;

}