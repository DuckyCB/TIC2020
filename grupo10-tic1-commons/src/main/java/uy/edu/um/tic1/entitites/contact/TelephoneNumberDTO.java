package uy.edu.um.tic1.entitites.contact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelephoneNumberDTO {



    private Integer number;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelephoneNumberDTO that = (TelephoneNumberDTO) o;
        return Objects.equals(number, that.number);
    }


}
