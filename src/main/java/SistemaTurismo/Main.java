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

        UsuarioEntity usuario = usuarioRepository.findById(6); //usuario: miguel

//        UsuarioEntity usuarioCadastradoAoBanco = new UsuarioEntity();
//
//        usuarioCadastradoAoBanco.setnome("Joana");
//        usuarioCadastradoAoBanco.setemail("joana@gmail.com");
//        usuarioCadastradoAoBanco.setsenha("Joana123#");
//        usuarioCadastradoAoBanco.setnumeroTelefone("45 98284841439");
//
//        usuarioRepository.cadastrar(usuarioCadastradoAoBanco);

        System.out.println(usuario.getnome());
        System.out.println(usuario.getnumeroTelefone());

       List<UsuarioEntity> usuarios = usuarioRepository.buscarTodos();
        for(UsuarioEntity u : usuarios){

            System.out.println(u.getnome());
        }

        System.out.println("Achei  eu:  " + usuarioRepository.buscarPorNomeInicial("Miguel"));
//
//        UsuarioEntity usuario3 = usuarioRepository.findById(3);
//
//        usuario3.setnome("Luciana");
//        usuario3.setsenha("Luci123@");
//        usuarioRepository.atualizar(usuario3);

//        UsuarioEntity usuario5 = usuarioRepository.findById(5);
//
//        usuarioRepository.remover(usuario5);




    }
}