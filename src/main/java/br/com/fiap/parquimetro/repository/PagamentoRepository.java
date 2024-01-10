package br.com.fiap.parquimetro.repository;

import br.com.fiap.parquimetro.entities.pagamento.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
