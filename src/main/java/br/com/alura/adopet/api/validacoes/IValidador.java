package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;

public interface IValidador {
    void validar(SolicitacaoAdocaoDTO dto);
}
