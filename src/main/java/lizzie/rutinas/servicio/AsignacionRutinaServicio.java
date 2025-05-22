package lizzie.rutinas.servicio;


import lizzie.rutinas.dto.AsignacionRutinaDTO;
import lizzie.rutinas.modelo.AsignacionRutina;
import lizzie.rutinas.modelo.Rutina;
import lizzie.rutinas.repositorio.AsignacionRutinaRepositorio;
import lizzie.rutinas.repositorio.RutinaRepositorio;

import org.springframework.stereotype.Service;
import lombok.Data;
import java.util.List;

@Service
public class AsignacionRutinaServicio {
    private final AsignacionRutinaRepositorio asignacionRepository;
    private final RutinaRepositorio rutinaRepository;

    public AsignacionRutinaServicio(
        AsignacionRutinaRepositorio asignacionRepository,
        RutinaRepositorio rutinaRepository
    ) {
        this.asignacionRepository = asignacionRepository;
        this.rutinaRepository = rutinaRepository;
    }

    public AsignacionRutinaDTO createAsignacion(AsignacionRutinaDTO dto) {
        Rutina rutina = rutinaRepository.findById(dto.getIdRutina())
            .orElseThrow(() -> new RuntimeException("Rutina no encontrada"));

        AsignacionRutina asignacion = new AsignacionRutina();
        asignacion.setRutina(rutina);
        asignacion.setClienteId(dto.getClienteId()); // 👈 Asegúrate de hacer esto

        AsignacionRutina saved = asignacionRepository.save(asignacion);
        return convertToDTO(saved);
    }




    private AsignacionRutinaDTO convertToDTO(AsignacionRutina asignacion) {
        return new AsignacionRutinaDTO(
            asignacion.getIdAsignacion(),
            asignacion.getRutina().getIdRutina(),
            asignacion.getRutina().getNombre(),
            asignacion.getClienteId() // <-- agrega esto
        );
    }
    
    public AsignacionRutinaDTO updateAsignacion(Long id, AsignacionRutinaDTO dto) {
        AsignacionRutina asignacion = asignacionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));

        // Aquí puedes actualizar los campos necesarios
        asignacion.setClienteId(dto.getClienteId());
        asignacion.setRutina(rutinaRepository.findById(dto.getIdRutina())
            .orElseThrow(() -> new RuntimeException("Rutina no encontrada")));

        AsignacionRutina guardado = asignacionRepository.save(asignacion);

        return new AsignacionRutinaDTO(
            guardado.getIdAsignacion(),
            guardado.getRutina().getIdRutina(),
            guardado.getRutina().getNombre(),
            guardado.getClienteId()
        );
    }


    public List<AsignacionRutina> findAll() {
        return asignacionRepository.findAll();
    }

    public AsignacionRutina findById(Long id) {
        return asignacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));
    }

    public AsignacionRutina save(AsignacionRutina asignacion) {
        return asignacionRepository.save(asignacion);
    }

    public void deleteById(Long id) {
        asignacionRepository.deleteById(id);
    }
}