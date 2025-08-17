package sn.estm.reservationsalles.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Utilisateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;

    @Email @NotBlank @Column(unique = true)
    private String email;

    @NotBlank
    private String role; // "ENSEIGNANT", "ADMIN", etc.
}
