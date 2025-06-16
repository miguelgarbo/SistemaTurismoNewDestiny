package Model.Entidades;
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

    @ManyToMany
    @JoinTable(
            name = "dia_passeios",
            joinColumns = @JoinColumn(name = "dia_id"),
            inverseJoinColumns = @JoinColumn(name = "passeio_id")
    )
    private List<PasseioEntity> passeios = new ArrayList<>();

    public void addPasseio(PasseioEntity passeio) {
        if (this.passeios == null) {
            this.passeios = new ArrayList<>();
        }
        if (!this.passeios.contains(passeio)) {
            this.passeios.add(passeio);
            if (passeio.getDias() == null) {
                passeio.setDias(new ArrayList<>());
            }
            if (!passeio.getDias().contains(this)) {
                passeio.getDias().add(this);
            }
        }
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
