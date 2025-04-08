package Entidades;
import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "roteiro_passeios")
@IdClass(RoteiroPasseiosID.class)

public class Roteiro_PasseiosEntity implements Serializable{

    @Id
    private Long idRoteiro;
    @Id
    private Long idPasseio;


    public Roteiro_PasseiosEntity(Long idRoteiro, Long idPasseio) {

        this.idRoteiro = idRoteiro;
        this.idPasseio = idPasseio;
    }

    public Roteiro_PasseiosEntity() {
    }

    @Override
    public String toString() {
        return "Roteiro_PasseiosEntity{" +
                "idRoteiro=" + idRoteiro +
                ", idPasseio=" + idPasseio +
                '}';
    }

    public Long getIdRoteiro() {
        return idRoteiro;
    }

    public void setIdRoteiro(Long idRoteiro) {
        this.idRoteiro = idRoteiro;
    }

    public Long getIdPasseio() {
        return idPasseio;
    }

    public void setIdPasseio(Long idPasseio) {
        this.idPasseio = idPasseio;
    }
}
