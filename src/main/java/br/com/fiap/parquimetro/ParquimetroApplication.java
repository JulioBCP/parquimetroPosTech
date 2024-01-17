package br.com.fiap.parquimetro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class ParquimetroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParquimetroApplication.class, args);

		Timer timer = new Timer();

		final long SEGUNDOS = 1000;
		TimerTask tarefa = new TimerTask() {
			@Override
			public void run() {
				System.out.println(new Date());
			}
		};

		timer.scheduleAtFixedRate(tarefa, 0, SEGUNDOS);
	}

}
