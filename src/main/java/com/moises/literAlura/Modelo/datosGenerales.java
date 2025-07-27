package com.moises.literAlura.Modelo;

import java.util.*;

public class datosGenerales {
    private libroInfo informacionLibros; 

    public datosGenerales(datosResultantes datos){
        Integer añoNacimiento = null ,añoFallecimiento = null; 
        for (datosLibros datosLibros : datos.resultados()) {
            List<String> nombresAutores = new ArrayList<>(); 
            if(datosLibros.autores() != null){
                for(datosPersonas autores : datosLibros.autores()){
                    nombresAutores.add(autores.nombre()); 
                    añoNacimiento = autores.año_nacimiento();
                    añoFallecimiento = autores.año_fallecimiento();  
                }
            }

            libroInfo libroInformacion = new libroInfo(
                datosLibros.titulo(), 
                nombresAutores, 
                datosLibros.lenguajes(),
                añoNacimiento,
                añoFallecimiento
                );
                
                informacionLibros = libroInformacion;  
        }
        
    }

    public libroInfo getInfoLibros (){
        return informacionLibros; 
    }


}
