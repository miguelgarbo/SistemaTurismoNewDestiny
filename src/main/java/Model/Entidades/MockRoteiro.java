package Model.Entidades;

import java.time.LocalDate;
import java.util.List;

public class MockRoteiro {
    private String nome;
    private LocalDate dataInicio;
    private String descricao;
    private List<String> passeiosInclusos; // Mock de passeios inclusos

    public MockRoteiro(String nome, LocalDate dataInicio, String descricao, List<String> passeiosInclusos) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.descricao = descricao;
        this.passeiosInclusos = passeiosInclusos;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<String> getPasseiosInclusos() {
        return passeiosInclusos;
    }
} 