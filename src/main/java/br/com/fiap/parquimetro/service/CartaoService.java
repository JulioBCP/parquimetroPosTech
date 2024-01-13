package br.com.fiap.parquimetro.service;

import br.com.fiap.parquimetro.entities.pagamento.Cartao;
import br.com.fiap.parquimetro.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Autowired
    CartaoRepository cartaoRepository;

    public Cartao save(Cartao cartao) {

        return cartaoRepository.save(cartao);
    }

}
