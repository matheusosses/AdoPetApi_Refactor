package br.com.alura.adopet.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "adocoes")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data")
    private LocalDateTime data;

    @NotNull
    @ManyToOne
    @JsonBackReference("tutor_adocoes")
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @NotNull
    @OneToOne
    @JoinColumn(name = "pet_id")
    @JsonManagedReference("adocao_pets")
    private Pet pet;

    @NotBlank
    @Column(name = "motivo")
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusAdocao status;

    @Column(name = "justificativa_status")
    private String justificativaStatus;
}
