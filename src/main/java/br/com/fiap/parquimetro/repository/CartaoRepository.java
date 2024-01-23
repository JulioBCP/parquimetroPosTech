package br.com.fiap.parquimetro.repository;

import br.com.fiap.parquimetro.entities.pagamento.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
