package service;

import lombok.RequiredArgsConstructor;
import models.Client;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final PasswordEncoder encoder;

    public Client save(Client client) {
        client.setClientSecret(encoder.encode(client.getClientSecret()));
        return repository.save(client);
    }

    public Client getByClientId(String clientId) {
        return repository.findByClientId(clientId);
    }

    public void saves(Client client) {
    }
}

