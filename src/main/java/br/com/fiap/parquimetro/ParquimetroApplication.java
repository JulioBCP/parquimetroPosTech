package br.com.fiap.parquimetro;

import br.com.fiap.parquimetro.entities.pagamento.CalculoPagamento;
import br.com.fiap.parquimetro.entities.pagamento.ModalidadeTempoEnum;
import br.com.fiap.parquimetro.service.CalculoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
				if (listaAberto != null && !listaAberto.isEmpty() ) {
					for (CalculoPagamento batida : listaAberto) {
						if (batida.getModalidadeTempoEnum()
								.equals(ModalidadeTempoEnum.TEMPO_FIXO)) {
							emiteTempoFixo(batida);
						} else {
							emiteTempoVarivel(batida);
						}
					}
				}
			}

			private void emiteTempoFixo(CalculoPagamento batida) {
				LocalDateTime tempoAtual = LocalDateTime.now();
				LocalDateTime tempoBatida = batida.getHorarioEntrada();

				//Só testa de data mes e ano forem iguais
				if (tempoAtual.getDayOfMonth() == tempoBatida.getDayOfMonth()
					&& tempoAtual.getMonth() == tempoBatida.getMonth()
						&& tempoAtual.getYear() == tempoBatida.getYear()) {

					Long minutosBatida = (batida.getTempoEmHoras() * 60)
							+ (tempoBatida.getHour() * 60)
							+ tempoBatida.getMinute();

					Long minutosAtual = (long) (tempoAtual.getHour() * 60
                                                + tempoAtual.getMinute());

					if (minutosBatida - minutosAtual <= 15
							&& batida.getFlagAlerta()==0) {
						System.out.println("Tempo está expirando - email enviado para : "
								+ batida.getCarro().getPessoa().getEmail());
						calculoPagamentoService.setarAlerta(batida.getId());
					}
				}
			}

			private void emiteTempoVarivel(CalculoPagamento batida) {

			}
		};
		timer.scheduleAtFixedRate(tarefa, 0, SEGUNDOS);
	}


}
