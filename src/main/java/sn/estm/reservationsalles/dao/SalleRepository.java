package sn.estm.reservationsalles.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.estm.reservationsalles.entites.Salle;
import java.util.Optional;

public interface SalleRepository extends JpaRepository<Salle, Long> {
    Optional<Salle> findByCode(String code);
    boolean existsByCode(String code);
}
