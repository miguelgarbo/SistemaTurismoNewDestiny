package Entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity(name = "pacote_passeios")
@IdClass(Pacote_PasseiosEntity.class)

public class Pacote_PasseiosEntity implements Serializable {
    @Id
    private Long idPacote;
    @Id
    private Long idPasseio;

    public Pacote_PasseiosEntity(Long idPacote, Long idPasseio) {
        this.idPacote = idPacote;
        this.idPasseio = idPasseio;
    }

    public Pacote_PasseiosEntity(){}

    @Override
    public String toString() {
        return "Pacote_PasseiosEntity{" +
                "idPacote=" + idPacote +
                ", idPasseio=" + idPasseio +
                '}';
    }

    public Long getIdPacote() {
        return idPacote;
    }

    public void setIdPacote(Long idPacote) {
        this.idPacote = idPacote;
    }

    public Long getIdPasseio() {
        return idPasseio;
    }

    public void setIdPasseio(Long idPasseio) {
        this.idPasseio = idPasseio;
    }
}
