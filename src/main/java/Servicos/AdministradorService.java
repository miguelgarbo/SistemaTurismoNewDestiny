package Servicos;
import Model.Entidades.AdministradorEntity;
import Repositorio.AdministradorRepository;
import javax.transaction.Transactional;
import java.util.List;


public class AdministradorService {


    private final AdministradorRepository administradorRepository;

    private AdministradorEntity idLoggedAdm;


    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    @Transactional
    public void cadastrarAdm(AdministradorEntity adm) {

        if (adm.getNome() == null || adm.getNome().isEmpty()) {

            throw new IllegalArgumentException("Nome não pode ser Vazio");
        }

        if (adm.getEmail() == null || adm.getEmail().isEmpty()) {

            throw new IllegalArgumentException("Email não pode ser Vazio");
        }

        administradorRepository.cadastrar(adm);
    }



    public boolean isEmailRegistrado(String email) {
        List<AdministradorEntity> listaAdms = administradorRepository.buscarTodos();

        for (AdministradorEntity adm : listaAdms) {
            if (adm.getEmail().equals(email)) {
                System.out.println("Esse Email Já tem Cadastro. Tente Seu Login Com Ele ");
                return true;
            }
        }
        return false;
    }


    public AdministradorEntity login(String email, String senha) {
        List<AdministradorEntity> adms = administradorRepository.buscarTodos();
        for (AdministradorEntity adm : adms) {
            if (adm.getEmail().equals(email) && adm.getSenha().equals(senha)) {
                return adm;
            }
        }
        return null;
    }


    public AdministradorEntity getIdLoggedAdm() {
        return idLoggedAdm;
    }

    public void setIdLoggedAdm(AdministradorEntity idLoggedAdm) {
        this.idLoggedAdm = idLoggedAdm;
    }


    public List<AdministradorEntity> buscarTodos(){
        return administradorRepository.buscarTodos();
    }
}