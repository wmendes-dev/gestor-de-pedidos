package br.com.pagamentos_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class AnalisePagamentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalisePagamentoApplication.class, args);
    }

}
