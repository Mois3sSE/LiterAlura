package com.moises.literAlura.Cliente;

import java.util.*;


public class menuInicial {
private static String urlBase = "https://gutendex.com/books/?";
private String[] menu = {"Elija la opcion a traves de su numero: ", 
"1.- Buscar libro por titulo","2.- Listar libros registrados",
"3.- Listar autores registrados","4.- Listar autores vivos en un determinado año",
"5.- Listar libros por idioma","0.- Salir"}; 
Scanner scan = new Scanner(System.in); 

    public void getMenuInicial(){
        System.out.println("");
        for (String menuInicial : menu) {
            System.out.println(menuInicial);
        }
        System.out.print("Ingresa una opcion: ");
    }

    public String buscarLibroTitulo (){
        System.out.print("Ingresa el titulo del libro a buscar: ");
            String libro = scan.nextLine();
            System.out.println("Libro ingresado = " + libro); 
            String libroUrl = urlBase.concat("search=")
                .concat(libro.replace(" ", "%20")); 
            System.out.println("La url sera : " + libroUrl);
        return libroUrl; 
    }
}
