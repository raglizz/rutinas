package lizzie.rutinas.controlador;


import lizzie.rutinas.dto.CreateRutinaDTO;
import lizzie.rutinas.dto.RutinaDTO;
import lizzie.rutinas.modelo.Rutina;
import lizzie.rutinas.servicio.RutinaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutinas")
@CrossOrigin(origins = "http://localhost:5173")
public class RutinaControlador {
    private final RutinaServicio rutinaService;

    public RutinaControlador(RutinaServicio rutinaService) {
        this.rutinaService = rutinaService;
    }

    @GetMapping
    public List<Rutina> getAllRutinas() {
        return rutinaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rutina> getRutinaById(@PathVariable Long id) {
        return ResponseEntity.ok(rutinaService.findById(id));
    }

    // Método único para crear rutina (usa DTO)
    @PostMapping
    public ResponseEntity<RutinaDTO> createRutina(@RequestBody CreateRutinaDTO createRutinaDTO) {
        return ResponseEntity.ok(rutinaService.createRutina(createRutinaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rutina> updateRutina(@PathVariable Long id, @RequestBody Rutina rutinaDetails) {
        Rutina rutina = rutinaService.findById(id);
        rutina.setNombre(rutinaDetails.getNombre());
        rutina.setDescripcion(rutinaDetails.getDescripcion());
        rutina.setDuracion(rutinaDetails.getDuracion());
        return ResponseEntity.ok(rutinaService.save(rutina));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRutina(@PathVariable Long id) {
        rutinaService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}