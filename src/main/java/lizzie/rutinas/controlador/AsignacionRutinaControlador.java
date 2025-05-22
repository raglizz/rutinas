package lizzie.rutinas.controlador;

import lizzie.rutinas.dto.AsignacionRutinaDTO;
import lizzie.rutinas.modelo.AsignacionRutina;
import lizzie.rutinas.servicio.AsignacionRutinaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/asignaciones")
@CrossOrigin(origins = "http://localhost:5173")
public class AsignacionRutinaControlador {
    private final AsignacionRutinaServicio asignacionService;

    public AsignacionRutinaControlador(AsignacionRutinaServicio asignacionService) {
        this.asignacionService = asignacionService;
    }

    @GetMapping
    public List<AsignacionRutinaDTO> getAllAsignaciones() {
        return asignacionService.findAll().stream()
            .map(a -> new AsignacionRutinaDTO(
                a.getIdAsignacion(),
                a.getRutina().getIdRutina(),
                a.getRutina().getNombre(),
                a.getClienteId() // ← CORRECTO
            ))
            .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<AsignacionRutinaDTO> getAsignacionById(@PathVariable Long id) {
        AsignacionRutina asignacion = asignacionService.findById(id);
        AsignacionRutinaDTO dto = new AsignacionRutinaDTO(
            asignacion.getIdAsignacion(),
            asignacion.getRutina().getIdRutina(),
            asignacion.getRutina().getNombre(),
            asignacion.getClienteId()
        );
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public AsignacionRutinaDTO createAsignacion(@RequestBody AsignacionRutinaDTO dto) {
        return asignacionService.createAsignacion(dto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AsignacionRutinaDTO> updateAsignacion(
            @PathVariable Long id,
            @RequestBody AsignacionRutinaDTO dto) {
        AsignacionRutinaDTO updated = asignacionService.updateAsignacion(id, dto);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsignacion(@PathVariable Long id) {
        asignacionService.deleteById(id);
        return ResponseEntity.ok().build();
    }
} 