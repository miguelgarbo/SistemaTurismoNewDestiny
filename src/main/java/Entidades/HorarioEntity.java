package Entidades;
import javax.persistence.*;
import java.time.LocalTime;

@Entity(name = "horario")
public class HorarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime horario;

    @ManyToOne
    @JoinColumn(name = "id_passeio", nullable = false)
    private PasseioEntity passeio;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalTime getHorario() { return horario; }
    public void setHorario(LocalTime horario) { this.horario = horario; }

    public PasseioEntity getPasseio() { return passeio; }
    public void setPasseio(PasseioEntity passeio) { this.passeio = passeio; }

    @Override
    public String toString() {
        return "HorarioEntity{" +
                "id=" + id +
                ", horario=" + horario +
                '}';
    }
}
