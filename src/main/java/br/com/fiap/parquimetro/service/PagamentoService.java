package br.com.fiap.parquimetro.service;

import br.com.fiap.parquimetro.ControllerNotFoundException;
import br.com.fiap.parquimetro.dto.PagamentoDTO;
import br.com.fiap.parquimetro.entities.pagamento.Pagamento;
import br.com.fiap.parquimetro.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    @Autowired
    PagamentoRepository pagamentoRepository;

    public Collection<PagamentoDTO> findAll() {
        var pagamentos = pagamentoRepository.findAll();
        return pagamentos
                .stream()
                .map(this::toPagamentoDTO)
                .collect(Collectors.toList());
    }

    public PagamentoDTO findById(Long id) {
        var pagamento =
                pagamentoRepository.findById(id).orElseThrow(
                        () -> new ControllerNotFoundException("Pagamento não Encontrado!!!!"));
                return toPagamentoDTO(pagamento);
    }

    public PagamentoDTO save(PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = toPagamento(pagamentoDTO);
        pagamento = pagamentoRepository.save(pagamento);
        return toPagamentoDTO(pagamento);
    }

    public PagamentoDTO update(Long id, PagamentoDTO pagamentoDTO) {
        try {
            Pagamento pagamento = pagamentoRepository.getReferenceById(id);
            pagamento.setFormaDePagamentoEnum(pagamentoDTO.formaPagmentoEnum());
            pagamento.setCartao(pagamentoDTO.cartao());
            pagamento.setPix(pagamentoDTO.pix());
            pagamento = pagamentoRepository.save(pagamento);

            return toPagamentoDTO(pagamento);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Pagamento não Encontrado!!!!!!!!");
        }
    }

    public void delete(Long id) {
        pagamentoRepository.deleteById(id);
    }

    private PagamentoDTO toPagamentoDTO(Pagamento pagamento) {
        return new PagamentoDTO(
                pagamento.getId(),
                pagamento.getFormaDePagamentoEnum(),
                pagamento.getPix(),
                pagamento.getCartao()
        );
    }

    private Pagamento toPagamento(PagamentoDTO pagamentoDTO) {
        return new Pagamento(
                pagamentoDTO.id(),
                pagamentoDTO.formaPagmentoEnum(),
                pagamentoDTO.pix(),
                pagamentoDTO.cartao()
        );
    }

}
