package Entidades;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "Pagamento")

public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idPedido;
    private String metodoPagamento;
    private StatusPagamento statusPagamento;
    private LocalDate data;

    public PagamentoEntity(int id, int idPedido, String metodoPagamento, StatusPagamento statusPagamento, LocalDate data) {
        this.id = id;
        this.idPedido = idPedido;
        this.metodoPagamento = metodoPagamento;
        this.statusPagamento = statusPagamento;
        this.data = data;
    }

    public PagamentoEntity(){}

    @Override
    public String toString() {
        return "PagamentoEntity{" +
                "id=" + id +
                ", idPedido=" + idPedido +
                ", metodoPagamento='" + metodoPagamento + '\'' +
                ", statusPagamento=" + statusPagamento +
                ", data=" + data +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
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
