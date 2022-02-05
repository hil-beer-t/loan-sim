package com.hilbert.loansimapi;

import com.hilbert.loansimapi.models.Month;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoanSimApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LoanSimApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
