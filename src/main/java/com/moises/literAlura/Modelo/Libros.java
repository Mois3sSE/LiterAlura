package com.moises.literAlura.Modelo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Libros {
    private String titulo; 
    private List<datosPersonas> autores; 
    private  List<String> lenguajes; 
    private Number descargas; 

    public Libros(datosLibros libro){
        this.titulo = libro.titulo(); 
        this.autores = libro.autores(); 
        this.lenguajes = libro.lenguajes(); 
        this.descargas = libro.descargas(); 
    }
     public String getTitulo(){
            return titulo; 
        }
    public datosPersonas getAutores(){
        for(int i = 0; i < autores.size();){
            return autores.get(i);
        }
        return null;  
        }
    public List<String> getLenguajes(){
            return lenguajes; 
        }
    public Number getDescargas(){
            return descargas; 
        }
    @Override
    public String toString() {
        return "========== Libro ==========" + 
        "\nTitulo : " + titulo +
        "\nLenguajes : " + lenguajes + 
        "\nDescargas : " + descargas;
    }
  
}
