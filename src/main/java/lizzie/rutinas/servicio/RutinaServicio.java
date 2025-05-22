package lizzie.rutinas.servicio;


import lizzie.rutinas.dto.CreateRutinaDTO;
import lizzie.rutinas.dto.RutinaDTO;
import lizzie.rutinas.modelo.Rutina;
import lizzie.rutinas.repositorio.RutinaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutinaServicio {
    private final RutinaRepositorio rutinaRepository;

    public RutinaServicio(RutinaRepositorio rutinaRepository) {
        this.rutinaRepository = rutinaRepository;
    }

    public List<Rutina> findAll() {
        return rutinaRepository.findAll();
    }

    public Rutina findById(Long id) {
        return rutinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rutina no encontrada"));
    }

    public Rutina save(Rutina rutina) {
        return rutinaRepository.save(rutina);
    }

    public void deleteById(Long id) {
        rutinaRepository.deleteById(id);
    }
    public RutinaDTO createRutina(CreateRutinaDTO createRutinaDTO) {
        Rutina rutina = new Rutina();
        rutina.setNombre(createRutinaDTO.getNombre());
        rutina.setDescripcion(createRutinaDTO.getDescripcion());
        rutina.setDuracion(createRutinaDTO.getDuracion());
        
        Rutina savedRutina = rutinaRepository.save(rutina);
        return convertToDTO(savedRutina);
    }
    
    // Convertir DTO a Rutina (nuevo método)
    private Rutina convertToEntity(CreateRutinaDTO dto) {
        Rutina rutina = new Rutina();
        rutina.setNombre(dto.getNombre());
        rutina.setDescripcion(dto.getDescripcion());
        rutina.setDuracion(dto.getDuracion());
        return rutina;
    }

    private RutinaDTO convertToDTO(Rutina rutina) {
        RutinaDTO dto = new RutinaDTO();
        dto.setIdRutina(rutina.getIdRutina());
        dto.setNombre(rutina.getNombre());
        dto.setDescripcion(rutina.getDescripcion());
        dto.setDuracion(rutina.getDuracion());
        return dto;
    }
}