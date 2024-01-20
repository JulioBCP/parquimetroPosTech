package br.com.fiap.parquimetro.service;

import br.com.fiap.parquimetro.ControllerNotFoundException;
import br.com.fiap.parquimetro.dto.CalculoPagamentoDTO;
import br.com.fiap.parquimetro.entities.pagamento.CalculoPagamento;
import br.com.fiap.parquimetro.repository.CalculoPagamentoRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculoPagamentoService {

    @Autowired
    CalculoPagamentoRepository calculoPagamentoRepository;

    public Collection<CalculoPagamentoDTO> findAll() {
        var calculoPagamentos = calculoPagamentoRepository.findAll();
        return calculoPagamentos
                .stream()
                .map(this::toCalculoPagamentoDTO)
                .collect(Collectors.toList());
    }

    public CalculoPagamentoDTO findById(Long id) {
        var calculoPagamento =
                calculoPagamentoRepository.findById(id).orElseThrow(
                        () -> new ControllerNotFoundException("Não foi possivel encontrar o valor para pagamento!!!!"));
        return toCalculoPagamentoDTO(calculoPagamento);
    }

    public CalculoPagamentoDTO saveDadosEntrada(CalculoPagamentoDTO calculoPagamentoDTO) {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        CalculoPagamentoDTO calculoPagamentoDTOAtualizado = calculoPagamentoDTO.withHorarioEntrada(dataHoraAtual);
        CalculoPagamento calculoPagamento = toCalculoPagamento(calculoPagamentoDTOAtualizado);

        calculoPagamento = calculoPagamentoRepository.save(calculoPagamento);
        return toCalculoPagamentoDTO(calculoPagamento);
    }

    public CalculoPagamentoDTO saveDadosSaida(Long id, CalculoPagamentoDTO calculoPagamentoDTO) {
        try {
            CalculoPagamento calculoPagamento = calculoPagamentoRepository.getReferenceById(id);

            LocalDateTime dataHoraAtual = LocalDateTime.now();
            CalculoPagamentoDTO calculoPagamentoDTOAtualizado = calculoPagamentoDTO.withHorarioSaida(dataHoraAtual);

//            calculoPagamento.setHorarioEntrada(calculoPagamento.getHorarioEntrada());
//            TODO: retornar o codigo da linha acima e apagar o da linha abaixo
            calculoPagamento.setHorarioEntrada(calculoPagamentoDTO.horarioEntrada());
            calculoPagamento.setHorarioSaida(calculoPagamentoDTOAtualizado.horarioSaida());
            calculoPagamento.setCarro(calculoPagamento.getCarro());
            calculoPagamento.setModalidadeTempoEnum(calculoPagamento.getModalidadeTempoEnum());
            calculoPagamento.setTempoEmHoras(calculoTempo(calculoPagamento.getHorarioEntrada(), calculoPagamento.getHorarioSaida()));


            calculoPagamento = calculoPagamentoRepository.save(calculoPagamento);
            return toCalculoPagamentoDTO(calculoPagamento);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não foi possivel atualizar os dados de saida do veiculo!!!!!!!!");
        }
    }

    public CalculoPagamentoDTO update(Long id, CalculoPagamentoDTO calculoPagamentoDTO) {
        try {
            CalculoPagamento calculoPagamento = calculoPagamentoRepository.getReferenceById(id);
            calculoPagamento.setHorarioEntrada(calculoPagamentoDTO.horarioEntrada());
            calculoPagamento.setHorarioSaida(calculoPagamentoDTO.horarioSaida());
            calculoPagamento.setCarro(calculoPagamentoDTO.carro());
            calculoPagamento.setModalidadeTempoEnum(calculoPagamentoDTO.modalidadeTempoEnum());
            calculoPagamento = calculoPagamentoRepository.save(calculoPagamento);

            return toCalculoPagamentoDTO(calculoPagamento);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não foi possivel atualizar o valor para pagamento!!!!!!!!");
        }
    }

    public void delete(Long id) {

        calculoPagamentoRepository.deleteById(id);
    }

    private CalculoPagamentoDTO toCalculoPagamentoDTO(CalculoPagamento calculoPagamento) {
        return new CalculoPagamentoDTO(
                calculoPagamento.getId(),
                calculoPagamento.getHorarioEntrada(),
                calculoPagamento.getHorarioSaida(),
                calculoPagamento.getTempoEmHoras(),
                calculoPagamento.getCarro(),
                calculoPagamento.getModalidadeTempoEnum()
        );
    }

    private CalculoPagamento toCalculoPagamento(CalculoPagamentoDTO calculoPagamentoDTO) {
        return new CalculoPagamento(
                calculoPagamentoDTO.id(),
                calculoPagamentoDTO.horarioEntrada(),
                calculoPagamentoDTO.horarioSaida(),
                calculoPagamentoDTO.carro(),
                calculoPagamentoDTO.modalidadeTempoEnum()
        );
    }

    private long calculoTempo(LocalDateTime horarioEntrada, LocalDateTime horarioSaida) {
        return Duration.between(horarioEntrada, horarioSaida).toHours();
    }

    public List<CalculoPagamento> buscaEmAberto() {
        return calculoPagamentoRepository.findPagamentosNulos();
    }

    public void setarAlerta(Long id) {
        calculoPagamentoRepository.setarAlerta(id);
    }
}
