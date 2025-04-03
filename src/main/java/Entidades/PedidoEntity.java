package Entidades;

import jakarta.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "Pedido")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUsuario;
    private Long idPacote;
    private int quantidadeIngressos;
    private double valorTotal;
    private StatusPagamento statusPagamento;
    private LocalDate dataCompra;

}


