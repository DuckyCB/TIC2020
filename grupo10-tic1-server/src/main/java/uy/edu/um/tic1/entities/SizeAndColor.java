package uy.edu.um.tic1.entities;


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


    @Column(length = 10)
    private String size;

    @Column(length = 10)
    private String color;





}
