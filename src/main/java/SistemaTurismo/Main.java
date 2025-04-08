package SistemaTurismo;
import Entidades.PasseioEntity;
import Entidades.Roteiro_PasseiosEntity;
import Entidades.UsuarioEntity;
import Repositorio.CustomizerFactory;
import Repositorio.PasseioRepository;
import Repositorio.Roteiro_PasseiosRepository;
import Repositorio.UsuarioRepository;
import javax.persistence.EntityManager;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManager em = CustomizerFactory.getEntityManager();
//      repositorios:
        UsuarioRepository usuarioRepository = new UsuarioRepository(em);
        Roteiro_PasseiosRepository roteiroPasseiosRepository = new Roteiro_PasseiosRepository(em);
        PasseioRepository passeioRepository = new PasseioRepository(em);

//
//        PasseioEntity passeio = passeioRepository.findById(1);
//        System.out.println(passeio.toString());
//
//        UsuarioEntity usuario = usuarioRepository.findById(6L); //usuario: miguel
//        UsuarioEntity usuarioCadastradoAoBanco = new UsuarioEntity();
//
//        usuarioCadastradoAoBanco.setnome("Joana");
//        usuarioCadastradoAoBanco.setemail("joana@gmail.com");
//        usuarioCadastradoAoBanco.setsenha("Joana123#");
//        usuarioCadastradoAoBanco.setnumeroTelefone("45 98284841439");
//
//        usuarioRepository.cadastrar(usuarioCadastradoAoBanco);
//
//        System.out.println(usuario.getnome());
//        System.out.println(usuario.getnumeroTelefone());
//
//       List<UsuarioEntity> usuarios = usuarioRepository.buscarTodos();
//        for(UsuarioEntity u : usuarios){
//
//            System.out.println(u.getnome());
//        }
//
//        System.out.println("Achei  eu:  " + usuarioRepository.buscarPorNomeInicial("Miguel"));
//
//        UsuarioEntity usuario3 = usuarioRepository.findById(3);
//
//        usuario3.setnome("Luciana");
//        usuario3.setsenha("Luci123@");
//        usuarioRepository.atualizar(usuario3);

//        UsuarioEntity usuario5 = usuarioRepository.findById(5);
//
//        usuarioRepository.remover(usuario5);

        //TESTES COM ROTEIRO_PASSEIOS

//       List<Roteiro_PasseiosEntity> listaPasseiosPorIdRoteiro = roteiroPasseiosRepository.buscarPorIdRoteiro(1);
//
//        for(int i =0;i<listaPasseiosPorIdRoteiro.size();i++){
//
//            System.out.println(listaPasseiosPorIdRoteiro.get(i).toString());
//        }




    }

}