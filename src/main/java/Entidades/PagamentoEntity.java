package Entidades;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "Pagamento")

public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int idPedido;
    private String metodoPagamento;
    private StatusPagamento statusPagamento;
    private LocalDate data;


}
