package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Clase Pelicula para la persistencia de datos
 * @author Raúl López Palomo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="pelicula")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;

    @OneToMany(mappedBy = "pelicula",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Opinion> opiniones;
}
