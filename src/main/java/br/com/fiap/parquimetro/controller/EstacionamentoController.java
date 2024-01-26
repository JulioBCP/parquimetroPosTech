package br.com.fiap.parquimetro.controller;

import br.com.fiap.parquimetro.dto.EstacionamentoDTO;
import br.com.fiap.parquimetro.service.EstacionamentoService;
import br.com.fiap.parquimetro.util.ErroResponse;
import br.com.fiap.parquimetro.util.PagamentoNaoRealizadoException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/estacionamentos")
public class EstacionamentoController {

    @Autowired
    private EstacionamentoService estacionamentoService;

    @GetMapping
    @Operation(summary = "Listar todos os estacionamentos", description = "Lista todos os estacionamentos registrados neste parquimetro.")
    public ResponseEntity<Collection<EstacionamentoDTO>> findAll() {
        return ResponseEntity.ok(estacionamentoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter os dados de um estacionamento por ID", description = "Obtem os dados de um estacionamento com base no ID fornecido.")
    public ResponseEntity<EstacionamentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(estacionamentoService.findById(id));
    }

    @PostMapping("/entrada")
    @Operation(summary = "Registra o inicio do estacionamento", description = "Registra a hora inicial do estacionamento passando os dados do veiculo e motorista conforme exemplo abaixo.")
    public ResponseEntity<EstacionamentoDTO> salvarInicioEstacionamento(@RequestBody EstacionamentoDTO estacionamentoDTO) {
        EstacionamentoDTO retornoEstacionamentoService = estacionamentoService.salvarInicioEstacionamento(estacionamentoDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(retornoEstacionamentoService);
    }

    @PutMapping("/saida/{id}")
    @Operation(summary = "Atualizar estacionamento por ID", description = "Registra finalizacao do tempo de estacionamento assim como o seu pagamento com base no ID e no corpo da requisicao conforme abaixo.")
    public ResponseEntity<?> salvarFimEstacionamento(@PathVariable Long id,
                                                                     @RequestBody EstacionamentoDTO estacionamentoDTO) {
        try {
            EstacionamentoDTO retornoEstacionamentoService= estacionamentoService.salvarFimEstacionamento(id, estacionamentoDTO);
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(retornoEstacionamentoService);
        } catch (PagamentoNaoRealizadoException pe) {
            ErroResponse erroResponse = new ErroResponse(pe.getMessage(), HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResponse);
        } catch (EntityNotFoundException e) {
            ErroResponse erroResponse = new ErroResponse("Pagamento não realizado", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResponse);
        }
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Apagar dados por ID", description = "Apaga os dados do estacionamento com base no seu ID.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estacionamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
