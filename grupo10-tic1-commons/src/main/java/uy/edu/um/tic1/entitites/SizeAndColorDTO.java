package uy.edu.um.tic1.entitites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SizeAndColorDTO {


    private Integer id;

    private String size;

    private String color;





}
