package br.com.fiap.parquimetro.service;

import br.com.fiap.parquimetro.ControllerNotFoundException;
import br.com.fiap.parquimetro.dto.CartaoDTO;
import br.com.fiap.parquimetro.dto.PessoaDTO;
import br.com.fiap.parquimetro.entities.Pessoa;
import br.com.fiap.parquimetro.entities.pagamento.Cartao;
import br.com.fiap.parquimetro.repository.CartaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Autowired
    CartaoRepository cartaoRepository;

    public CartaoDTO findById(Long id) {
        var cartao =
                cartaoRepository.findById(id).orElseThrow(
                        () -> new ControllerNotFoundException("Cartão não encontrado!!!!"));
        return toCartaoDTO(cartao);
    }

    public Cartao save(Cartao cartao) {
        return cartaoRepository.save(cartao);
    }

    public CartaoDTO update(Long id, CartaoDTO cartaoDTO) {
        try {
            Cartao buscaCartao = cartaoRepository.getReferenceById(id);
            buscaCartao.setNumeroCartao(cartaoDTO.numeroCartao());
            buscaCartao.setNomeNoCartao(cartaoDTO.nomeNoCartao());
            buscaCartao.setDataValidade(cartaoDTO.dataValidade());
            buscaCartao.setCcv(cartaoDTO.ccv());
            buscaCartao = cartaoRepository.save(buscaCartao);

            return toCartaoDTO(buscaCartao);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Cartão não encontrado!!!");
        }
    }

    public  void delete(Long id) {
        cartaoRepository.deleteById(id);
    }

    private CartaoDTO toCartaoDTO(Cartao cartao) {
        return new CartaoDTO(
                cartao.getId(),
                cartao.getNumeroCartao(),
                cartao.getNomeNoCartao(),
                cartao.getDataValidade(),
                cartao.getCcv()
        );
    }

    private Cartao toCartao(CartaoDTO cartaoDTO) {
        return new Cartao(
                cartaoDTO.id(),
                cartaoDTO.numeroCartao(),
                cartaoDTO.nomeNoCartao(),
                cartaoDTO.dataValidade(),
                cartaoDTO.ccv()
        );
    }

}
