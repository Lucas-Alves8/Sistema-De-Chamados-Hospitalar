package service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import models.Employee;
import models.enums.ElevatorFloor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import repository.EmployeeRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Data
public class EmployeeService {

    private final EmployeeRepository repository;

    @PreAuthorize("hasAnyRole('ADMIN')")
    public Employee createEmployee(String name, String cpf, String sector, ElevatorFloor elevatorFloor, String rg, Integer age) {

        if (repository.existsByCpf(cpf)) {
            throw new RuntimeException("CPF already used");
        }

        Employee employee = new Employee();
        employee.setName(name);
        employee.setCpf(cpf);
        employee.setSector(sector);
        employee.setElevatorFloor(elevatorFloor);
        employee.setRg(rg);
        employee.setAge(age);

        return repository.save(employee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Employee updateEmployee(UUID id, String name, String cpf, String sector, ElevatorFloor elevatorFloor, String rg, Integer age) {

        Employee employee = repository.findById(id).orElseThrow(() ->
        new RuntimeException("Employee not found or not existed"));

        if (repository.existsByCpf(cpf) && !employee.getCpf().equals(cpf)) {
            throw new  RuntimeException("Cpf already used");
        }

        employee.setName(name);
        employee.setCpf(cpf);
        employee.setSector(sector);
        employee.setElevatorFloor(elevatorFloor);
        employee.setRg(rg);
        employee.setAge(age);

        return repository.save(employee);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Employee deleteEmployee(UUID id) {

        Employee employee = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Employee not found or not existed"));

        repository.delete(employee);
        return employee;
    }
    @PreAuthorize("hasAnyRole('SUPPORTTI', 'ADMIN')")
    public List<Employee> listAllEmployees() {
        return repository.findAll();

    }
    @PreAuthorize("hasAnyRole('SUPPORTTI', 'ADMIN')")
    public Employee listEmployeesById(UUID id) {
        return repository.findById(id).orElseThrow(()
                -> new RuntimeException("Employee not found or not existed"));

    }

}
