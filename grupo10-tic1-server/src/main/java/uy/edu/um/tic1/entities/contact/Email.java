package uy.edu.um.tic1.entities.contact;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {


    @Column(name = "email_user", length = 20)
    private String user;
    @Column(name = "email_domain", length = 20)
    private String domain;

}
