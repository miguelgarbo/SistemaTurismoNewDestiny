package Model.Entidades;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Pagamento")

public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pedido", nullable = false, unique = true)
    private PedidoEntity pedido;

    private String metodoPagamento;

    private StatusPagamento statusPagamento;
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    public PagamentoEntity(Long id, PedidoEntity pedido, String metodoPagamento, StatusPagamento statusPagamento, LocalDate data, UsuarioEntity usuario) {
        this.id = id;
        this.pedido = pedido;
        this.metodoPagamento = metodoPagamento;
        this.statusPagamento = statusPagamento;
        this.data = data;
        this.usuario = usuario;
    }

    public PagamentoEntity(){}



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}
