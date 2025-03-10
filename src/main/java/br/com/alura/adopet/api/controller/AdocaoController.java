package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.adocao.AprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.adocao.ReprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.service.AdocaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adocoes")
public class AdocaoController {

    private final AdocaoService service;

    public AdocaoController(AdocaoService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> solicitar(@RequestBody @Valid SolicitacaoAdocaoDTO dto) {
        try {
            service.solicitar(dto);
            return ResponseEntity.ok("Adocao solicitada com sucesso!");
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/aprovar")
    @Transactional
    public ResponseEntity<String> aprovar(@RequestBody @Valid AprovacaoAdocaoDTO dto) {
        service.aprovar(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reprovar")
    @Transactional
    public ResponseEntity<String> reprovar(@RequestBody @Valid ReprovacaoAdocaoDTO dto) {
        service.reprovar(dto);
        return ResponseEntity.ok().build();
    }

}
