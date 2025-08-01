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
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Personas> autores;
    @ElementCollection 
    private List<String> lenguajes; 
    private Integer descargas; 

    public Libros(datosLibros libro){
        this.titulo = libro.titulo(); 
        List<Personas> autores = libro.autores().stream()
            .map(a -> new Personas(a.nombre(), a.año_nacimiento(), a.año_fallecimiento()))
            .toList();
        setPersonas(autores); 

        this.lenguajes = libro.lenguajes(); 
        this.descargas = libro.descargas(); 
    }
     public String getTitulo(){
            return titulo; 
        }
    private void setPersonas(List<Personas> autores){
        this.autores = autores; 
    }
    public Personas getPersonas(){
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
