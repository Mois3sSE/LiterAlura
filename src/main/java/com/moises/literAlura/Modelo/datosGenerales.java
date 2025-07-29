package com.moises.literAlura.Modelo;

import java.util.*;

public class datosGenerales {
    private libroInfo informacionLibros;
    Scanner scan = new Scanner(System.in);  

    public datosGenerales(datosResultantes datos){
        Integer añoNacimiento = null ,añoFallecimiento = null;
        List<String> nombresAutores = new ArrayList<>();
        List<datosLibros> librosEncontrados = new ArrayList<>();
        int libro;  
        for (datosLibros datosLibros : datos.resultados()) {
            librosEncontrados.add(datosLibros); 
        }
        libro = seleccionarTitulo(librosEncontrados);

        if(!datos.resultados().isEmpty()){
            datosLibros datoLibro = datos.resultados().get(libro);    
               
            if(datoLibro.autores()!= null){
                for(datosPersonas autores : datoLibro.autores()){
                    nombresAutores.add(autores.nombre()); 
                    añoNacimiento = autores.año_nacimiento();
                    añoFallecimiento = autores.año_fallecimiento();  
                }
            }
            libroInfo libroInformacion = new libroInfo(
                datoLibro.titulo(), 
                nombresAutores, 
                datoLibro.lenguajes(),
                añoNacimiento,
                añoFallecimiento
                );
                
                informacionLibros = libroInformacion;  
        }
        
    }


    public libroInfo getInfoLibros (){
        return informacionLibros; 
    }

    private int seleccionarTitulo(List<datosLibros> libros){
        System.out.println("Se encontraron los siguientes libros de acuerdo" +
        " a tu solicitud");
        for(int i = 0; i < libros.size(); i++){
            System.out.println((i+1)+".- "+libros.get(i).titulo());
        }
        System.out.println("¿Cual de ellos quieres guardar");
        int opcion = scan.nextInt() - 1; 
        return opcion; 
    }


}
