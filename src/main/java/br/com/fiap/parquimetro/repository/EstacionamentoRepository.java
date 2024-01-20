package br.com.fiap.parquimetro.repository;

import br.com.fiap.parquimetro.entities.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {
}
