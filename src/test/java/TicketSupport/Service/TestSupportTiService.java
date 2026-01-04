package TicketSupport.Service;

import models.SupportTi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.SupportTiRepository;
import service.SupportTiService;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class TestSupportTiService {

    @Mock
    private SupportTiRepository repository;

    @InjectMocks
    private SupportTiService service;

    @DisplayName("Test Create SupportTi sucess ")
    @Test
    void shouldCreateSupportTiSucessfully() {

        String name = "Lucas";
        String cpf = "12345678900";
        String rg = "1234567";
        Integer age = 20;

        given(repository.existsByCpf(cpf)).willReturn(false);
        given(repository.save(any(SupportTi.class)))
                .willAnswer(invocation -> invocation.getArguments(0));
    }

    @DisplayName("Test update SupportTi sucess")
    @Test
    void shouldupdateSupportTiSucessfully() {

        UUID id = UUID.randomUUID();

        SupportTi existing = new SupportTi();
        existing.setId(id);
        existing.setName("Lucas");
        existing.setCpf("12345678900");
        existing.setRg("1234567");
        existing.setAge(20);

        String newName = "Lucas Alves";
        String newCpf = "12345678900";
        String newRg = "7654321";
        Integer newAge = 21;

        given(repository.findById(id)).willReturn(Optional.of(existing));
        given(repository.existsByCpf(newCpf)).willReturn(false);
        given(repository.save(any(SupportTi.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        SupportTi result = service.updateSupportTi(
                id, newName, newCpf, newRg, newAge
        );

        assertNotNull(result);
        assertEquals(newName, result.getName());
        assertEquals(newCpf, result.getCpf());
        assertEquals(newRg, result.getRg());
        assertEquals(newAge, result.getAge());

        then(repository).should().findById(id);
        then(repository).should().existsByCpf(newCpf);
        then(repository).should().save(existing);
    }
}

