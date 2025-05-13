package Entidades;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "Pedido")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario",nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne()
    @JoinColumn(name = "pacoteTuristico")
    private PacoteTuristicoEntity pacoteTuristico;

    @ManyToOne()
    @JoinColumn(name = "passeio")
    private PasseioEntity passeio;

    private Long quantidadeIngressos;
    private BigDecimal valorTotal;
    private StatusPagamento statusPagamento;
    private LocalDate dataCompra;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private PagamentoEntity pagamento;


    public PedidoEntity(Long id, UsuarioEntity usuario, PacoteTuristicoEntity pacoteTuristico, PasseioEntity passeio, Long quantidadeIngressos, BigDecimal valorTotal, StatusPagamento statusPagamento, LocalDate dataCompra, PagamentoEntity pagamento) {
        this.id = id;
        this.usuario = usuario;
        this.pacoteTuristico = pacoteTuristico;
        this.passeio = passeio;
        this.quantidadeIngressos = quantidadeIngressos;
        this.valorTotal = valorTotal;
        this.statusPagamento = statusPagamento;
        this.dataCompra = dataCompra;
        this.pagamento = pagamento;
    }

    public PedidoEntity(){}

    @Override
    public String toString() {
        return "PedidoEntity{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", pacoteTuristico=" + pacoteTuristico +
                ", passeio=" + passeio +
                ", quantidadeIngressos=" + quantidadeIngressos +
                ", valorTotal=" + valorTotal +
                ", statusPagamento=" + statusPagamento +
                ", dataCompra=" + dataCompra +
                ", pagamento=" + pagamento +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioEntity getusuario() {
        return usuario;
    }

    public void setusuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public PacoteTuristicoEntity getpacoteTuristico() {
        return pacoteTuristico;
    }

    public void setpacoteTuristico(PacoteTuristicoEntity pacoteTuristico) {
        this.pacoteTuristico = pacoteTuristico;
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

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public PacoteTuristicoEntity getPacoteTuristico() {
        return pacoteTuristico;
    }

    public void setPacoteTuristico(PacoteTuristicoEntity pacoteTuristico) {
        this.pacoteTuristico = pacoteTuristico;
    }

    public PasseioEntity getPasseio() {
        return passeio;
    }

    public void setPasseio(PasseioEntity passeio) {
        this.passeio = passeio;
    }

    public PagamentoEntity getPagamento() {
        return pagamento;
    }

    public void setPagamento(PagamentoEntity pagamento) {
        this.pagamento = pagamento;
    }
}


