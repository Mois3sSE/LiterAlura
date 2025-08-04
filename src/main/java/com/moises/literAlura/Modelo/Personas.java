package com.moises.literAlura.Modelo;

import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Personas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Integer año_nacimiento;
    private Integer año_fallecimiento;
    private String nombre;
    @ManyToMany(mappedBy = "autores", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Libros> libros = new ArrayList<>();  
    public Personas(){}

    public Personas(String nombre,Integer año_nacimiento,Integer año_fallecimiento){
        this.nombre = nombre; 
        this.año_nacimiento = año_nacimiento; 
        this.año_fallecimiento = año_fallecimiento; 
    }
    public Integer getAñoNacimiento(){
        return año_nacimiento; 
    }
    public Integer getAñoFallecimiento(){
        return año_fallecimiento; 
    }
    public String getNombre(){
        return nombre; 
    }
    public List<Libros> getLibros(){
        return libros; 
    }
    public Long getId(){
        return Id; 
    }
    public void setId(Long Id){
        this.Id = Id; 
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personas)) return false;
        Personas p = (Personas) o;
        return Objects.equals(nombre, p.nombre) &&
           Objects.equals(año_nacimiento, p.año_nacimiento) &&
           Objects.equals(año_fallecimiento, p.año_fallecimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, año_nacimiento, año_fallecimiento);
    }
    
    @Override
    public String toString() {
        return "\nAutor: " + nombre + 
        "\nNacimiento: " + año_nacimiento +
        "\nFallecimiento: " + año_fallecimiento;
    }
    
}
