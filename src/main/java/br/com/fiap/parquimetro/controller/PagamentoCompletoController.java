package br.com.fiap.parquimetro.controller;

import br.com.fiap.parquimetro.dto.PagamentoCompletoDTO;
import br.com.fiap.parquimetro.entities.pagamento.PagamentoCompleto;
import br.com.fiap.parquimetro.service.PagamentoCompletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/pagamentosCompleto")
public class PagamentoCompletoController {

    @Autowired
    private PagamentoCompletoService pagamentoCompletoService;

    @GetMapping
    public ResponseEntity<Collection<PagamentoCompletoDTO>> findAll() {
        return ResponseEntity.ok(pagamentoCompletoService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<PagamentoCompletoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoCompletoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PagamentoCompletoDTO> savePagamento(@RequestBody PagamentoCompletoDTO pagamentoCompletoDTO) {
        pagamentoCompletoService.savePagamento(pagamentoCompletoDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(pagamentoCompletoDTO);
    }
}
