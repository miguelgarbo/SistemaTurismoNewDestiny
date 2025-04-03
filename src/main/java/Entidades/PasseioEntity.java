package Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "passeio")

public class PasseioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String duracao;
    private double preco;
    private List<String> listaHorarios = new ArrayList<>();;
    private String localizacao;
    private List<String> fotos;
}
