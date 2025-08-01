package com.moises.literAlura.Cliente;

import java.util.*;

import com.moises.literAlura.Modelo.*; 
import com.moises.literAlura.Repositorio.*;
import com.moises.literAlura.Servicios.*;


public class menuInicial {
private static String urlBase = "https://gutendex.com/books/?";
private String[] menu = {"Elija la opcion a traves de su numero: ", 
"1.- Buscar libro por titulo","2.- Listar libros registrados",
"3.- Listar autores registrados","4.- Listar autores vivos en un determinado año",
"5.- Listar libros por idioma","0.- Salir"}; 
Scanner scan = new Scanner(System.in); 
consumoApi libros = new consumoApi();
LibrosRepository repositorio; 

    public menuInicial(){}
    public menuInicial(LibrosRepository repositorio){
        this.repositorio = repositorio; 
    }

    public void run (){
		int opcion = 0;
		boolean repeticion = true; 
		String url = ""; 

		do {
			while (repeticion) {
				getMenuInicial();
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
					url = buscarLibroTitulo(); 
					var datosGenerales = setDatos(url); 
					System.out.println(datosGenerales.getLibros() + " " 
					+ datosGenerales.getAutor());
					repositorio.save(datosGenerales.getLibros()); 
				break;
                case 2: 
                    listarLibros();
                break; 
			
				default:
					break;
			}
			
		
		} while (opcion != 0);
		// System.out.println(datos);
		scan.close();
	
    }


    private void getMenuInicial(){
        System.out.println("");
        for (String menuInicial : menu) {
            System.out.println(menuInicial);
        }
        System.out.print("Ingresa una opcion: ");
    }

    private String buscarLibroTitulo (){
        System.out.print("Ingresa el titulo del libro a buscar: ");
            scan.nextLine(); 
            String libro = scan.nextLine();
            System.out.println("Libro ingresado = " + libro); 
            String libroUrl = urlBase.concat("search=")
                .concat(libro.replace(" ", "%20")); 
            System.out.println("La url sera : " + libroUrl);
        return libroUrl; 
    }
    private void listarLibros(){
        repositorio.findAll(); 
    }

    private datosGenerales setDatos(String url ){
        var json = libros.obtenerDatos(url); 
        conversorDatos conversor = new conversorDatos(); 
        var datos = conversor.obtenerDatos(json,datosResultantes.class);
        datosGenerales guardadoDatos = new datosGenerales(datos);
        return guardadoDatos;    
    }
}
