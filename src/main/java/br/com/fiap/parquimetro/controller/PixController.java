package br.com.fiap.parquimetro.controller;

import br.com.fiap.parquimetro.entities.pagamento.Pix;
import br.com.fiap.parquimetro.service.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pix")
public class PixController {

    @Autowired
    private PixService pixService;

    @PostMapping
    public ResponseEntity<Pix> save(@RequestBody Pix pix) {
        pix = pixService.save(pix);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(pix);
    }
}
