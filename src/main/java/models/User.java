package models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String login;

    @Column
    private String password;

    @Column(name = "roles")
    private List<String> roles;

    }
