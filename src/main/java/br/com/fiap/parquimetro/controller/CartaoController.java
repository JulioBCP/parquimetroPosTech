package br.com.fiap.parquimetro.controller;

import br.com.fiap.parquimetro.entities.pagamento.Cartao;
import br.com.fiap.parquimetro.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<Cartao> save(@RequestBody Cartao cartao) {
        cartao = cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(cartao);
    }

}
