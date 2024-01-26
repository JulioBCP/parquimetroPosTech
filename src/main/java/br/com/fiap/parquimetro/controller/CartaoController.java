package br.com.fiap.parquimetro.controller;

import br.com.fiap.parquimetro.dto.CartaoDTO;
import br.com.fiap.parquimetro.entities.pagamento.Cartao;
import br.com.fiap.parquimetro.service.CartaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    CartaoService cartaoService;

    @GetMapping("/{id}")
    @Operation(summary = "Obter o cartão de um motorista", description = "Obtem os dados do cartão com base no seu id.")
    public ResponseEntity<CartaoDTO> findById(@PathVariable Long id){
        var cartao = cartaoService.findById(id);
        return ResponseEntity.ok(cartao);
    }

    @PostMapping
    @Operation(summary = "Registrar um cartão de credito ou debito", description = "Registra um cartão de credito ou debito para um motorista ja cadastrado, conforme exemplo abaixo.")
    public ResponseEntity<Cartao> save(@RequestBody Cartao cartao) {
        cartao = cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(cartao);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cartão de credito ou debito por ID", description = "Atualiza dados do cartão de credito ou debito de um motorista com base no seu id, conforme exemplo abaixo.")
    public ResponseEntity<CartaoDTO> update(@PathVariable Long id, @RequestBody CartaoDTO cartaoDTO) {
        return ResponseEntity.ok(cartaoService.update(id, cartaoDTO));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Apagar dados do cartão por ID", description = "Apaga os dados do cartão de credito ou debito com base no seu ID, conforme exemplo abaixo.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
