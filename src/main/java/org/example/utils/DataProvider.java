package org.example.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase Singletone para gestionar la conexión a la base de datos
 * @author Raúl López Palomo
 */
public class DataProvider {

    private static SessionFactory sessionFactory =null;

    private DataProvider() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            var configuration = new Configuration().configure();
            configuration.setProperty("hibernate.connection.username",System.getenv("DB_USER"));
            configuration.setProperty("hibernate.connection.password",System.getenv("DB_PASSWORD"));
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
