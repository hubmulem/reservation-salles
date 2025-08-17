package sn.estm.reservationsalles.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sn.estm.reservationsalles.entites.Salle;
import sn.estm.reservationsalles.service.SalleService;

import java.util.List;

@RestController
@RequestMapping("/api/salles")
@RequiredArgsConstructor
public class SalleController {
    @Autowired
    private SalleService service;

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Salle create(@Validated @RequestBody Salle s){ return service.creer(s); }

    @GetMapping("/{id}")
    public Salle get(@PathVariable Long id){ return service.lire(id); }

    @GetMapping
    public List<Salle> list(){ return service.lister(); }

    @PutMapping("/{id}")
    public Salle update(@PathVariable Long id, @Validated @RequestBody Salle s){ return service.maj(id, s); }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){ service.supprimer(id); }
}
