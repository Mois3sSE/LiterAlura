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
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Personas> autores;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> lenguajes; 
    private Integer descargas;

    public Libros(){} 
    public Libros(datosLibros libro){
        this.titulo = libro.titulo(); 
        List<Personas> autoresL = libro.autores().stream()
            .map(a -> new Personas(a.nombre(), a.año_nacimiento(), a.año_fallecimiento()))
            .toList();
        setPersonas(autoresL); 

        this.lenguajes = libro.lenguajes(); 
        this.descargas = libro.descargas(); 
    }
     public String getTitulo(){
            return titulo; 
        }
    private void setPersonas(List<Personas> autores){
        this.autores = autores; 
    }
    public List<Personas> getPersonas(){
        return autores;  
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
        return "==========LIBRO==========\n" +
        "Titulo: " + titulo +
        "\nDescargas: " + descargas +
        "\nLenguajes: " + lenguajes; 
    }
   
  
}
