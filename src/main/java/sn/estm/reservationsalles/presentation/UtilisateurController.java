package sn.estm.reservationsalles.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sn.estm.reservationsalles.entites.Utilisateur;
import sn.estm.reservationsalles.service.UtilisateurService;

import java.util.List;

@RestController @RequestMapping("/api/utilisateurs") @RequiredArgsConstructor
public class UtilisateurController {
    @Autowired
    private UtilisateurService service;

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Utilisateur create(@Validated @RequestBody Utilisateur u){ return service.creer(u); }

    @GetMapping("/{id}") public Utilisateur get(@PathVariable Long id){ return service.lire(id); }
    @GetMapping public List<Utilisateur> list(){ return service.lister(); }

    @PutMapping("/{id}") public Utilisateur update(@PathVariable Long id, @Validated @RequestBody Utilisateur u){
        return service.maj(id, u);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){ service.supprimer(id); }
}
