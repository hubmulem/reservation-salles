package sn.estm.reservationsalles.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Salle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(unique = true)
    private String code;            // ex: B-201

    @NotBlank
    private String nom;

    @Min(1)
    private int capacite;

    private String localisation;    // ex: Bloc B, 2e étage
}
