package lizzie.rutinas.dto;


import lombok.Data;

@Data
public class RutinaDTO {
    private Long idRutina;
    private String nombre;
    private String descripcion;
    private Integer duracion;
}