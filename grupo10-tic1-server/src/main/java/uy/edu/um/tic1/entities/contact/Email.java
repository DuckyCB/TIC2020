package uy.edu.um.tic1.entities.contact;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entitites.contact.EmailDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Email {


    @Column(name = "email_user", length = 20)
    private String user;
    @Column(name = "email_domain", length = 20)
    private String domain;

    public EmailDTO toDTO() {
        return EmailDTO.builder()
                .user(this.user)
                .domain(this.domain)
                .build();
    }
}
