package br.com.fiap.parquimetro.controller;

import br.com.fiap.parquimetro.dto.CarroDTO;
import br.com.fiap.parquimetro.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/carros")
public class CarroController {
    @Autowired
    private CarroService service;

    @GetMapping
    public ResponseEntity<Collection<CarroDTO>> findAll(){
        var carros = service.findAll();
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> findById(@PathVariable Long id) {
        var pessoa = service.findById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<CarroDTO> save(@RequestBody CarroDTO carroDTO){
        carroDTO = service.save(carroDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(carroDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarroDTO> update(
            @PathVariable Long id,
            @RequestBody CarroDTO carroDTO) {

        carroDTO = service.update(id,carroDTO);

        return ResponseEntity.ok(carroDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
