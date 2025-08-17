package sn.estm.reservationsalles.service;

import sn.estm.reservationsalles.entites.Reservation;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    Reservation reserver(Reservation r);
    Reservation modifier(Long id, Reservation r);
    void annuler(Long id);
    Reservation lire(Long id);
    List<Reservation> lister();
    List<Reservation> rechercherParSalleEtPeriode(Long salleId, LocalDateTime debut, LocalDateTime fin);
}
