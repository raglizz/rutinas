package lizzie.rutinas.repositorio;


import lizzie.rutinas.modelo.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RutinaRepositorio extends JpaRepository<Rutina, Long> {
}