package uy.edu.um.tic1.entities.sizecolor;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"size", "color"},
        name = "uc_size_color"))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SizeAndColor {


    @Id
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "size",
            foreignKey =@ForeignKey(name = "fk_sizecolor_size") )
    private Size size;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "color",
            foreignKey =@ForeignKey(name = "fk_sizecolor_color") )
    private ColorImpl color;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SizeAndColor that = (SizeAndColor) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(size, that.size) &&
                Objects.equals(color, that.color);
    }


}
