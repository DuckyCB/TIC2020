package uy.edu.um.tic1.entities.sizecolor;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SizeAndColor {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "size",
            foreignKey =@ForeignKey(name = "fk_sizecolor_size") )
    private Size size;

    @ManyToOne
    @JoinColumn(name = "color",
            foreignKey =@ForeignKey(name = "fk_sizecolor_color") )
    private ColorImpl color;
}
