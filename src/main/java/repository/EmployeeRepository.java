package repository;

import jakarta.validation.constraints.NotNull;
import models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    boolean existsByCpf(String cpf);

}
