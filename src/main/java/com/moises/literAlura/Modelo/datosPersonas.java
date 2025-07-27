package com.moises.literAlura.Modelo;

import com.fasterxml.jackson.annotation.*; 

@JsonIgnoreProperties(ignoreUnknown = true)

public record datosPersonas(
    @JsonAlias("birth_year") Integer año_nacimiento,
    @JsonAlias("death_year") Integer año_fallecimiento,
    @JsonAlias("name") String nombre
) {
   
}
