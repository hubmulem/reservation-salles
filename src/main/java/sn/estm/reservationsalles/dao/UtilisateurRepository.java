package sn.estm.reservationsalles.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.estm.reservationsalles.entites.Utilisateur;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);
    boolean existsByEmail(String email);
}
