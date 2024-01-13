package br.com.fiap.parquimetro.repository;

import br.com.fiap.parquimetro.entities.pagamento.PagamentoCompleto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoCompletoRepository extends JpaRepository<PagamentoCompleto, Long> {
}
