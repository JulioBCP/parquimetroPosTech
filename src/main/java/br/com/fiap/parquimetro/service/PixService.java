package br.com.fiap.parquimetro.service;

import br.com.fiap.parquimetro.entities.pagamento.Pix;
import br.com.fiap.parquimetro.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PixService {

    @Autowired
    PixRepository pixRepository;

    public Pix save(Pix pix) {
        return pixRepository.save(pix);
    }

}
