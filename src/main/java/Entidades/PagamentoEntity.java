package Entidades;

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

    public PagamentoEntity(PedidoEntity pedido, String metodoPagamento, StatusPagamento statusPagamento, LocalDate data) {
        this.pedido = pedido;
        this.metodoPagamento = metodoPagamento;
        this.statusPagamento = statusPagamento;
        this.data = data;
    }

    public PagamentoEntity(){}

    @Override
    public String toString() {
        return "PagamentoEntity{" +
                "id=" + id +
                ", pedido=" + pedido +
                ", metodoPagamento='" + metodoPagamento + '\'' +
                ", statusPagamento=" + statusPagamento +
                ", data=" + data +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoEntity getpedido() {
        return pedido;
    }

    public void setpedido(PedidoEntity pedido) {
        this.pedido = pedido;
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
}
