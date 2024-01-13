package br.com.fiap.parquimetro.controller;

import br.com.fiap.parquimetro.dto.CalculoPagamentoDTO;
import br.com.fiap.parquimetro.service.CalculoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/calculoPagamentos")
public class CalculoPagamentoController {

    @Autowired
    private CalculoPagamentoService calculoPagamentoService;

    @GetMapping
    public ResponseEntity<Collection<CalculoPagamentoDTO>> findAll () {
        return ResponseEntity.ok(calculoPagamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalculoPagamentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(calculoPagamentoService.findById(id));
    }

    @PostMapping("/entrada")
    public ResponseEntity<CalculoPagamentoDTO> saveDadosEntrada(@RequestBody CalculoPagamentoDTO calculoPagamentoDTO) {
        calculoPagamentoDTO = calculoPagamentoService.saveDadosEntrada(calculoPagamentoDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(calculoPagamentoDTO);
    }

    @PutMapping("/saida/{id}")
//    public ResponseEntity<CalculoPagamentoDTO> saveDadosSaida(@PathVariable Long id, @RequestBody DadosSaidaDTO dadosSaidaDTO) {
    public ResponseEntity<CalculoPagamentoDTO> saveDadosSaida(@PathVariable Long id, @RequestBody CalculoPagamentoDTO calculoPagamentoDTO) {
        calculoPagamentoDTO = calculoPagamentoService.saveDadosSaida(id, calculoPagamentoDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(calculoPagamentoDTO);
    }

    @PutMapping
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        calculoPagamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
