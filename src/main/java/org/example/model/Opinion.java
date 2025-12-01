package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase Opinion para la persistencia de datos
 * @author Raúl López Palomo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "opinion")
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;
    private String usuario;
    private Integer puntuacion;
    @ManyToOne
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;

    @Override
    public String toString() {
        return "Opinion{" +
                "puntuacion=" + puntuacion +
                ", usuario='" + usuario + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", id=" + id +
                ", pelicula=" + pelicula.getTitulo() +
                '}';
    }
}
