package service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import models.Employee;
import models.SupportTi;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import repository.SupportTiRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupportTiService {

    private final SupportTiRepository repository;

    @PreAuthorize("hasRole('ADMIN')")
    public SupportTi createSupportTi(String name, String cpf, String rg, Integer age) {

        if (repository.existsByCpf(cpf)) {
            throw new RuntimeException("Cpf already used");
        }
        SupportTi supportTi = new SupportTi();

        supportTi.setName(name);
        supportTi.setCpf(cpf);
        supportTi.setRg(rg);
        supportTi.setAge(age);

        return repository.save(supportTi);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public SupportTi updateSupportTi(UUID id, String name, String cpf, String rg, Integer age) {

        SupportTi supportTi = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Employee not found or not existed"));

        if (repository.existsByCpf(cpf) && !supportTi.getCpf().equals(cpf)) {
            throw new RuntimeException("Cpf already used");
        }

        supportTi.setName(name);
        supportTi.setCpf(cpf);
        supportTi.setRg(rg);
        supportTi.setAge(age);

        return repository.save(supportTi);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPPORTTI')")
    public List<SupportTi> listAllSupportTi() {
       return repository.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPPORTTI')")
    public SupportTi listSupportTiById(UUID id){

        return repository.findById(id).orElseThrow(() ->
            new RuntimeException("Id not found or not existed"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public SupportTi deleteSupportTi(UUID id) {
        SupportTi supportTi = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Id not found or not existed"));

        repository.delete(supportTi);
        return supportTi;
    }
}
