package Model.Entidades;

import javax.persistence.*;

@Entity(name = "foto")
public class FotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @ManyToOne
        @JoinColumn(name = "idpasseio") // nome da coluna na tabela de fotos
        private PasseioEntity passeio;
        private String url;

    public FotoEntity(Long id, PasseioEntity passeio, String url) {
        this.id = id;
        this.passeio = passeio;
        this.url = url;
    }
    public FotoEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PasseioEntity getPasseio() {
        return passeio;
    }

    public void setPasseio(PasseioEntity passeio) {
        this.passeio = passeio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
