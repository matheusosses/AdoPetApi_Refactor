package br.com.alura.adopet.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "pets")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoPet tipo;

    @NotBlank
    private String nome;

    @NotBlank
    private String raca;

    @NotNull
    private Integer idade;

    @NotBlank
    private String cor;

    @NotNull
    private Float peso;

    private Boolean adotado;

    @ManyToOne
    @JsonBackReference("abrigo_pets")
    private Abrigo abrigo;

    @OneToOne(mappedBy = "pet")
    @JsonBackReference("adocao_pets")
    private Adocao adocao;

    public void atualizar(Abrigo abrigo) {
        this.abrigo = abrigo;
        this.adotado = false;
    }
}
