package sn.estm.reservationsalles.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(indexes = {
        @Index(name = "idx_res_salle_deb_fin", columnList = "salle_id,dateDebut,dateFin")
})
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Salle salle;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Utilisateur utilisateur;

    @NotNull
    private LocalDateTime dateDebut;

    @NotNull
    private LocalDateTime dateFin;

    @NotBlank
    private String motif; // Cours, Soutenance, Réunion...

    @PrePersist @PreUpdate
    private void validateDates() {
        if (dateFin != null && dateDebut != null && !dateFin.isAfter(dateDebut)) {
            throw new IllegalArgumentException("dateFin doit être après dateDebut");
        }
    }
}
