package lizzie.rutinas.modelo;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "rutinas")
public class Rutina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rutina")
    private Long idRutina;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    private Integer duracion; // en minutos

    @OneToMany(mappedBy = "rutina", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<AsignacionRutina> asignaciones = new ArrayList<>();
}