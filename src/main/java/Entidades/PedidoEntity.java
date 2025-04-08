package Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "Pedido")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUsuario;
    private Long idPacote;
    private Long quantidadeIngressos;
    private BigDecimal valorTotal;
    private StatusPagamento statusPagamento;
    private LocalDate dataCompra;

    public PedidoEntity(Long idUsuario, Long idPacote, Long quantidadeIngressos, BigDecimal valorTotal, StatusPagamento statusPagamento, LocalDate dataCompra) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdPacote() {
        return idPacote;
    }

    public void setIdPacote(Long idPacote) {
        this.idPacote = idPacote;
    }

    public Long getQuantidadeIngressos() {
        return quantidadeIngressos;
    }

    public void setQuantidadeIngressos(Long quantidadeIngressos) {
        this.quantidadeIngressos = quantidadeIngressos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
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


