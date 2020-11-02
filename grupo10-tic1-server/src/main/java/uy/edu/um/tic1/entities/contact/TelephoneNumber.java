package uy.edu.um.tic1.entities.contact;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entitites.contact.TelephoneNumberDTO;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelephoneNumber {



    private Integer number;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelephoneNumber that = (TelephoneNumber) o;
        return Objects.equals(number, that.number);
    }


    public TelephoneNumberDTO toDTO() {
        return TelephoneNumberDTO.builder().number(this.number).build();
    }
}
