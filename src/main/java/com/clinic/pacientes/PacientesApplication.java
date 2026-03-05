package com.clinic.pacientes;

import com.clinic.pacientes.model.Paciente;
import com.clinic.pacientes.repository.PacienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class PacientesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PacientesApplication.class, args);
    }

    // Este código roda automaticamente assim que o servidor liga!
    @Bean
    CommandLineRunner initDatabase(PacienteRepository repository) {
        return args -> {
            // 1. Criamos um paciente de teste
            Paciente p = new Paciente();
            p.setNome("João da Silva");
            p.setDataNascimento(LocalDate.of(1985, 5, 20)); // Ano, Mês, Dia
            p.setGenero("Masculino");
            p.setContato("99999-1111");
            p.setCpf("000.000.000-00");
            p.setQueixaPrincipal("Dor de cabeça constante há 3 dias.");
            p.setObservacao("Paciente chegou ansioso para a primeira consulta.");

            // 2. Pedimos ao nosso Bibliotecário para o guardar no banco de dados
            repository.save(p);

            System.out.println("✅ Paciente de teste (João) salvo com sucesso no Banco de Dados!");
        };
    }
}