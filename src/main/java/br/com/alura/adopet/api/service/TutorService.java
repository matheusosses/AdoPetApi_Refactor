package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public void cadastrar(Tutor tutor){
        boolean telefoneJaCadastrado = tutorRepository.existsByTelefone(tutor.getTelefone());
        boolean emailJaCadastrado = tutorRepository.existsByEmail(tutor.getEmail());

        if (telefoneJaCadastrado || emailJaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        } else {
            tutorRepository.save(tutor);
        }
    }

    public void atualizar(Tutor tutor){
        tutorRepository.save(tutor);
    }
}
