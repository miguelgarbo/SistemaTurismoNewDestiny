package Entidades;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "dia")
public class DiaEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int numeroDoDia;
    private LocalDate dataReal;

    @ManyToOne
    @JoinColumn(name = "roteiro_id", nullable = false)
    private RoteiroPersonalizadoEntity roteiro;

    @OneToMany(mappedBy = "dia", cascade = CascadeType.ALL)
    private List<PasseioEntity> passeios;

    // Métodos para adicionar passeios ao dia

    public void addPasseio(PasseioEntity passeio) {
        if (passeios == null) passeios = new ArrayList<>();
        this.passeios.add(passeio);
        passeio.setDia(this); // Associando o passeio ao dia
    }

    public List<PasseioEntity> getPasseios() {
        return passeios;
    }

    public void setPasseios(List<PasseioEntity> passeios) {
        this.passeios = passeios;
    }

    public int getNumeroDoDia() {
        return numeroDoDia;
    }

    public void setNumeroDoDia(int numeroDoDia) {
        this.numeroDoDia = numeroDoDia;
    }

    public LocalDate getdataReal() {
        return dataReal;
    }

    public void setdataReal(LocalDate dataReal) {
        this.dataReal = dataReal;
    }

    public RoteiroPersonalizadoEntity getRoteiro() {
        return roteiro;
    }

    public void setRoteiro(RoteiroPersonalizadoEntity roteiro) {
        this.roteiro = roteiro;
    }
}
