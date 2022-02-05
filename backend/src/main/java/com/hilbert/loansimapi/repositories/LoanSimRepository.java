package com.hilbert.loansimapi.repositories;

import com.hilbert.loansimapi.models.LoanSim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanSimRepository extends JpaRepository<LoanSim, Long> {
}