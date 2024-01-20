package br.com.fiap.parquimetro;

import br.com.fiap.parquimetro.entities.pagamento.CalculoPagamento;
import br.com.fiap.parquimetro.service.CalculoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


@SpringBootApplication
public class ParquimetroApplication implements CommandLineRunner {
	@Autowired
	private CalculoPagamentoService calculoPagamentoService;

	public static void main(String[] args) {
		SpringApplication.run(ParquimetroApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Timer timer = new Timer();

		final long SEGUNDOS = 1000;
		TimerTask tarefa = new TimerTask() {
			@Override
			public void run() {
				List<CalculoPagamento> listaAberto = calculoPagamentoService.buscaEmAberto();
				System.out.println(listaAberto.get(0).getHorarioEntrada());
			}
		};
		timer.scheduleAtFixedRate(tarefa, 0, SEGUNDOS);
	}
}
