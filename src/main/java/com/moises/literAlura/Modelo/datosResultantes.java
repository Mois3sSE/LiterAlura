package com.moises.literAlura.Modelo;

import java.util.List;
import com.fasterxml.jackson.annotation.*; 

@JsonIgnoreProperties(ignoreUnknown = true)

public record datosResultantes( 
   @JsonAlias("results") List<datosLibros> resultados
){
}
