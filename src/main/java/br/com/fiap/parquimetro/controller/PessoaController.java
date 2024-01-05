package br.com.fiap.parquimetro.controller;

import br.com.fiap.parquimetro.dto.PessoaDTO;
import br.com.fiap.parquimetro.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService service;

    @GetMapping
    public ResponseEntity<Collection<PessoaDTO>> findAll(){
        var pessoas = service.findAll();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
        var pessoa = service.findById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> save(@RequestBody PessoaDTO pessoaDTO){
        pessoaDTO = service.save(pessoaDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(pessoaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> update(
            @PathVariable Long id,
            @RequestBody PessoaDTO pessoaDTO) {

        pessoaDTO = service.update(id,pessoaDTO);

        return ResponseEntity.ok(pessoaDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
