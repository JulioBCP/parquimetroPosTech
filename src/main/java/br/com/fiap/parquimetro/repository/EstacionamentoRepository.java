package br.com.fiap.parquimetro.repository;

import br.com.fiap.parquimetro.entities.Estacionamento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {

    @Query("select p from Estacionamento p where p.horarioSaida is null")
    List<Estacionamento> findPagamentosNulos();

    @Modifying
    @Transactional
    @Query("update Estacionamento p set p.flagAlerta=1 where p.id=(:id)")
    void setarAlerta(Long id);

    @Modifying
    @Transactional
    @Query("update Estacionamento p set p.tempoEmHoras=:tempo where p.id=:id")
    void aumentarTempo(Long id, Long tempo);
}
