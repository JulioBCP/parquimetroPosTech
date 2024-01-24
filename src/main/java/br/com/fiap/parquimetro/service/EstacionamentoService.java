package br.com.fiap.parquimetro.service;

import br.com.fiap.parquimetro.ControllerNotFoundException;
import br.com.fiap.parquimetro.dto.EstacionamentoDTO;
import br.com.fiap.parquimetro.entities.Estacionamento;
import br.com.fiap.parquimetro.entities.pagamento.ModalidadeTempoEnum;
import br.com.fiap.parquimetro.repository.EstacionamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstacionamentoService {

    private static final double VALOR_HORA_FIXO = 7;
    private static final double VALOR_MINUTO_VARIAVEL = (10.0/60.0);

    @Autowired
    EstacionamentoRepository estacionamentoRepository;

    public Collection<EstacionamentoDTO> findAll() {
        var estacionamento = estacionamentoRepository.findAll();
        return estacionamento
                .stream()
                .map(this::toEstacionamentoDTO)
                .collect(Collectors.toList());
    }

    public EstacionamentoDTO findById(Long id) {
        var estacionamento = estacionamentoRepository.findById(id).orElseThrow(
                () -> new ControllerNotFoundException("Não foi possivel encontrar o estacionamento com id: " + id));
        return toEstacionamentoDTO(estacionamento);
    }

    public EstacionamentoDTO salvarInicioEstacionamento(EstacionamentoDTO estacionamentoDTO) {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
//        Duration duracaoASubtrair = Duration.ofMinutes(15);
//        LocalDateTime resultado = dataHoraAtual.minus(duracaoASubtrair);
        EstacionamentoDTO estacionamentoComHorarioEntradaAtual = estacionamentoDTO.comHorarioEntrada(dataHoraAtual);
        Estacionamento estacionamento = toEstacionamento(estacionamentoComHorarioEntradaAtual);

        estacionamentoRepository.save(estacionamento);

        EstacionamentoDTO estacionamentoDTO1 = toEstacionamentoDTO(estacionamento);
        return estacionamentoDTO1;
    }

    public EstacionamentoDTO salvarFimEstacionamento(Long id, EstacionamentoDTO estacionamentoDTO) {
        try {
            Estacionamento estacionamento = estacionamentoRepository.getReferenceById(id);

            LocalDateTime dataHoraAtual = LocalDateTime.now();
            EstacionamentoDTO estacionamentoComHorarioSaidaaAtual = estacionamentoDTO.comHorarioSaida(dataHoraAtual);

            estacionamento.setCarro(estacionamento.getCarro());
            estacionamento.setHorarioEntrada(estacionamento.getHorarioEntrada());
            estacionamento.setHorarioSaida(dataHoraAtual);
            estacionamento.setModalidadeTempoEnum(estacionamento.getModalidadeTempoEnum());
            if (estacionamento.getModalidadeTempoEnum().equals(ModalidadeTempoEnum.TEMPO_FIXO)) {
                estacionamento.setTempoEmHoras(estacionamento.getTempoEmHoras());
            } else {
                estacionamento.setTempoEmHoras(calculoTempo(estacionamento.getHorarioEntrada(), estacionamento.getHorarioSaida()));
            }
            double valor = calculoValorPagamento(estacionamento.getModalidadeTempoEnum(), estacionamento.getTempoEmHoras());
            double valorFormatado = Math.round(valor * 100.0) / 100.0;
            estacionamento.setValorPagamento(valorFormatado);
            estacionamento.setFormaDePagamentoEnum(estacionamentoDTO.formaDePagamentoEnum());
            estacionamento.setChavePix(estacionamentoDTO.chavePix());
            estacionamento.setCartao(estacionamentoDTO.cartao());

            estacionamentoRepository.save(estacionamento);

            return toEstacionamentoDTO(estacionamento);

        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não foi possivel finalizar o estacionamento com id: " + id);
        }
    }

    public void delete(Long id) {
        estacionamentoRepository.deleteById(id);
    }

    private EstacionamentoDTO toEstacionamentoDTO(Estacionamento estacionamento) {
        return new EstacionamentoDTO(
                estacionamento.getId(),
                estacionamento.getCarro(),
                estacionamento.getHorarioEntrada(),
                estacionamento.getHorarioSaida(),
                estacionamento.getModalidadeTempoEnum(),
                estacionamento.getTempoEmHoras(),
                estacionamento.getValorPagamento(),
                estacionamento.getFormaDePagamentoEnum(),
                estacionamento.getChavePix(),
                estacionamento.getCartao()
        );
    }

    private Estacionamento toEstacionamento(EstacionamentoDTO estacionamentoDTO) {
        return new Estacionamento(
                estacionamentoDTO.id(),
                estacionamentoDTO.carro(),
                estacionamentoDTO.horarioEntrada(),
                estacionamentoDTO.horarioSaida(),
                estacionamentoDTO.modalidadeTempoEnum(),
                estacionamentoDTO.tempoEmHoras(),
                estacionamentoDTO.valorPagamento(),
                estacionamentoDTO.formaDePagamentoEnum(),
                estacionamentoDTO.chavePix(),
                estacionamentoDTO.cartao()
        );
    }

    private long calculoTempo(LocalDateTime horarioEntrada, LocalDateTime horarioSaida) {
        return Duration.between(horarioEntrada, horarioSaida).toMinutes();
    }

    private double calculoValorPagamento(ModalidadeTempoEnum modalidadeTempo, long tempoEmHoras) {

        if(modalidadeTempo == ModalidadeTempoEnum.TEMPO_FIXO) {
            return tempoEmHoras * VALOR_HORA_FIXO;
        } else {
            return tempoEmHoras * VALOR_MINUTO_VARIAVEL;
        }
    }

    public List<Estacionamento> buscaEmAberto() {
        return estacionamentoRepository.findPagamentosNulos();
    }

    public void setarAlerta(Estacionamento batida) {
        System.out.println("Tempo está expirando - email enviado para : "
                + batida.getCarro().getPessoa().getEmail());

        estacionamentoRepository.setarAlerta(batida.getId());
    }

    public void aumentarTempo(Estacionamento batida) {
        System.out.println("Tempo será incrementado - email enviado para : "
                + batida.getCarro().getPessoa().getEmail());

        estacionamentoRepository.aumentarTempo(batida.getId(), batida.getTempoEmHoras() + 60);
    }

}
