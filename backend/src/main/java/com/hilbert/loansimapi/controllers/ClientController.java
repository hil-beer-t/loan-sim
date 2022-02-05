package com.hilbert.loansimapi.controllers;

import com.hilbert.loansimapi.models.Client;
import com.hilbert.loansimapi.models.LoanSim;
import com.hilbert.loansimapi.models.LoanStatus;
import com.hilbert.loansimapi.repositories.ClientRepository;
import com.hilbert.loansimapi.repositories.LoanSimRepository;
import com.hilbert.loansimapi.utils.GenerateLoanSimCod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    LoanSimRepository loanSimRepository;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = new ArrayList<>(clientRepository.findAll());
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<Client> insertOneClient(@RequestBody Client clientInBody){

        Client client = clientRepository.findByEmail(clientInBody.getEmail());

        if ( client != null){
            return ResponseEntity.unprocessableEntity().build();
        }

        // TODO: ENCODE PASSWORD -> clientInBody.setPassword(passwordEncoder.encode(clientInBody.getPassword()));
        clientInBody = clientRepository.save(clientInBody);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(clientInBody.getId()).toUri();
        return ResponseEntity.created(uri).body(clientInBody);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable Long id){
        Client client = clientRepository.findById(id).get();
        return ResponseEntity.ok(client);
    }

    @PostMapping(value = "/{clientId}/create-loan-sim")
    public ResponseEntity<LoanSim> createLoanSim(@RequestBody LoanSim LoanSimInBody, @PathVariable Long clientId){

        // TODO: autenticate

        Client client = clientRepository.findById(clientId).get();
        GenerateLoanSimCod generateCod = new GenerateLoanSimCod(client.getId());
        LoanSimInBody.setCod(generateCod.getCod());
        LoanSimInBody.setStatus(LoanStatus.WAITING_FOR_APPROVAL);
        LoanSimInBody.setClient(client);
        LoanSimInBody = loanSimRepository.save(LoanSimInBody);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{clientId}")
                .buildAndExpand(LoanSimInBody.getId()).toUri();
        return ResponseEntity.created(uri).body(LoanSimInBody);
    }

}
