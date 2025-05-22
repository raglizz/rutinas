package lizzie.rutinas.dto;


import lombok.Data;

@Data
public class CreateRutinaDTO {
    private String nombre;
    private String descripcion;
    private Integer duracion;
}