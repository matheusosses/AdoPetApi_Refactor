package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetComAdocaoEmAndamento implements IValidador{

    private final AdocaoRepository adocaoRepository;

    public ValidacaoPetComAdocaoEmAndamento(AdocaoRepository adocaoRepository) {
        this.adocaoRepository = adocaoRepository;
    }

    public void validar(SolicitacaoAdocaoDTO dto) {
        if (adocaoRepository.existsByPetIdAndStatus(dto.idPet(), StatusAdocao.APROVADO)) {
            throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
        }
    }
}
