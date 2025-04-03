package Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity(name = "Passeios_Pacote")
public class Pacote_PasseiosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPacote;
    private Long idPasseio;

}
