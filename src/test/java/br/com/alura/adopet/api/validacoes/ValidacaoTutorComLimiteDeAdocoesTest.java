package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ValidacaoTutorComLimiteDeAdocoesTest {

    @Mock
    private AdocaoRepository repository;

    @Mock
    private SolicitacaoAdocaoDTO dto;

    private ValidacaoTutorComLimiteDeAdocoes validacao;

    @BeforeEach
    void setUp() {
        this.validacao = new ValidacaoTutorComLimiteDeAdocoes(repository);
    }



    @Test
    @DisplayName("Deveria lançar exceção quando tutor tiver o máximo de 5 adoções")
    void cenario1(){
        BDDMockito.given(repository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO))
                .willReturn(5);

        assertThrows(ValidacaoException.class, () -> validacao.validar(dto));
    }

    @Test
    @DisplayName("Não deveria lançar exceção quando tutor tiver adoções disponíveis")
    void cenario2(){
        BDDMockito.given(repository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO))
                .willReturn(0);

        assertDoesNotThrow(() -> validacao.validar(dto));
    }
}