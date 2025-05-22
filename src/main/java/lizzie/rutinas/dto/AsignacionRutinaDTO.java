package lizzie.rutinas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AsignacionRutinaDTO {

    @JsonProperty("idAsignacion")
    private Long idAsignacion;

    @JsonProperty("idRutina")
    private Long idRutina;

    @JsonProperty("nombreRutina")
    private String nombreRutina;
    
    @JsonProperty("clienteId")
    private Long clienteId;

    // Constructor con 4 parámetros (si incluiste clienteId)
    public AsignacionRutinaDTO(Long idAsignacion, Long idRutina, String nombreRutina, Long clienteId) {
        this.idAsignacion = idAsignacion;
        this.idRutina = idRutina;
        this.nombreRutina = nombreRutina;
        this.clienteId = clienteId;
    }

    
}