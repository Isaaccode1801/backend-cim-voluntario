package com.clinic.pacientes.controller;

import com.clinic.pacientes.model.Paciente;
import com.clinic.pacientes.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Diz que esta classe é um Rececionista da web
@RequestMapping("/api/pacientes") // O endereço que o React vai chamar
@CrossOrigin(origins = "*") // Liberta o acesso para o React não ser bloqueado
public class PacienteController {

    @Autowired // Chama o nosso "Bibliotecário"
    private PacienteRepository repository;

    // Rota 1: Buscar a lista de todos os pacientes (Para a tua Tela 1)
    @GetMapping
    public List<Paciente> listarTodos() {
        return repository.findAll();
    }

    // Rota 2: Salvar um novo paciente
    @PostMapping
    public Paciente salvar(@RequestBody Paciente paciente) {
        // Garante que a relação entre Paciente e Anexo seja estabelecida
        if (paciente.getAnexos() != null) {
            paciente.getAnexos().forEach(anexo -> anexo.setPaciente(paciente));
        }
        return repository.save(paciente);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}