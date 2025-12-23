package models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = )
    private String sector
}
