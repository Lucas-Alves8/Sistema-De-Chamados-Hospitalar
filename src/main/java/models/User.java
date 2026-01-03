package models;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "login", length = 25, unique = true, nullable = false)
    private String login;

    @Column(name = "password", length = 255,nullable = false)
    private String password;

    @Type(ListArrayType.class)
    @Column(name = "roles", columnDefinition = "varchar[]")
    private List<String> roles;

    }

