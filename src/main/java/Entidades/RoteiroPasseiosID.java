package Entidades;

import java.io.Serializable;
import java.util.Objects;

public class RoteiroPasseiosID implements Serializable {

    private Long idRoteiro;
    private Long idPasseio;

    public RoteiroPasseiosID(Long idRoteiro, Long idPasseio) {
        this.idRoteiro = idRoteiro;
        this.idPasseio = idPasseio;
    }

    public RoteiroPasseiosID(){}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RoteiroPasseiosID that = (RoteiroPasseiosID) o;
        return idRoteiro == that.idRoteiro && idPasseio == that.idPasseio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRoteiro, idPasseio);
    }
}
