package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.tutor.TutorDTO;
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

    public void cadastrar(TutorDTO dto){
        Tutor tutor = tutorRepository.getReferenceById(dto.idTutor());

        boolean jaCadastrado = tutorRepository.existsByTelefoneOrEmail(tutor.getTelefone(), tutor.getEmail());

        if (jaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        } else {
            tutorRepository.save(tutor);
        }
    }

    public void atualizar(TutorDTO dto){
        Tutor tutor = tutorRepository.getReferenceById(dto.idTutor());
        tutorRepository.save(tutor);
    }
}
