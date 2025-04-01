package Entity;

public class Pagamentos {

    private int idPagamento;
    private int idPedido;
    private String metodoPagamento;
    private StatusPagamento statusPagamento;
    private String dataPagamento;

    //id_pagamento (PK, serial) – Identificador único do pagamento.
    //
    //id_pedido (FK → pedidos) – Pedido associado ao pagamento.
    //
    //metodo_pagamento (varchar) – Método utilizado (Cartão de Crédito, Pix, PayPal, etc.).
    //
    //status (varchar) – Status do pagamento ("Pendente", "Aprovado", "Cancelado").
    //
    //data_pagamento (timestamp) – Data e hora do pagamento

}
