package com.clinic.pacientes.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List; // Adicionado para a lista de anexos
import java.util.ArrayList;

@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ABA 1: Identificação
    private String nome;
    private LocalDate dataNascimento;
    private String genero;
    private String contato;
    private String idade;
    private String endereco;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Anexo> anexos = new ArrayList<>();

    // ABA 2: AP/AF
    @Column(columnDefinition = "TEXT")
    private String antecedentesPessoais;

    @Column(columnDefinition = "TEXT")
    private String fotoPath; // Pode manter para uma foto de perfil principal

    @Column(columnDefinition = "TEXT")
    private String antecedentesFamiliares;

    // ABA 3: QPD/HDA
    @Column(columnDefinition = "TEXT")
    private String queixaPrincipal;
    private String duracao;

    @Column(columnDefinition = "TEXT")
    private String historiaDoencaAtual;

    // ABA 4: Conduta
    @Column(columnDefinition = "TEXT")
    private String conduta;

    // ABA 5: Observação
    @Column(columnDefinition = "TEXT")
    private String observacao;

    public Paciente() {
    }

    // --- GETTERS E SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // NOVO: Getters e Setters para Anexos
    public List<Anexo> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<Anexo> anexos) {
        this.anexos = anexos;
        if (anexos != null) {
            // Garante que cada anexo saiba quem é o seu paciente dono
            anexos.forEach(a -> a.setPaciente(this));
        }
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getAntecedentesPessoais() {
        return antecedentesPessoais;
    }

    public void setAntecedentesPessoais(String antecedentesPessoais) {
        this.antecedentesPessoais = antecedentesPessoais;
    }

    public String getAntecedentesFamiliares() {
        return antecedentesFamiliares;
    }

    public void setAntecedentesFamiliares(String antecedentesFamiliares) {
        this.antecedentesFamiliares = antecedentesFamiliares;
    }

    public String getQueixaPrincipal() {
        return queixaPrincipal;
    }

    public void setQueixaPrincipal(String queixaPrincipal) {
        this.queixaPrincipal = queixaPrincipal;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getHistoriaDoencaAtual() {
        return historiaDoencaAtual;
    }

    public void setHistoriaDoencaAtual(String historiaDoencaAtual) {
        this.historiaDoencaAtual = historiaDoencaAtual;
    }

    public String getFotoPath() {
        return fotoPath;
    }

    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
    }

    public String getConduta() {
        return conduta;
    }

    public void setConduta(String conduta) {
        this.conduta = conduta;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}