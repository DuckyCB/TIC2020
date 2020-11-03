package uy.edu.um.tic1.entitites.users;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "user_role")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AdminUserDTO.class, name = "admin"),
        @JsonSubTypes.Type(value = ClientDTO.class, name = "client"),
        @JsonSubTypes.Type(value = BrandUserDTO.class, name = "brand"),
        @JsonSubTypes.Type(value = StoreUserDTO.class, name = "store"),
})


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class AppUserDTO {

    private Integer id;

    private String username;

}
