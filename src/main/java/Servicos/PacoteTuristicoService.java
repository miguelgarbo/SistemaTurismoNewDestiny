package Servicos;

import Entidades.PacoteTuristicoEntity;
import Repositorio.PacoteTuristicoRepository;

import javax.inject.Inject;
import java.util.List;

public class PacoteTuristicoService {

    @Inject
    private PacoteTuristicoRepository pacoteTuristicoRepository;

    public List<PacoteTuristicoEntity> listarTodosPacotes() {
        return pacoteTuristicoRepository.buscarTodos();
    }

    public void mostrarPacotesFormatados() {
        List<PacoteTuristicoEntity> pacotes = listarTodosPacotes();

        if (pacotes == null || pacotes.isEmpty()) {
            System.out.println("nenhum pacote turístico cadastrado.");
            return;
        }

        System.out.println("\nPACOTES TURÍSTICOS CADASTRADOS");
        System.out.println("==============================================");

        for (PacoteTuristicoEntity pacote : pacotes) {
            System.out.println("\nID: " + pacote.getId());
            System.out.println("Título: " + pacote.getTitulo());
            System.out.println("Categoria: " + pacote.getCategoria());
            System.out.printf("Preço Total: R$ %.2f%n", pacote.getPrecoTotal());
            System.out.println("----------------------------------------------");
        }

        System.out.printf("%n📊 Total de pacotes: %d%n", pacotes.size());
    }
}