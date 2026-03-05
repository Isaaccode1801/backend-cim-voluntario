package com.clinic.pacientes.repository;

import com.clinic.pacientes.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Qual a Classe, Qual o tipo do ID (Long)>
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Só com esta linha, já ganhamos os poderes de salvar, deletar, buscar, etc!
}