package sn.estm.reservationsalles.service;

import sn.estm.reservationsalles.entites.Salle;
import java.util.List;

public interface SalleService {
    Salle creer(Salle s);
    Salle lire(Long id);
    List<Salle> lister();
    Salle maj(Long id, Salle s);
    void supprimer(Long id);
}
