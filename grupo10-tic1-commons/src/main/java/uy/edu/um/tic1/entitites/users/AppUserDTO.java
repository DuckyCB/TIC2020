package uy.edu.um.tic1.entitites.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AdminUserDTO.class, name = "admin"),
        @JsonSubTypes.Type(value = ClientDTO.class, name = "client"),
        @JsonSubTypes.Type(value = BrandUserDTO.class, name = "brand"),
        @JsonSubTypes.Type(value = StoreUserDTO.class, name = "brand"),
})


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class AppUserDTO {

    private Integer id;

    private String username;

}
