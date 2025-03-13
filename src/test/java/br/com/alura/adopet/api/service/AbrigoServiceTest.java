package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.abrigo.CadastroAbrigoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AbrigoServiceTest {

    @Mock
    private AbrigoRepository repository;

    @Mock
    private CadastroAbrigoDTO dto;

    @Mock
    private Abrigo abrigo;

    @Mock
    private Pet pet;

    @Captor
    private ArgumentCaptor<Abrigo> captor;

    @InjectMocks
    private AbrigoService service;

    @Test
    @DisplayName("Ao cadastrar deveria lançar exceção quando dados já estiverem cadastrados")
    void cenario1(){
        given(repository.getReferenceById(dto.idAbrigo())).willReturn(abrigo);
        given(repository.existsByNomeOrTelefoneOrEmail(abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail()))
                        .willReturn(true);

        assertThrows(ValidacaoException.class, () -> service.cadastrar(dto));
    }

    @Test
    @DisplayName("Ao cadastrar não deveria lançar exceção quando dados não estiverem cadastrados")
    void cenario2(){
        given(repository.getReferenceById(dto.idAbrigo())).willReturn(abrigo);
        given(repository.existsByNomeOrTelefoneOrEmail(abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail()))
                .willReturn(false);

        assertDoesNotThrow(() -> service.cadastrar(dto));
    }

    @Test
    @DisplayName("Ao cadastrar pet deveria lançar exceção quando id não for encontrado")
    void cenario3(){
        String idOuNome = "1";
        Long id = Long.parseLong(idOuNome);

        given(repository.getReferenceById(id)).willThrow(new EntityNotFoundException());

        assertThrows(ValidacaoException.class, () -> service.cadastrarPet(idOuNome, pet));
    }

    @Test
    @DisplayName("Ao cadastrar pet deveria lançar exceção quando nome não for encontrado")
    void cenario4(){
        String idOuNome = "";

        given(repository.findByNome(idOuNome)).willThrow(new EntityNotFoundException());

        assertThrows(ValidacaoException.class, () -> service.cadastrarPet(idOuNome, pet));
    }

    @Test
    @DisplayName("Ao listar pet deveria lançar exceção quando id não for encontrado")
    void cenario5(){
        String idOuNome = "1";
        Long id = Long.parseLong(idOuNome);

        given(repository.getReferenceById(id)).willThrow(new EntityNotFoundException());

        assertThrows(ValidacaoException.class, () -> service.listarPets(idOuNome));
    }

    @Test
    @DisplayName("Ao listar pet deveria lançar exceção quando nome não for encontrado")
    void cenario6(){
        String idOuNome = "";

        given(repository.findByNome(idOuNome)).willThrow(new EntityNotFoundException());

        assertThrows(ValidacaoException.class, () -> service.listarPets(idOuNome));
    }
}