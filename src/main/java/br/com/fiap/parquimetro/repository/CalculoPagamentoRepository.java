package br.com.fiap.parquimetro.repository;

import br.com.fiap.parquimetro.entities.pagamento.CalculoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculoPagamentoRepository extends JpaRepository<CalculoPagamento, Long> {
}
