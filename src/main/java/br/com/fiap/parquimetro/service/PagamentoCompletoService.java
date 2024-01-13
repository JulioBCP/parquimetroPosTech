package br.com.fiap.parquimetro.service;

import br.com.fiap.parquimetro.ControllerNotFoundException;
import br.com.fiap.parquimetro.dto.CalculoPagamentoDTO;
import br.com.fiap.parquimetro.dto.PagamentoCompletoDTO;
import br.com.fiap.parquimetro.dto.PagamentoDTO;
import br.com.fiap.parquimetro.entities.pagamento.CalculoPagamento;
import br.com.fiap.parquimetro.entities.pagamento.Pagamento;
import br.com.fiap.parquimetro.entities.pagamento.PagamentoCompleto;
import br.com.fiap.parquimetro.repository.CalculoPagamentoRepository;
import br.com.fiap.parquimetro.repository.PagamentoCompletoRepository;
import br.com.fiap.parquimetro.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PagamentoCompletoService {

    @Autowired
    PagamentoCompletoRepository pagamentoCompletoRepository;
    @Autowired
    CalculoPagamentoRepository calculoPagamentoRepository;
    @Autowired
    PagamentoRepository pagamentoRepository;

    public Collection<PagamentoCompletoDTO> findAll() {
        var pagamentosCompleto = pagamentoCompletoRepository.findAll();
        return pagamentosCompleto
                .stream()
                .map(this::toPagamentoCompletoDTO)
                .collect(Collectors.toList());
    }

    public PagamentoCompletoDTO findById(Long id) {
        var pagamentoCompleto = pagamentoCompletoRepository.findById(id).orElseThrow(
                () -> new ControllerNotFoundException("Não foi possivel encontrar o pagamento!!!!"));
        return toPagamentoCompletoDTO(pagamentoCompleto);
    }

    public PagamentoCompletoDTO savePagamento(PagamentoCompletoDTO pagamentoCompletoDTO) {
        try {
            Pagamento pagamento = pagamentoRepository.getReferenceById(pagamentoCompletoDTO.pagamento().getId());
            pagamento.setId(pagamento.getId());
            pagamento.setFormaDePagamentoEnum(pagamento.getFormaDePagamentoEnum());
            pagamento.setPix(pagamento.getPix());
            pagamento.setCartao(pagamento.getCartao());
            pagamentoRepository.save(pagamento);

            CalculoPagamento calculoPagamento = calculoPagamentoRepository.getReferenceById(
                    pagamentoCompletoDTO.calculoPagamento().getId());
            LocalDateTime dataHoraAtual = LocalDateTime.now();
//            calculoPagamento.setHorarioEntrada(calculoPagamento.getHorarioEntrada());
//            TODO: retornar o codigo da linha acima e apagar o da linha abaixo
            calculoPagamento.setHorarioEntrada(pagamentoCompletoDTO.calculoPagamento().getHorarioEntrada());
            calculoPagamento.setHorarioSaida(dataHoraAtual);
            calculoPagamento.setCarro(calculoPagamento.getCarro());
            calculoPagamento.setModalidadeTempoEnum(calculoPagamento.getModalidadeTempoEnum());
            calculoPagamento.setTempoEmHoras(calculoTempo(calculoPagamento.getHorarioEntrada(),
                                                                        calculoPagamento.getHorarioSaida()));
            calculoPagamentoRepository.save(calculoPagamento);

            var pagamentoCompleto = new PagamentoCompleto(pagamentoCompletoDTO.id(), pagamento, calculoPagamento);

            pagamentoCompleto = pagamentoCompletoRepository.save(pagamentoCompleto);
            return toPagamentoCompletoDTO(pagamentoCompleto);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não foi possivel salvar os dados de pagamento!!!!!!!!");
        }
    }

    private PagamentoCompletoDTO toPagamentoCompletoDTO(PagamentoCompleto pagamentoCompleto) {
        return new PagamentoCompletoDTO(
                pagamentoCompleto.getId(),
                pagamentoCompleto.getPagamento(),
                pagamentoCompleto.getCalculoPagamento()
        );
    }

    private PagamentoCompleto toPagamentoCompleto(PagamentoCompletoDTO pagamentoCompletoDTO) {
        return new PagamentoCompleto(
                pagamentoCompletoDTO.id(),
                pagamentoCompletoDTO.pagamento(),
                pagamentoCompletoDTO.calculoPagamento()
        );
    }

    private long calculoTempo(LocalDateTime horarioEntrada, LocalDateTime horarioSaida) {
        return Duration.between(horarioEntrada, horarioSaida).toHours();
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

    private PagamentoDTO toPagamentoDTO(Pagamento pagamento) {
        return new PagamentoDTO(
                pagamento.getId(),
                pagamento.getFormaDePagamentoEnum(),
                pagamento.getPix(),
                pagamento.getCartao()
        );
    }
}
