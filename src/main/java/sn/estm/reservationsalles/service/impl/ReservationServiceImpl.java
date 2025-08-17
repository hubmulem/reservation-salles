package sn.estm.reservationsalles.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.estm.reservationsalles.dao.ReservationRepository;
import sn.estm.reservationsalles.dao.SalleRepository;
import sn.estm.reservationsalles.dao.UtilisateurRepository;
import sn.estm.reservationsalles.entites.Reservation;
import sn.estm.reservationsalles.entites.Salle;
import sn.estm.reservationsalles.entites.Utilisateur;
import sn.estm.reservationsalles.exception.ConflitReservationException;
import sn.estm.reservationsalles.exception.NotFoundException;
import sn.estm.reservationsalles.service.ReservationService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepo;
    private final SalleRepository salleRepo;
    private final UtilisateurRepository utilisateurRepo;

    private Salle requireSalle(Long id){
        return salleRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Salle "+id+" introuvable"));
    }

    private Utilisateur requireUtilisateur(Long id){
        return utilisateurRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Utilisateur "+id+" introuvable"));
    }

    @Transactional
    @Override
    public Reservation reserver(Reservation r){
        Salle s = requireSalle(r.getSalle().getId());
        Utilisateur u = requireUtilisateur(r.getUtilisateur().getId());

        boolean conflit = reservationRepo.existsChevauchement(
                s.getId(), r.getDateDebut(), r.getDateFin(), null);
        if (conflit) throw new ConflitReservationException("Créneau déjà pris pour cette salle");

        r.setSalle(s);
        r.setUtilisateur(u);
        return reservationRepo.save(r);
    }

    @Transactional
    @Override
    public Reservation modifier(Long id, Reservation r){
        Reservation cur = lire(id);
        boolean conflit = reservationRepo.existsChevauchement(
                cur.getSalle().getId(), r.getDateDebut(), r.getDateFin(), cur.getId());
        if (conflit) throw new ConflitReservationException("Créneau déjà pris pour cette salle");

        cur.setDateDebut(r.getDateDebut());
        cur.setDateFin(r.getDateFin());
        cur.setMotif(r.getMotif());
        if (r.getSalle() != null) cur.setSalle(requireSalle(r.getSalle().getId()));
        if (r.getUtilisateur() != null) cur.setUtilisateur(requireUtilisateur(r.getUtilisateur().getId()));

        return reservationRepo.save(cur);
    }

    @Override
    public void annuler(Long id){
        reservationRepo.delete(lire(id));
    }

    @Override
    public Reservation lire(Long id){
        return reservationRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Réservation "+id+" introuvable"));
    }

    @Override
    public List<Reservation> lister(){
        return reservationRepo.findAll();
    }

    @Override
    public List<Reservation> rechercherParSalleEtPeriode(Long salleId, LocalDateTime debut, LocalDateTime fin){
        requireSalle(salleId);
        return reservationRepo.findBySalle_IdAndDateDebutBetween(salleId, debut, fin);
    }
}
