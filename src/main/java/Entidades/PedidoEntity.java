package Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "Pedido")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idUsuario;
    private int idPacote;
    private int quantidadeIngressos;
    private double valorTotal;
    private StatusPagamento statusPagamento;
    private LocalDate dataCompra;

    public PedidoEntity(int id, int idUsuario, int idPacote, int quantidadeIngressos, double valorTotal, StatusPagamento statusPagamento, LocalDate dataCompra) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPacote = idPacote;
        this.quantidadeIngressos = quantidadeIngressos;
        this.valorTotal = valorTotal;
        this.statusPagamento = statusPagamento;
        this.dataCompra = dataCompra;
    }

    public PedidoEntity(){}

    @Override
    public String toString() {
        return "PedidoEntity{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", idPacote=" + idPacote +
                ", quantidadeIngressos=" + quantidadeIngressos +
                ", valorTotal=" + valorTotal +
                ", statusPagamento=" + statusPagamento +
                ", dataCompra=" + dataCompra +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPacote() {
        return idPacote;
    }

    public void setIdPacote(int idPacote) {
        this.idPacote = idPacote;
    }

    public int getQuantidadeIngressos() {
        return quantidadeIngressos;
    }

    public void setQuantidadeIngressos(int quantidadeIngressos) {
        this.quantidadeIngressos = quantidadeIngressos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }
}


