package Entidades;


import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "roteiro_passeios")

public class Roteiro_PasseiosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRoteiro;
    private int idPasseio;


    public Roteiro_PasseiosEntity(int idRoteiro, int idPasseio) {

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

    public int getIdRoteiro() {
        return idRoteiro;
    }

    public void setIdRoteiro(int idRoteiro) {
        this.idRoteiro = idRoteiro;
    }

    public int getIdPasseio() {
        return idPasseio;
    }

    public void setIdPasseio(int idPasseio) {
        this.idPasseio = idPasseio;
    }
}
