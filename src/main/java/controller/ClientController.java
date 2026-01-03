package controller;

import lombok.RequiredArgsConstructor;
import models.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.ClientService;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> save(@RequestBody Client client){
        service.saves(client);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
