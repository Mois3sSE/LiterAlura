package com.moises.literAlura.Repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.moises.literAlura.Modelo.Libros;

public interface LibrosRepository extends JpaRepository<Libros,Long>{
        @Query("select l.lenguajes from Libros l")
    List<String> encontrarLenguajes();
        @Query("SELECT l FROM Libros l JOIN l.lenguajes lang WHERE :idioma MEMBER OF l.lenguajes")
    List<Libros> buscarPorIdioma(String idioma);


}
