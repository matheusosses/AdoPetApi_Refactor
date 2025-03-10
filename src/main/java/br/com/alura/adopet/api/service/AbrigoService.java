package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.abrigo.CadastroAbrigoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {

    private final AbrigoRepository abrigoRepository;

    public AbrigoService(AbrigoRepository abrigoRepository) {
        this.abrigoRepository = abrigoRepository;
    }

    public List<Abrigo> listar(){
        return abrigoRepository.findAll();
    }

    public void cadastrar(@Valid CadastroAbrigoDTO dto){
        Abrigo abrigo = abrigoRepository.getReferenceById(dto.idAbrigo());

        boolean nomeJaCadastrado = abrigoRepository.existsByNome(abrigo.getNome());
        boolean telefoneJaCadastrado = abrigoRepository.existsByTelefone(abrigo.getTelefone());
        boolean emailJaCadastrado = abrigoRepository.existsByEmail(abrigo.getEmail());

        if (nomeJaCadastrado || telefoneJaCadastrado || emailJaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        } else {
            abrigoRepository.save(abrigo);
        }
    }

    public List<Pet> listarPets(String idOuNome){
        try {
            Long id = Long.parseLong(idOuNome);
            return abrigoRepository.getReferenceById(id).getPets();
        } catch (EntityNotFoundException enfe) {
            throw new ValidacaoException("Informaçao não encontrada");
        } catch (NumberFormatException e) {
            try {
                return abrigoRepository.findByNome(idOuNome).getPets();
            } catch (EntityNotFoundException enfe) {
                throw new ValidacaoException("Informaçao não encontrada");
            }
        }
    }

    public void cadastrarPet(String idOuNome, Pet pet) {
        try {
            Long id = Long.parseLong(idOuNome);
            Abrigo abrigo = abrigoRepository.getReferenceById(id);
            atualizarInformacoes(pet, abrigo);
        } catch (EntityNotFoundException enfe) {
            throw new ValidacaoException("Informaçao não encontrada");
        } catch (NumberFormatException nfe) {
            try {
                Abrigo abrigo = abrigoRepository.findByNome(idOuNome);
                atualizarInformacoes(pet, abrigo);
            } catch (EntityNotFoundException enfe) {
                throw new ValidacaoException("Informaçao não encontrada");
            }
        }
    }

    private void atualizarInformacoes(Pet pet, Abrigo abrigo){
        pet.atualizar(abrigo);
        abrigo.getPets().add(pet);
        abrigoRepository.save(abrigo);
    }
}
