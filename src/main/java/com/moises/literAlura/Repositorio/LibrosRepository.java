package com.moises.literAlura.Repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.moises.literAlura.Modelo.Libros;

public interface LibrosRepository extends JpaRepository<Libros,Long>{
        @Query("select l.lenguajes from Libros l")
    List<String> encontrarLenguajes();
        @Query("select l from Libros l join l.lenguajes lang where :idioma member of l.lenguajes")
    List<Libros> buscarPorIdioma(String idioma);


}
