package com.moises.literAlura.Modelo;

public class Personas {
    private Integer año_nacimiento;
    private Integer año_fallecimiento; 
    private String nombre; 

    public Personas(datosPersonas autor){
        this.año_fallecimiento = autor.año_fallecimiento(); 
        this.año_nacimiento = autor.año_nacimiento(); 
        this.nombre = autor.nombre(); 
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
    @Override
    public String toString() {
        return "\nAutor : "+ nombre +
        "\nNacimiento : " + año_nacimiento + 
        "\nFallecimiento : " + año_fallecimiento +
        "\n=======================";
    }
}
