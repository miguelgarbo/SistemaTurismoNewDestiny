package Entidades;

import javax.persistence.*;

@Entity
@Table(name = "foto")
public class FotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "idPasseio", nullable = false)
    private PasseioEntity passeio;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public PasseioEntity getPasseio() { return passeio; }
    public void setPasseio(PasseioEntity passeio) { this.passeio = passeio; }

    @Override
    public String toString() {
        return "FotoEntity{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
