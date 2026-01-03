package models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;


import java.util.UUID;

@Entity
@Table(name = "client")
@Data
public class Client {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "client_id", length = 255, nullable = false)
    private String clientId;

    @Column(name = "client_secret", length = 100, nullable = false)
    private String clientSecret;

    @Column(name = "redirect_uri", length = 255, nullable = false)
    private String redirectURI;

    @Column(name = "scope", length = 25, nullable = false)
    private String scope;

}
