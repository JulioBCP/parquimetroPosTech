package br.com.fiap.parquimetro.controller;

import br.com.fiap.parquimetro.dto.CarroDTO;
import br.com.fiap.parquimetro.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Listar todos os veiculos", description = "Lista todos os veiculos registrados neste parquimetro.")
    public ResponseEntity<Collection<CarroDTO>> findAll(){
        var carros = service.findAll();
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter os dados de um veiculo por ID", description = "Obtem os dados de um veiculo com base no ID fornecido.")
    public ResponseEntity<CarroDTO> findById(@PathVariable Long id) {
        var pessoa = service.findById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    @Operation(summary = "Registra um veiculo", description = "Registra um veiculo para um motorista ja cadastrado, conforme exemplo abaixo.")
    public ResponseEntity<CarroDTO> save(@RequestBody CarroDTO carroDTO){
        carroDTO = service.save(carroDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(carroDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar veiculo por ID", description = "Atualiza dados de um veiculo com base no ID e motorista, conforme exemplo abaixo.")
    public ResponseEntity<CarroDTO> update(
            @PathVariable Long id,
            @RequestBody CarroDTO carroDTO) {

        carroDTO = service.update(id,carroDTO);

        return ResponseEntity.ok(carroDTO);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Apagar dados de um veiculo por ID", description = "Apaga o registro de um veiculo com base no seu ID, desvinculando-o do seu motorista.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
