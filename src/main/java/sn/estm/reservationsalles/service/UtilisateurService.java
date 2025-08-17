package sn.estm.reservationsalles.service;

import sn.estm.reservationsalles.entites.Utilisateur;
import java.util.List;

public interface UtilisateurService {
    Utilisateur creer(Utilisateur u);
    Utilisateur lire(Long id);
    List<Utilisateur> lister();
    Utilisateur maj(Long id, Utilisateur u);
    void supprimer(Long id);
}
