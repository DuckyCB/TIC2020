package uy.edu.um.tic1.entities.sizecolor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorImpl {

    @Id
    @Column(length = 10, unique = true)
    private String color;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorImpl color1 = (ColorImpl) o;
        return Objects.equals(color, color1.color);
    }


}
