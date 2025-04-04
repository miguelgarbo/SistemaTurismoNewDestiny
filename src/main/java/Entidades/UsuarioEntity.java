package Entidades;

import javax.persistence.*;

@Entity
@Table (name = "Usuario")

public class UsuarioEntity {
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String senha;
    private String email;
    private String numeroTelefone;

    public UsuarioEntity(int id, String nome, String senha, String email, String numeroTelefone) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.numeroTelefone = numeroTelefone;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }

    public String getsenha() {
        return senha;
    }

    public void setsenha(String senha) {
        this.senha = senha;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getnumeroTelefone() {
        return numeroTelefone;
    }

    public void setnumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }
}
