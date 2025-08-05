package com.moises.literAlura.Cliente;

import java.beans.Transient;
import java.util.*;

import org.hibernate.annotations.SourceType;

import com.moises.literAlura.Modelo.*; 
import com.moises.literAlura.Repositorio.*;
import com.moises.literAlura.Servicios.*;

import jakarta.transaction.Transactional;


public class menuInicial {
private static String urlBase = "https://gutendex.com/books/?";
private String[] menu = {"Elija la opcion a traves de su numero: ", 
"1.- Buscar libro por titulo","2.- Listar libros registrados",
"3.- Listar autores registrados","4.- Listar autores en un lapso de tiempo",
"5.- Listar autores vivos en un determinado año","6.- Listar libros por idioma","0.- Salir"}; 
Scanner scan = new Scanner(System.in); 
consumoApi libros = new consumoApi();
LibrosRepository repositorioLibros; 
PersonasRepository repositorioPersonas;
List<Libros> librosLista;
List<Personas> autoresLista; 


    public menuInicial(){}
    public menuInicial(LibrosRepository repositorioL,PersonasRepository repositoryP){
        this.repositorioLibros = repositorioL;
        this.repositorioPersonas = repositoryP;  
    }

    public void run (){
		int opcion = 0;
		boolean repeticion = true; 

		do {
			while (repeticion) {
				getMenuInicial();
				try {
					opcion = scan.nextInt(); 
					repeticion = false;
				} catch (InputMismatchException e) {
					System.out.println("El valor ingresado no es valido" +
					"\nIngresa un valor valido ");
					scan.next(); 
					}
			} 
			repeticion = true; 
			switch (opcion) {
				case 0 -> System.exit(0);
				case 1 -> buscarLibroTitulo(); 
                case 2 -> listarLibros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresEnLapso();
                case 5 -> listarAutoresEnAño();
                case 6 -> listarLibrosPorIdioma();
				default-> System.out.println("OK");
			}
			
		
		} while (opcion != 0);
		// System.out.println(datos);
		scan.close();
	
    }


    private void getMenuInicial(){
        System.out.println("");
        for (String menuInicial : menu) {
            System.out.println(menuInicial);
        }
        System.out.print("Ingresa una opcion: ");
    }

    private void buscarLibroTitulo (){
        System.out.print("Ingresa el titulo del libro a buscar: ");
        scan.nextLine(); 
        String libro = scan.nextLine();
        System.out.println("Libro ingresado = " + libro); 
        String libroUrl = urlBase.concat("search=")
                .concat(libro.replace(" ", "%20")); 
        System.out.println("La url sera : " + libroUrl);
        var datosGenerales = setDatos(libroUrl); 
		System.out.println(datosGenerales.getLibros() + " " 
		    + datosGenerales.getAutor() + "\nEl peso es de :" + datosGenerales.getAutor().size());
		repositorioLibros.save(datosGenerales.getLibros());
        repositorioPersonas.saveAll(datosGenerales.getAutor()); 
    }

    @Transactional
    private void listarLibros(){
        librosLista = repositorioLibros.findAll();
            librosLista.stream()
            .sorted(Comparator.comparing(Libros::getTitulo))
            .forEach(libro -> {
            System.out.println(libro);
            List<Personas> autores = new ArrayList<>();
            autores = libro.getPersonas(); 
            String mensaje = (autores.size() != 1 ) ? "Autores: " : "Autor: "; 
            System.out.print(mensaje);
            autores.forEach(e -> {
                System.out.print(e.getNombre() + " ");
            });
            System.out.println("\n");
            
            });
    }
    @Transactional
    private void listarAutores(){
        autoresLista = repositorioPersonas.findAll(); 
        impresionListaAutores(autoresLista); 
    }
    @Transactional 
    private void listarAutoresEnLapso(){
        System.out.print("Ingresa el periodo donde quieres saber los autores vivos" +
        "\n1er Año: ");
        Integer año1 = scan.nextInt();
        System.out.print("2do Año: ");
        Integer año2 = scan.nextInt(); 
        List<Personas> autoresAño = repositorioPersonas.autoresPorPeriodo(año1, año2);
        impresionListaAutores(autoresAño);
    }
    @Transactional 
    private void listarAutoresEnAño(){
        System.out.println("Ingresa el año vivo de los autores que desea buscar");
        Integer año = scan.nextInt(); 
        List<Personas> autoresPorAño = repositorioPersonas.autoresPorAño(año); 
        impresionListaAutores(autoresPorAño);
    }
    @Transactional
    private void listarLibrosPorIdioma(){
        List<String> codigos = repositorioLibros.encontrarLenguajes();
        List<String> codigosSeparados = codigos.stream().
            flatMap(codigo -> Arrays.stream(codigo.split(",")))
            .map(String::trim)
            .toList(); 
        System.out.println("Selecciona el idioma que deseas buscar: ");
       codigosSeparados.stream()
       .distinct()
       .forEach(codigo -> {
            String idioma = mapeoLenguajes(List.of(codigo)).get(0); 
            System.out.println(codigo + " --- " + idioma);
       });
       scan.next(); 
       String idioma = scan.nextLine();
       List<Libros> librosPorIdioma = repositorioLibros.buscarPorIdioma(idioma); 
       System.out.println(librosPorIdioma.size());

    }
    private datosGenerales setDatos(String url ){
        var json = libros.obtenerDatos(url); 
        conversorDatos conversor = new conversorDatos(); 
        var datos = conversor.obtenerDatos(json,datosResultantes.class);
        datosGenerales guardadoDatos = new datosGenerales(datos);
        return guardadoDatos;    
    }
    private void impresionListaAutores(List<Personas> autoresLista){
        autoresLista.stream()
        .distinct()
        .sorted(Comparator.comparing(Personas::getNombre))
        .forEach(autor -> {
            System.out.println("-----------------"+autor);
            System.out.print("Libros: ");
            List<Libros> librosAutor = repositorioPersonas.librosPorAutor(autor.getNombre());
            librosAutor.forEach(libro -> {
                System.out.print(libro.getTitulo() + " ");
            }); 
            System.out.println("\n-----------------"); 
        }); 
    }
    private List<String>  mapeoLenguajes(List<String> codigos){
        Map<String,String> mapaIdiomas = Map.of(
            "en","Inglés",
            "es", "Español",
            "pt", "Portugués",
            "pg", "Portugués",
            "fr", "Francés",
            "de", "Alemán"
        ); 
        return codigos.stream()
        .distinct()
        .map(codigo -> mapaIdiomas.getOrDefault(codigo, "Desconocido"))
        .toList(); 
    }
    
}
