package br.com.fiap.parquimetro.controller;

import br.com.fiap.parquimetro.dto.PessoaDTO;
import br.com.fiap.parquimetro.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Listar todos as pessoas", description = "Lista todas pessoas cadastradas neste parquimetro.")
    public ResponseEntity<Collection<PessoaDTO>> findAll(){
        var pessoas = service.findAll();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter os dados de um motorista por ID", description = "Obtem os dados de um motorista com base no ID fornecido.")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
        var pessoa = service.findById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    @Operation(summary = "Registra um motorista", description = "Registra um motorista, conforme exemplo abaixo.")
    public ResponseEntity<PessoaDTO> save(@RequestBody PessoaDTO pessoaDTO){
        pessoaDTO = service.save(pessoaDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(pessoaDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar motorista por ID", description = "Atualiza dados de um motorista com base no seu id, conforme exemplo abaixo.")
    public ResponseEntity<PessoaDTO> update(
            @PathVariable Long id,
            @RequestBody PessoaDTO pessoaDTO) {

        pessoaDTO = service.update(id,pessoaDTO);

        return ResponseEntity.ok(pessoaDTO);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Apagar dados de um motorista por ID", description = "Apaga o registro de um motorista com base no seu ID, desregistrando-o do sistema.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
