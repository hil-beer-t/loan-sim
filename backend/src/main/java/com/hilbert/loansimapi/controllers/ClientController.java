package com.hilbert.loansimapi.controllers;

import com.hilbert.loansimapi.models.Client;
import com.hilbert.loansimapi.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = new ArrayList<>(clientRepository.findAll());
        return ResponseEntity.ok(clients);
    }

}
