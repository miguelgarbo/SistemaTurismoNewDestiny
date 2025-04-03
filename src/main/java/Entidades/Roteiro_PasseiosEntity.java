package Entidades;

import jakarta.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Passeios_Roteiros")
public class Roteiro_PasseiosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoteiro;
    private Long idPasseio;

}
