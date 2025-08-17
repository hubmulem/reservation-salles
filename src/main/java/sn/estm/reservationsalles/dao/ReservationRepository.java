package sn.estm.reservationsalles.dao;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import sn.estm.reservationsalles.entites.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Détection de chevauchement pour une salle donnée
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM Reservation r " +
            "WHERE r.salle.id = :salleId " +
            "AND r.dateDebut < :dateFin " +
            "AND r.dateFin > :dateDebut " +
            "AND (:excludeId IS NULL OR r.id <> :excludeId)")
    boolean existsChevauchement(@Param("salleId") Long salleId,
                                @Param("dateDebut") LocalDateTime dateDebut,
                                @Param("dateFin") LocalDateTime dateFin,
                                @Param("excludeId") Long excludeId);

    List<Reservation> findBySalle_IdAndDateDebutBetween(Long salleId, LocalDateTime debut, LocalDateTime fin);
}
