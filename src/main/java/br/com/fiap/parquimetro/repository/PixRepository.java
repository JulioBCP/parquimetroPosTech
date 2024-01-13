package br.com.fiap.parquimetro.repository;

import br.com.fiap.parquimetro.entities.pagamento.Pix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PixRepository extends JpaRepository<Pix, Long> {
}
