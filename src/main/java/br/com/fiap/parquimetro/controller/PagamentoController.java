package br.com.fiap.parquimetro.controller;

import br.com.fiap.parquimetro.dto.PagamentoDTO;
import br.com.fiap.parquimetro.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<Collection<PagamentoDTO>> findAll() {
        return ResponseEntity.ok(pagamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> save(@RequestBody PagamentoDTO pagamentoDTO) {
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(pagamentoDTO);
    }

    @PutMapping
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pagamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}