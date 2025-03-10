package br.com.alura.adopet.api.dto.adocao;

import jakarta.validation.constraints.NotNull;

public record AprovacaoAdocaoDTO(
        @NotNull
        Long idAdocao) {
}
