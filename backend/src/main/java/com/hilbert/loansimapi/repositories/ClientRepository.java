package com.hilbert.loansimapi.repositories;

import com.hilbert.loansimapi.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}