package com.moises.literAlura.Modelo;
import java.util.List;
import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)

public record datosLibros(
    @JsonAlias("title") String titulo,
    @JsonAlias("authors") List<datosPersonas> autores,
    @JsonAlias("languages") List<String> lenguajes
) {
    public void getTitulo(){
        System.out.println(titulo);
    }
}
