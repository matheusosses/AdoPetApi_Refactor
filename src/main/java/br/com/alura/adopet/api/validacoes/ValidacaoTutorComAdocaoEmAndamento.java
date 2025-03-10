package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorComAdocaoEmAndamento implements IValidador{

    private final AdocaoRepository adocaoRepository;

    public ValidacaoTutorComAdocaoEmAndamento(AdocaoRepository adocaoRepository) {
        this.adocaoRepository = adocaoRepository;

    }

    public void validar(SolicitacaoAdocaoDTO dto) {
        if (adocaoRepository.existsByTutorIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO)) {
            throw new ValidacaoException("Tutor já possui outra adoção aguardando avaliação!");
        }
    }
}
