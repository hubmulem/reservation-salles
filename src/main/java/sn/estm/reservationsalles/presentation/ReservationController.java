package sn.estm.reservationsalles.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.estm.reservationsalles.entites.Reservation;
import sn.estm.reservationsalles.service.ReservationService;

import java.time.LocalDateTime;
import java.util.List;

@RestController @RequestMapping("/api/reservations") @RequiredArgsConstructor
public class ReservationController {
    @Autowired
    private ReservationService service;

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Reservation reserver(@RequestBody Reservation r){ return service.reserver(r); }

    @PutMapping("/{id}")
    public Reservation modifier(@PathVariable Long id, @RequestBody Reservation r){ return service.modifier(id, r); }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void annuler(@PathVariable Long id){ service.annuler(id); }

    @GetMapping("/{id}") public Reservation get(@PathVariable Long id){ return service.lire(id); }
    @GetMapping public List<Reservation> list(){ return service.lister(); }

    @GetMapping("/salle/{salleId}")
    public List<Reservation> rechercher(@PathVariable Long salleId,
                                        @RequestParam LocalDateTime debut,
                                        @RequestParam LocalDateTime fin){
        return service.rechercherParSalleEtPeriode(salleId, debut, fin);
    }
}
