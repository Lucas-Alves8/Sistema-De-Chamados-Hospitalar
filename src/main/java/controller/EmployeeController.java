package controller;

import controller.dto.CreateEmployeeDto;
import controller.dto.UpdateEmployeeDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import models.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.EmployeeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody @Valid CreateEmployeeDto createEmployeeDto) {
        return employeeService.createEmployee(
                createEmployeeDto.name(),
                createEmployeeDto.cpf(),
                createEmployeeDto.sector(),
                createEmployeeDto.elevatorFloor(),
                createEmployeeDto.rg(),
                createEmployeeDto.age()

        );
    }
    @PutMapping("/employee/update")
    public Employee updateEmployee(@RequestBody @Valid UpdateEmployeeDto updateEmployeeDto) {
        return employeeService.updateEmployee(
                updateEmployeeDto.id(),
                updateEmployeeDto.name(),
                updateEmployeeDto.cpf(),
                updateEmployeeDto.sector(),
                updateEmployeeDto.elevatorFloor(),
                updateEmployeeDto.rg(),
                updateEmployeeDto.age()

        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/employee")
    public List<Employee> listAllEmployees() {
        return employeeService.listAllEmployees();
    }
    @GetMapping("/employee/{id}")
    public Employee listEmployeesById(@PathVariable UUID id) {
        return employeeService.listEmployeesById(id);
    }

}
