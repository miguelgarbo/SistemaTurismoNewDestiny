package Servicos;

import Entidades.UsuarioEntity;
import Repositorio.UsuarioRepository;
import org.hibernate.service.spi.InjectService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

public class UsuarioService {

    @Inject
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void cadastrarUsuario(UsuarioEntity usuario){

        if(usuario.getnome() == null || usuario.getnome().isEmpty()){

            throw new IllegalArgumentException("Nome não pode ser Vazio");
        }

        if(usuario.getemail() == null || usuario.getemail().isEmpty()){

            throw new IllegalArgumentException("Email não pode ser Vazio");
        }

        usuarioRepository.cadastrar(usuario);
    }

    @Transactional
    public void removerUsuario(Long id){
        UsuarioEntity usuario = usuarioRepository.findById(id);
        if(usuario != null){
            usuarioRepository.remover(usuario);
        }
    }

    @Transactional
    public void atualizarUsuario(UsuarioEntity usuario){

        if(usuario.getid() == null){
            throw new IllegalArgumentException("ID do Usuário não pode ser nulo");
        }

        if(usuario.getnome() == null || usuario.getnome().isEmpty()){
            throw new IllegalArgumentException("Nome Não pode Ser Vazio");
        }

        if(usuario.getemail() == null || usuario.getemail().isEmpty()){
            throw new IllegalArgumentException("Email Não pode Ser Vazio");
        }
    }

    @Transactional
    public List<UsuarioEntity> listarUsuario(){
        return usuarioRepository.buscarTodos();
    }





}
