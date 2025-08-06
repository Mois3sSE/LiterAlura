package com.moises.literAlura.Repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.moises.literAlura.Modelo.Libros;
import com.moises.literAlura.Modelo.Personas;

public interface PersonasRepository extends JpaRepository<Personas,Long> {
   List<Personas> findByNombre(String nombre); 
      @Query("select l from Libros l join l.autores a where a.nombre like %:nombre%")
   List<Libros> librosPorAutor(String nombre); 
      @Query("select a from Personas a where a.año_nacimiento >= :año1 and a.año_fallecimiento <= :año2")
   List<Personas> autoresPorPeriodo(Integer año1,Integer año2); 
      @Query("select a from Personas a where año_nacimiento <= :año and año_fallecimiento >= :año")
   List<Personas> autoresPorAño(Integer año); 

}
