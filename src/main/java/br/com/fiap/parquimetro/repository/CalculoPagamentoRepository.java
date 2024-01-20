package br.com.fiap.parquimetro.repository;

import br.com.fiap.parquimetro.entities.pagamento.CalculoPagamento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalculoPagamentoRepository extends JpaRepository<CalculoPagamento, Long> {

    @Query("select p from CalculoPagamento p where p.horarioSaida is null")
    List<CalculoPagamento> findPagamentosNulos();
}
