package com.moises.literAlura.Cliente;
   
public class menuInicial {
 private String[] menu = {"Elija la opcion a traves de su numero: ", 
"1.- Buscar libro por titulo","2.- Listar libros registrados",
"3.- Listar autores registrados","4.- Listar autores vivos en un determinado año",
"5.- Listar libros por idioma","0.- Salir"}; 

    public void getMenuInicial(){
        System.out.println("");
        for (String menuInicial : menu) {
            System.out.println(menuInicial);
        }
    }
}
