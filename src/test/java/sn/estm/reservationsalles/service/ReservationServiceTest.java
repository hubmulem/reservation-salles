package sn.estm.reservationsalles.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sn.estm.reservationsalles.dao.*;
import sn.estm.reservationsalles.entites.*;
import sn.estm.reservationsalles.exception.ConflitReservationException;
import sn.estm.reservationsalles.service.impl.ReservationServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    @Test
    void refuseSiChevauchement(){
        ReservationRepository rr = mock(ReservationRepository.class);
        SalleRepository sr = mock(SalleRepository.class);
        UtilisateurRepository ur = mock(UtilisateurRepository.class);

        Salle s = Salle.builder().id(1L).code("B-201").nom("Amphi").capacite(100).build();
        Utilisateur u = Utilisateur.builder().id(1L).nom("Prof").email("p@e.sn").role("E").build();
        when(sr.findById(1L)).thenReturn(Optional.of(s));
        when(ur.findById(1L)).thenReturn(Optional.of(u));
        when(rr.existsChevauchement(eq(1L), any(), any(), isNull())).thenReturn(true);

        ReservationService service = new ReservationServiceImpl(rr, sr, ur);
        Reservation r = Reservation.builder()
                .salle(Salle.builder().id(1L).build())
                .utilisateur(Utilisateur.builder().id(1L).build())
                .dateDebut(LocalDateTime.now())
                .dateFin(LocalDateTime.now().plusHours(2))
                .motif("Test").build();

        assertThrows(ConflitReservationException.class, () -> service.reserver(r));
        verify(rr, never()).save(Mockito.any());
    }
}
