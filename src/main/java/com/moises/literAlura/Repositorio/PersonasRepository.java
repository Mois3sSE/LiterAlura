package com.moises.literAlura.Repositorio;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.moises.literAlura.Modelo.Libros;
import com.moises.literAlura.Modelo.Personas;

public interface PersonasRepository extends JpaRepository<Personas,Long> {
   List<Personas> findByNombre(String nombre); 
  @Query("SELECT l FROM Libros l JOIN l.autores a WHERE a.nombre LIKE %:nombre%")
   List<Libros> librosPorAutor(String nombre); 

}
