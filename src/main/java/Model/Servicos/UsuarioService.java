package Model.Servicos;
import Model.Entidades.UsuarioEntity;
import Model.Repositorio.UsuarioRepository;
import javax.transaction.Transactional;
import java.util.List;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, RoteiroPersonalizadoService roteiroPersonalizadoService) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void cadastrarUsuario(UsuarioEntity usuario) {
        // Validações
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser Vazio");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser Vazio");
        }

        if (usuario.getSenha().length() < 8) {
            throw new IllegalArgumentException("A senha deve conter 8 ou mais caracteres.");
        }

        usuarioRepository.cadastrar(usuario);
    }

    public void removerUsuario(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id);
        if (usuario != null) {
            usuarioRepository.remover(usuario);
        }
    }

    public void atualizarUsuario(UsuarioEntity usuario) {
        if (usuario.getId() == null) {
            throw new IllegalArgumentException("ID do Usuário não pode ser nulo");
        }
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome Não pode Ser Vazio");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email Não pode Ser Vazio");
        }
        usuarioRepository.atualizar(usuario);
    }

    public UsuarioEntity login(String email, String senha) {
        List<UsuarioEntity> usuarios = usuarioRepository.buscarTodos();
        for (UsuarioEntity usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public boolean isEmailRegistrado(String email) {
        List<UsuarioEntity> listaUsuarios = usuarioRepository.buscarTodos();
        for (UsuarioEntity usuario : listaUsuarios) {
            if (usuario.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void alterarInformacoesUsuario(UsuarioEntity usuarioLogado, int campoSelecionado, String novoValor) {
        switch (campoSelecionado) {
            case 1:
                usuarioLogado.setNome(novoValor);
                break;
            case 2:
                usuarioLogado.setSenha(novoValor);
                break;
            case 3:
                usuarioLogado.setNumeroTelefone(novoValor);
                break;
            default:
                throw new IllegalArgumentException("Opção inválida");
        }
        atualizarUsuario(usuarioLogado);
    }

    public List<UsuarioEntity> buscarTodos() {
        return usuarioRepository.buscarTodos();
    }


    public UsuarioEntity findById(Long id) {
        return usuarioRepository.findById(id);
    }
}
