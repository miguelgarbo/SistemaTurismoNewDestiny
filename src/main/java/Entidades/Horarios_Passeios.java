package Entidades;

import java.util.List;

public class Horarios_Passeios {

    private int idPasseio;
    private List<Integer> horarios;

    public Horarios_Passeios(int idPasseio, List<Integer> horarios) {
        this.idPasseio = idPasseio;
        this.horarios = horarios;
    }

    public int getIdPasseio() {
        return idPasseio;
    }

    public void setIdPasseio(int idPasseio) {
        this.idPasseio = idPasseio;
    }

    public List<Integer> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Integer> horarios) {
        this.horarios = horarios;
    }
}


