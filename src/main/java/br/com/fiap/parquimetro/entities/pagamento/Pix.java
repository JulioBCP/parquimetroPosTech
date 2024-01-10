package br.com.fiap.parquimetro.entities.pagamento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Pix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chavePix;

    public Pix() {
    }

    public Pix(Long id, String chavePix) {
        this.id = id;
        this.chavePix = chavePix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pix)) return false;
        Pix pix = (Pix) o;
        return Objects.equals(id, pix.id) && Objects.equals(chavePix, pix.chavePix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chavePix);
    }

    @Override
    public String toString() {
        return "Pix{" +
                "id=" + id +
                ", chavePix='" + chavePix + '\'' +
                '}';
    }
}
