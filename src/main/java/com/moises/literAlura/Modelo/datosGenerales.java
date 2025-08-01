package com.moises.literAlura.Modelo;

import java.util.*;

public class datosGenerales {
    Scanner scan = new Scanner(System.in);
    private Libros libro ;
    private Personas autor;   

    public datosGenerales(datosResultantes datos){
        List<datosLibros> librosEncontrados = new ArrayList<>();
        int libro; 

        for (datosLibros datosLibros : datos.resultados()) {
            librosEncontrados.add(datosLibros); 
        }
        libro = seleccionarTitulo(librosEncontrados);

        if(!datos.resultados().isEmpty()){
            datosLibros datoLibro = datos.resultados().get(libro);    
            Libros libroSeleccionado = new Libros(datoLibro); 
            Personas autoresLibro = libroSeleccionado.getPersonas(); 

            setLibro(libroSeleccionado);
            setAutor(autoresLibro);
        }
        
    }
    private void setLibro (Libros libro){
        this.libro = libro; 
    }
    private void setAutor(Personas autor){
        this.autor = autor; 
    }
    public Libros getLibros(){
        return libro; 
    }
    public Personas getAutor(){
        return autor; 
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
