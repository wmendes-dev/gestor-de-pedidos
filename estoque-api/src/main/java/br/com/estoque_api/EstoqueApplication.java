package br.com.estoque_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EstoqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstoqueApplication.class, args);
    }

}