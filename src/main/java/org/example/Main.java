package org.example;

import org.example.model.Opinion;
import org.example.model.Pelicula;
import org.example.service.DataService;
import org.example.utils.DataProvider;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DataService dataService=new DataService(DataProvider.getSessionFactory());

        // Historia de Usuario 1- Registro de nuevas Películas

        Pelicula pelicula=new Pelicula();
        pelicula.setTitulo("Prueba FINAL");

        dataService.guardarPelicula(pelicula);

        // Historia de Usuario 2- Obtener opiniones de un usuario especifico

        dataService.findOpinionByEmail("user1@example.com").forEach(System.out::println);


        // Historia de Usuario 3- Añadir opiniones a una pelicula ya existente previamente

        Opinion opinion=new Opinion();
        opinion.setDescripcion("Opinion 3");
        opinion.setUsuario("raul@gmail.com");
        opinion.setPuntuacion(3);

        Opinion opinion2=new Opinion();
        opinion2.setDescripcion("Opinion 4");
        opinion2.setUsuario("raul@gmail.com");
        opinion2.setPuntuacion(2);



        dataService.guardarOpinionEnPeliculaExistente(pelicula,opinion);
        dataService.guardarOpinionEnPeliculaExistente(pelicula,opinion2);

        //  Historia de Usuario 4- Listado de peliculas con baja puntuacion

        dataService.listarPeliculasNotaMenor3().forEach(System.out::println);

    }
}