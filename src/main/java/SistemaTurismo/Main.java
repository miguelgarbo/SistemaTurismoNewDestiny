package SistemaTurismo;
import Entidades.UsuarioEntity;
import Repositorio.CustomizerFactory;
import Repositorio.UsuarioRepository;
import javax.persistence.EntityManager;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManager em = CustomizerFactory.getEntityManager();
        UsuarioRepository usuarioRepository = new UsuarioRepository(em);

        UsuarioEntity usuario = usuarioRepository.findById(4);

        usuarioRepository.remover(usuario);



        System.out.println(usuario.getnome());
        System.out.println(usuario.getnumeroTelefone());

       List<UsuarioEntity> usuarios = usuarioRepository.buscarTodos();
        for(UsuarioEntity u : usuarios){

            System.out.println(u.getnome());
            System.out.println(usuarioRepository.buscarPorNomeInicial("Miguel"));
        }



    }
}