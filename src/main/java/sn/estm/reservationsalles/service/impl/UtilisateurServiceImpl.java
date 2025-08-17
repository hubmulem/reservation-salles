package sn.estm.reservationsalles.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.estm.reservationsalles.dao.UtilisateurRepository;
import sn.estm.reservationsalles.entites.Utilisateur;
import sn.estm.reservationsalles.exception.NotFoundException;
import sn.estm.reservationsalles.service.UtilisateurService;

import java.util.List;

@Service @RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateurRepository repo;

    @Override public Utilisateur creer(Utilisateur u){ return repo.save(u); }
    @Override public Utilisateur lire(Long id){
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Utilisateur "+id+" introuvable"));
    }
    @Override public List<Utilisateur> lister(){ return repo.findAll(); }
    @Override public Utilisateur maj(Long id, Utilisateur u){
        Utilisateur cur = lire(id);
        cur.setNom(u.getNom());
        cur.setEmail(u.getEmail());
        cur.setRole(u.getRole());
        return repo.save(cur);
    }
    @Override public void supprimer(Long id){ repo.delete(lire(id)); }
}
