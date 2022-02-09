package com.hilbert.loansimapi.services;

import com.hilbert.loansimapi.data.ClientDetailData;
import com.hilbert.loansimapi.models.Client;
import com.hilbert.loansimapi.repositories.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientDetailServiceImpl implements UserDetailsService {

    private final ClientRepository clientRepository;

    public ClientDetailServiceImpl(ClientRepository repository) {
        this.clientRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Client> client = Optional.ofNullable(clientRepository.findByEmail(email));

        if(client.isEmpty()){
            throw new UsernameNotFoundException("Client email [" + email + "] not found");
        }

        return new ClientDetailData(client);
    }
}
