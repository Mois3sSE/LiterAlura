package com.moises.literAlura.Cliente;

import java.util.*;
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
                default -> System.out.println("Ingresa una opcion del menu");
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
        System.out.print("Ingresa palabras claves a buscar: ");
        scan.nextLine(); 
        String libro = scan.nextLine();
        String libroUrl = urlBase.concat("search=")
                .concat(libro.replace(" ", "%20")); 
        var datosGenerales = setDatos(libroUrl); 
        try{
		repositorioLibros.save(datosGenerales.getLibros());
        repositorioPersonas.saveAll(datosGenerales.getAutor()); 
        } catch(org.springframework.dao.DataIntegrityViolationException e){
            System.out.println("El libro ingresado ya esta en la base de datos"
            + "\nIngresa otro libro");
            scan.nextLine(); 
            run();
        }
    }

    @Transactional
    private void listarLibros(){
        librosLista = repositorioLibros.findAll();
        impresionListaLibros(librosLista);
    }
    @Transactional
    private void listarAutores(){
        autoresLista = repositorioPersonas.findAll(); 
        impresionListaAutores(autoresLista); 
    }
    @Transactional 
    private void listarAutoresEnLapso(){
        Integer año1,año2; 
        System.out.print("Ingresa el periodo donde quieres saber los autores vivos" +
        "\n(El intervalo abarca el nacimiento despues del primer año"+
        " al fallecimiento del segundo año)\n1er Año: ");
        try{
            año1 = scan.nextInt();
            System.out.print("2do Año: ");
            año2 = scan.nextInt();
            List<Personas> autoresAño = repositorioPersonas.autoresPorPeriodo(año1, año2);
            if(autoresAño.size() == 0){
                System.out.println("No hay autores registrados en ese lapso de tiempo");
            } else { impresionListaAutores(autoresAño); }
        } catch(java.util.InputMismatchException e){
            System.out.println("No has ingresado datos numericos\n"
            +"Vuelve a intentarlo");
            scan.nextLine(); 
            run();
            
        }
        
        
    }
    @Transactional 
    private void listarAutoresEnAño(){
        System.out.println("Ingresa el año vivo de los autores que desea buscar");
        try{
            Integer año = scan.nextInt(); 
            List<Personas> autoresPorAño = repositorioPersonas.autoresPorAño(año);
            if(autoresPorAño.size() == 0){
                System.out.println("No hay autores registrados para ese año");
            } else {impresionListaAutores(autoresPorAño);}
            
        }catch(java.util.InputMismatchException e){
            System.out.println("No has ingresado datos numericos\n"
            +"Vuelve a intentarlo");
            scan.nextLine(); 
            run();
        }
        
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
       scan.nextLine(); 
        String idiomaSel = scan.nextLine(); 
       List<Libros> librosPorIdioma = repositorioLibros.buscarPorIdioma(idiomaSel);
       if(librosPorIdioma.size() == 0){
        System.out.println("Parece que el idioma no esta registrado\nIntentalo de nuevo");
       } else{ impresionListaLibros(librosPorIdioma);}
    

    }
    private datosGenerales setDatos(String url ){
        var json = libros.obtenerDatos(url); 
        conversorDatos conversor = new conversorDatos(); 
        var datos = conversor.obtenerDatos(json,datosResultantes.class);
        datosGenerales guardadoDatos = new datosGenerales(datos);
        return guardadoDatos;    
    }
    private void impresionListaLibros(List<Libros> librosLista){
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
