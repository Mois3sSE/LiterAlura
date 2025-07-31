package com.moises.literAlura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;
import com.moises.literAlura.Cliente.*;
import com.moises.literAlura.Servicios.*;
import com.moises.literAlura.Modelo.*;
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
		var menuInicial = new menuInicial();
		Scanner scan = new Scanner(System.in); 
		int opcion = 0;
		boolean repeticion = true; 
		String url = ""; 

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
				case 0:
					System.exit(0); 
				break; 
				case 1:
					url = menuInicial.buscarLibroTitulo();
					var datosGenerales = menuInicial.setDatos(url); 
					System.out.println(datosGenerales.getLibros() + " " 
					+ datosGenerales.getAutor());
					repository.save(datosGenerales.getLibros()); 
				break;
			
				default:
					break;
			}
			
		
		} while (opcion != 0);
		// System.out.println(datos);
		scan.close();
	
	}

}
