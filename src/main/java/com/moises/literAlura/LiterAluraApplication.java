package com.moises.literAlura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;
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
		var menuInicial = new menuInicial();
		var libros = new consumoApi();
		Scanner scan = new Scanner(System.in); 
		int opcion = 0;
		boolean repeticion = true; 

		do {
			while (repeticion) {
				menuInicial.getMenuInicial();
				try {
					opcion = scan.nextInt(); 
					repeticion = false;
				} catch (InputMismatchException e) {
					System.out.println("El valor ingresado no es valido" +
					"\nIngresa un valor valido ");
					scan.next(); 
					}
			} 
			repeticion = true; 

			switch (opcion) {
				case 1:
					menuInicial.buscarLibroTitulo();
				break;
			
				default:
					break;
			}
			
			
		
		} while (opcion != 0);
		

		
		

		var json = libros.obtenerDatos(url); 

		conversorDatos conversor = new conversorDatos(); 
		var datos = conversor.obtenerDatos(json,datosResultantes.class); 
		datosGenerales guardadoDatos = new datosGenerales(datos);
		System.out.println(guardadoDatos.getInfoLibros().getTitulo()); 
		
		// System.out.println(datos);
		scan.close();
	
	}

}
