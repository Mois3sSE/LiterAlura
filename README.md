# LiterAlura
<p> Programa realizado en el curso de Practicando Spring Boot: Challenge Literalura<br>
Impartido por : <b>Alura LATAM</b></p>
<h2> Descripcion </h2> 
<p>Este programa realiza la union de los conocimientos para la conexion con API's externas a JAVA y la conexion con la base de datos<br> 
Dentro de este programa se hizo uso de la API gratitua <strong>GUTENDEX</strong>. Ademas de un proyexto en spring para fortalecer los conocimientos<br> 
Este programa permite buscar libros y guardarlos en la base de datos para que, a posterior el usuario pueda buscar los libros en su base de datos</p>
<h2>Menu</h2>
<p> Este programa consta del siguiente menu </p>
<ul>
  <li> Buscar libro por titulo</li>
  <li> Listar libros registrados</li>
  <li> Listar autores registrados</li>
  <li> Listar autores en un lapso de tiempo</li>
  <li> Listar autores vivos en un determinado año</li>
  <li> Listar libros por idioma</li>
  <li> Salir</li>
</ul>
<h3>Buscar libro por titulo</h3>
<p>Esta opcion permite buscar en la API GUTENDEX el libro que se guste por medio de la funcion <strong>search</strong> la cual otorga la misma API<br> 
Posterior a la busqueda en la API se realiza la insercion en la base de datos (comprobando antes que no exista el libro en la base de datos)<br>
Esta opcion permite buscar los libros por medio de palabras claves o incluso los nombres de los autores. <br>Para mas informacion, lease la documentacion de la API </p>
<h3>Listar libros registrados</h3>
<p>Esta opcion permite al usuario realizar una busqueda en la base de datos por medio de una interfaz, de esta manera se lista cada uno de los autores que esten
ya registrados en la base de datos del usuario. Se muestra el nombre del libro, la cantidad de descargas, los lenguajes disponibles y el nombre del o los autore(s) </p>
<h3> Listar autores registrados</h3>
<p>Esta opcion permite al usuario realizar la busqueda en la base de datos de cada uno de los autores que ya esten registrados en esta base de datos,
se recolecta la informacion por medio de una interfaz referida a la clase y se lista toda la informacion del autor (nombre,fecha de nacimiento,fecha de fallecimiento)<br> 
adenas de mostrar los libros de los cuales es autor</p>
<h3>Listar autores en un lapso de tiempo</h3>
<p>Esta opcion permite al usuario ingresar un lapso de tiempo para poder saber que autores estaba vivos dentro de ese lapso de tiempo. Para que esta opcion fun
debe haber nacido despues de ese año y el segundo año determinara que el autor debera de haber fallecido antes del año dado</strong></p>
<h3>Listar autores vivos en un determinado año</h3>
<p>Esta opcion permite al usuario ingresar un solo año y poder descubrir llos autores registrados que estaban vivos en ese año.<br> 
Esta opcion le mostrara al usuario toda la informacion del autor y los libros registrados para ese mismo autor</p>
<h3>Listar libros por idioma</h3>
<p>Esta opcion permite al usuario realizar la busqueda de los libros en la base de datos dependiendo del los idiomas disponibles para ese mismo libro.<br>
Se hizo uso de un mapeo para cada uno de los simbolos que se encuentran en la base de datos( pendientes a cambios ). Para que la opcion funcione <br> 
adecuadamente se debe de <strong>hacer uso de los codigos de lenguajes (Ej. "en-> Ingles,es -> Español" , etc )</strong></p>
<h3>Salir</h3>
<p>Esta opcion le permite al usuario salir de la aplicacion</p>
