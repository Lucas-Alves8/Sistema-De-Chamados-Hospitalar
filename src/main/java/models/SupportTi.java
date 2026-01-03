package models;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "supportti")
@Data
public class SupportTi {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "age", length = 3, nullable = false)
    private Integer age;

    @Column(name = "rg", length = 20, nullable = false)
    private String rg;

    @Type(ListArrayType.class)
    @Column(name = "roles", columnDefinition = "varchar[]")
    private List<String> roles;
}
