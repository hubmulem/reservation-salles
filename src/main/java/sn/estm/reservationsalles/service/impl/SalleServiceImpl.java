package sn.estm.reservationsalles.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.estm.reservationsalles.dao.SalleRepository;
import sn.estm.reservationsalles.entites.Salle;
import sn.estm.reservationsalles.exception.NotFoundException;
import sn.estm.reservationsalles.service.SalleService;

import java.util.List;

@Service @RequiredArgsConstructor
public class SalleServiceImpl implements SalleService {
    private final SalleRepository repo;

    @Override public Salle creer(Salle s){
        return repo.save(s);
    }
    @Override public Salle lire(Long id){
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Salle "+id+" introuvable"));
    }
    @Override public List<Salle> lister(){ return repo.findAll(); }

    @Override public Salle maj(Long id, Salle s){
        Salle cur = lire(id);
        cur.setNom(s.getNom());
        cur.setCode(s.getCode());
        cur.setCapacite(s.getCapacite());
        cur.setLocalisation(s.getLocalisation());
        return repo.save(cur);
    }
    @Override public void supprimer(Long id){ repo.delete(lire(id)); }
}
