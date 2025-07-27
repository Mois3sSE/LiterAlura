package com.moises.literAlura.Modelo;

import java.util.List;

public class libroInfo {

        private String titulo; 
        private List<String> autores; 
        private List<String> lenguajes;
        private Integer año_nacimiento; 
        private Integer año_fallecimiento;  

        public libroInfo(String title,List<String> autores,List<String> lenguajes
        ,Integer año_nacimiento,Integer año_fallecimiento){
            this.titulo = title; 
            this.autores = autores; 
            this.lenguajes = lenguajes;
            this.año_nacimiento = año_nacimiento; 
            this.año_fallecimiento = año_fallecimiento;  
        }

        public String getTitulo(){
            return titulo; 
        }
        public List<String> getAutores(){
            return autores; 
        }
        public List<String> getLenguajes(){
            return lenguajes; 
        }
        public Integer getAñoNacimiento(){
            return año_nacimiento; 
        }
        public Integer getAñoFallecimiento(){
            return año_fallecimiento; 
        }
        @Override
        public String toString() {
        return "El titulo es " + titulo + 
        "\n, Los autores son " + autores +
        " Nacio el " + año_nacimiento + " y fallecio el " + año_fallecimiento +
        "\n, Los idiomas disponibles son " + lenguajes +
        "\n===========================";
        }
}
