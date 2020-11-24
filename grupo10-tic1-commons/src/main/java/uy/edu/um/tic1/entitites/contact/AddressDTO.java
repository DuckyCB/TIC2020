package uy.edu.um.tic1.entitites.contact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

    private Integer zipCode;
    private String street;
    private String doorNumber;
    private String optional;


    @Override
    public String toString() {
        return street.toString() + " " + doorNumber.toString() + " " + optional.toString() + "\n" +
                "Código Zip: " + zipCode.toString();
    }

}
