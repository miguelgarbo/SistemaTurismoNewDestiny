package Entidades;

import java.io.Serializable;
import java.util.Objects;

public class PacotePasseiosID implements Serializable {

    private Long idPacote;
    private Long idPasseio;

    public PacotePasseiosID() {
    }

    public PacotePasseiosID(Long idPacote, Long idPasseio) {
        this.idPacote = idPacote;
        this.idPasseio = idPasseio;
    }

    // Getters e Setters
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

    // equals() e hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PacotePasseiosID)) return false;
        PacotePasseiosID that = (PacotePasseiosID) o;
        return Objects.equals(idPacote, that.idPacote) &&
                Objects.equals(idPasseio, that.idPasseio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPacote, idPasseio);
    }

}
