package Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity(name = "pacote_passeios")
public class Pacote_PasseiosEntity {
    @Id
    private int idPacote;
    @Id
    private int idPasseio;

    public Pacote_PasseiosEntity(int idPacote, int idPasseio) {
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

    public int getIdPacote() {
        return idPacote;
    }

    public void setIdPacote(int idPacote) {
        this.idPacote = idPacote;
    }

    public int getIdPasseio() {
        return idPasseio;
    }

    public void setIdPasseio(int idPasseio) {
        this.idPasseio = idPasseio;
    }
}
