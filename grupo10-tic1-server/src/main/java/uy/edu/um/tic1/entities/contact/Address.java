package uy.edu.um.tic1.entities.contact;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entitites.contact.AddressDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {


    private Integer zipCode;
    @Column(length = 50)
    private String street;
    @Column(length = 10)
    private String doorNumber;
    @Column(length = 50)
    private String optional;



    public AddressDTO toDTO(){
        return AddressDTO.builder()
                .zipCode(this.zipCode)
                .street(this.street)
                .doorNumber(this.doorNumber)
                .optional(this.optional)
                .build();
    }


}
