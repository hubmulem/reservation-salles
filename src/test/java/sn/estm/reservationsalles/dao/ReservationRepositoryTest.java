package sn.estm.reservationsalles.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sn.estm.reservationsalles.entites.*;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ReservationRepositoryTest {

    @Autowired ReservationRepository reservationRepo;
    @Autowired SalleRepository salleRepo;
    @Autowired UtilisateurRepository utilisateurRepo;

    @Test
    void detecteChevauchement(){
        Salle s = salleRepo.save(Salle.builder().code("B-201").nom("Amphi").capacite(100).build());
        Utilisateur u = utilisateurRepo.save(Utilisateur.builder().nom("Prof").email("prof@estm.sn").role("ENSEIGNANT").build());

        reservationRepo.save(Reservation.builder()
                .salle(s).utilisateur(u)
                .dateDebut(LocalDateTime.of(2025,8,20,10,0))
                .dateFin(LocalDateTime.of(2025,8,20,12,0))
                .motif("Cours").build());

        boolean conflit = reservationRepo.existsChevauchement(
                s.getId(),
                LocalDateTime.of(2025,8,20,11,0),
                LocalDateTime.of(2025,8,20,13,0),
                null
        );
        assertThat(conflit).isTrue();
    }
}
