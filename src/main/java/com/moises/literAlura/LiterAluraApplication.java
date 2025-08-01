package com.moises.literAlura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.moises.literAlura.Cliente.*;
import com.moises.literAlura.Repositorio.*;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
	@Autowired
	private LibrosRepository repository; 
	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menuInicial run = new menuInicial(repository);
		run.run(); 
	}

}
