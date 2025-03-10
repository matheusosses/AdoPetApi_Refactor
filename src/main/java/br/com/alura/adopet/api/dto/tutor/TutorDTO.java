package br.com.alura.adopet.api.dto.tutor;

import br.com.alura.adopet.api.model.Adocao;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record TutorDTO(
        @NotNull
        Long idTutor,

        @NotBlank
        String nome,

        @NotBlank
        @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
        String telefone,

        @NotBlank
        @Email
        String email,

        List<Adocao> pets) {
}
