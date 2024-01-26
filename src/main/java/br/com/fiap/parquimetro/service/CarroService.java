package br.com.fiap.parquimetro.service;

import br.com.fiap.parquimetro.ControllerNotFoundException;
import br.com.fiap.parquimetro.dto.CarroDTO;
import br.com.fiap.parquimetro.entities.Carro;
import br.com.fiap.parquimetro.repository.CarroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CarroService {
    @Autowired
    private CarroRepository repo;

    public Collection<CarroDTO> findAll() {
        var carros = repo.findAll();
        return carros
                .stream()
                .map(this::toCarroDTO)        //Transforma cada carro em CarroDTO
                .collect(Collectors.toList());  //Devolve a Lista
    }

    public CarroDTO findById(Long id) {
        var carro =
                repo.findById(id).orElseThrow(
                        () -> new ControllerNotFoundException("Carro não Encontrada !!!!")                );
        return toCarroDTO(carro);
    }

    public CarroDTO save(CarroDTO carroDTO) {
        Carro carro = toCarro(carroDTO);
        carro = repo.save(carro);
        return toCarroDTO(carro);
    }

    public CarroDTO update(Long id, CarroDTO carroDTO) {
        try {
            Carro buscaCarro = repo.getReferenceById(id);
            buscaCarro.setPlaca(carroDTO.placa());
            buscaCarro.setPessoa(carroDTO.pessoa());
            buscaCarro = repo.save(buscaCarro);

            return toCarroDTO(buscaCarro);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Carro não Encontrado !!!!!!!!");
        }
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private CarroDTO toCarroDTO(Carro carro) {
        return new CarroDTO(
                carro.getId(),
                carro.getPlaca(),
                carro.getPessoa()
        );
    }

    private Carro toCarro(CarroDTO carroDTO) {
        return new Carro(
                carroDTO.id(),
                carroDTO.placa(),
                carroDTO.pessoa()
        );
    }
}
