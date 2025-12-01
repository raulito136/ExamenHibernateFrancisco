package org.example.service;

import org.example.model.Opinion;
import org.example.model.Pelicula;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Clase servicio que contendra todos los metodos necesarios
 * para realizar las historias de usuario
 * @author Raúl López Palomo
 */

public class DataService {
    SessionFactory sessionFactory;

    /**
     * Constructor de la clase DataService
     * @param sessionFactory
     */
    public DataService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Historia de Usuario 1- Registro de nuevas Películas
     * Metodo para guardar una nueva pelicula
     * @param entity
     * @return Pelicula
     */
    public Pelicula guardarPelicula(Pelicula entity) {
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    /**
     * Historia de Usuario 2- Obtener opiniones de un usuario especifico
     * Metodo para buscar opiniones de un usuario especifico
     * @param email
     * @return Lista de opiniones
     */

    public List<Opinion> findOpinionByEmail(String email){
        try(Session session=sessionFactory.openSession()){
            Query<Opinion> q=session.createQuery("from Opinion o where o.usuario=:email", Opinion.class);
            q.setParameter("email",email);
            return q.getResultList();
        }
    }

    /**
     * Historia de Usuario 3- Añadir opiniones a una pelicula ya existente previamente
     * Metodo para añadir una opinion a una pelicula existente
     * @param pelicula
     * @param opinion
     */

    public void guardarOpinionEnPeliculaExistente(Pelicula pelicula, Opinion opinion){
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            Pelicula peli=session.find(Pelicula.class,pelicula.getId());
            peli.getOpiniones().add(opinion);
            opinion.setPelicula(peli);
            session.persist(opinion);
            session.getTransaction().commit();
        }
    }

    /**
     * Historia de Usuario 4- Listado de peliculas con baja puntuacion
     * Metodo para listar peliculas con baja puntuacion en concreto 3 o menor
     * @return Lista de peliculas
     */

    public List<String> listarPeliculasNotaMenor3(){
        try(Session session=sessionFactory.openSession()){
            Query<String> q=session.createQuery("select distinct p.titulo from Pelicula p join p.opiniones op where op.puntuacion<=3");
            return q.getResultList();

        }
    }
}
