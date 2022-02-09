package com.hilbert.loansimapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication()
public class LoanSimApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LoanSimApiApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
	}

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
		return encoder;
	}

}
