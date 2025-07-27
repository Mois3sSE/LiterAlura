package com.moises.literAlura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.moises.literAlura.Cliente.*;
import com.moises.literAlura.Servicios.*;
import com.moises.literAlura.Modelo.*;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
private static String url = "https://gutendex.com/books/?ids=20008"; 
	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var libros = new consumoApi();
		var menuInicial = new menuInicial();

		menuInicial.getMenuInicial();
		System.out.println("===============================");

		var json = libros.obtenerDatos(url); 

		conversorDatos conversor = new conversorDatos(); 
		var datos = conversor.obtenerDatos(json,datosResultantes.class); 
		datosGenerales guardadoDatos = new datosGenerales(datos);
		System.out.println(guardadoDatos.getInfoLibros().getTitulo()); 
		
		// System.out.println(datos);
	
	}

}
