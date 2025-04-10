package Servicos;

import Entidades.PacoteTuristicoEntity;
import Entidades.PasseioEntity;
import Repositorio.PacoteTuristicoRepository;

import javax.inject.Inject;
import java.util.List;

public class PacoteTuristicoService {

    @Inject
    private PacoteTuristicoRepository pacoteTuristicoRepository;


    public void imprimirPacotesDisponiveis(List<PacoteTuristicoEntity> pacotes) {
        System.out.println("==== Lista de Pacotes Turísticos ====");

        for (PacoteTuristicoEntity pacote : pacotes) {
            // Imprime os dados do pacote
            System.out.printf("ID: %d\n", pacote.getId());
            System.out.printf("Título: %s\n", pacote.getTitulo());
            System.out.printf("Preço Total: R$ %.2f\n", pacote.getPrecoTotal());

            if (pacote.getpasseios() != null && !pacote.getpasseios().isEmpty()) {
                System.out.println("Passeios Inclusos:");
                for (PasseioEntity passeio : pacote.getpasseios()) {
                    System.out.println("ID: " + passeio.getId());
                    System.out.println("Título: " + passeio.getTitulo());
                    System.out.println("Descrição: " + passeio.getDescricao());
                    System.out.println("Localização: " + passeio.getLocalizacao());
                    System.out.println("Preço: " + passeio.getPreco());
                    System.out.println("Duração: " + passeio.getDuracao());
                    System.out.println("-------------------------------------");
                }
            } else {
                System.out.println("Nenhum passeio incluso.");
            }

            System.out.println("-------------------------------------");
        }
    }

}