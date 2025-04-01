package Entity;

public class Usuario {

    private int idUsuario;
    private String nomeUsuario;
    private String senhaUsuario;
    private String emailUsuario;
    private String numeroTelefoneUsuario;

    public Usuario(int idUsuario, String nomeUsuario, String senhaUsuario, String emailUsuario, String numeroTelefoneUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.senhaUsuario = senhaUsuario;
        this.emailUsuario = emailUsuario;
        this.numeroTelefoneUsuario = numeroTelefoneUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNumeroTelefoneUsuario() {
        return numeroTelefoneUsuario;
    }

    public void setNumeroTelefoneUsuario(String numeroTelefoneUsuario) {
        this.numeroTelefoneUsuario = numeroTelefoneUsuario;
    }
}
