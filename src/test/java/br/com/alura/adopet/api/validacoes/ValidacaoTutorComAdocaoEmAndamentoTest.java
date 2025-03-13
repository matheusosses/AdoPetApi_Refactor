package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidacaoTutorComAdocaoEmAndamentoTest {

    @Mock
    private AdocaoRepository repository;

    @Mock
    private SolicitacaoAdocaoDTO dto;

    private ValidacaoTutorComAdocaoEmAndamento validacao;

    @BeforeEach
    void setUp() {
        this.validacao = new ValidacaoTutorComAdocaoEmAndamento(repository);
    }

    @Test
    @DisplayName("Deveria lançar exceção quando tutor está com avaliação em aguardo")
    void cenario1(){
        BDDMockito.given(repository.existsByTutorIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO))
                .willReturn(true);

        assertThrows(ValidacaoException.class, () -> validacao.validar(dto));
    }

    @Test
    @DisplayName("Não deveria lançar exceção quando tutor está disponível")
    void cenario2(){
        BDDMockito.given(repository.existsByTutorIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO))
                .willReturn(false);

        assertDoesNotThrow(() -> validacao.validar(dto));
    }
}