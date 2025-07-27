package com.moises.literAlura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.moises.literAlura.Servicios.*;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
private static String url = "https://gutendex.com/books/?ids=2000"; 
	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var libros = new consumoApi();
		var json = libros.obtenerDatos(url); 
		System.out.println(json); 
	}

}
