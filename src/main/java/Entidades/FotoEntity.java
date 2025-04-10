package Entidades;

import javax.persistence.*;

@Entity(name = "foto")
public class FotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @ManyToOne
        @JoinColumn(name = "idpasseio") // nome da coluna na tabela de fotos
        private PasseioEntity passeio;
        private String url;



}
