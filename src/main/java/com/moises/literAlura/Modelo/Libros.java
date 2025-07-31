package com.moises.literAlura.Modelo;

import java.util.List;
import jakarta.persistence.*; 

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo; 
    @Transient
    private List<datosPersonas> autores; 
    private List<String> lenguajes; 
    private Integer descargas; 

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
    public Integer getDescargas(){
            return descargas; 
        }
    public Long getId(){
        return Id; 
    }
    @Override
    public String toString() {
        return "========== Libro ==========" + 
        "\nTitulo : " + titulo +
        "\nLenguajes : " + lenguajes + 
        "\nDescargas : " + descargas;
    }
  
}
