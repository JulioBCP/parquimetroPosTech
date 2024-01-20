package br.com.fiap.parquimetro.controller;

import br.com.fiap.parquimetro.dto.EstacionamentoDTO;
import br.com.fiap.parquimetro.entities.Estacionamento;
import br.com.fiap.parquimetro.service.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/estacionamentos")
public class EstacionamentoController {

    @Autowired
    private EstacionamentoService estacionamentoService;

    @GetMapping
    public ResponseEntity<Collection<EstacionamentoDTO>> findAll() {
        return ResponseEntity.ok(estacionamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstacionamentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(estacionamentoService.findById(id));
    }

    @PostMapping("/entrada")
    public ResponseEntity<EstacionamentoDTO> salvarInicioEstacionamento(@RequestBody EstacionamentoDTO estacionamentoDTO) {
        EstacionamentoDTO retornoEstacionamentoService = estacionamentoService.salvarInicioEstacionamento(estacionamentoDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(retornoEstacionamentoService);
    }

    @PutMapping("/saida/{id}")
    public ResponseEntity<EstacionamentoDTO> salvarFimEstacionamento(@PathVariable Long id,
                                                                     @RequestBody EstacionamentoDTO estacionamentoDTO) {
     EstacionamentoDTO retornoEstacionamentoService= estacionamentoService.salvarFimEstacionamento(id, estacionamentoDTO);
     return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(retornoEstacionamentoService);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estacionamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
