package Entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "categoria")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @ManyToMany(mappedBy = "categorias")
    private List<PasseioEntity> passeios = new ArrayList<>();

    @ManyToMany(mappedBy = "categorias")
    private List<PacoteTuristicoEntity> pacoteTuristico;

    public CategoriaEntity( String nome, String descricao, List<PasseioEntity> passeios, List<PacoteTuristicoEntity> pacoteTuristico) {
        this.nome = nome;
        this.descricao = descricao;
        this.passeios = passeios;
        this.pacoteTuristico = pacoteTuristico;
    }

    public CategoriaEntity(){}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<PasseioEntity> getPasseios() {
        return passeios;
    }

    public void setPasseios(List<PasseioEntity> passeios) {
        this.passeios = passeios;
    }

    public List<PacoteTuristicoEntity> getPacoteTuristico() {
        return pacoteTuristico;
    }

    public void setPacoteTuristico(List<PacoteTuristicoEntity> pacoteTuristico) {
        this.pacoteTuristico = pacoteTuristico;
    }
}