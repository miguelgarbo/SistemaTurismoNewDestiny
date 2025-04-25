package Entidades;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "servico_categorias")
public class ServicoCategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A categoria é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaEntity categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passeio_id")
    private PasseioEntity passeio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servico_id")
    private ServicoEntity servico;

    // Construtores
    public ServicoCategoriaEntity() {
    }

    public ServicoCategoriaEntity(CategoriaEntity categoria, PasseioEntity passeio) {
        this.categoria = categoria;
        this.passeio = passeio;
    }

    public ServicoCategoriaEntity(CategoriaEntity categoria, ServicoEntity servico) {
        this.categoria = categoria;
        this.servico = servico;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public PasseioEntity getPasseio() {
        return passeio;
    }

    public void setPasseio(PasseioEntity passeio) {
        this.passeio = passeio;
    }

    public ServicoEntity getServico() {
        return servico;
    }

    public void setServico(ServicoEntity servico) {
        this.servico = servico;
    }

    @Override
    public String toString() {
        return "ServicoCategoriaEntity{" +
                "id=" + id +
                ", categoria=" + categoria +
                ", passeio=" + passeio +
                ", servico=" + servico +
                '}';
    }
} 