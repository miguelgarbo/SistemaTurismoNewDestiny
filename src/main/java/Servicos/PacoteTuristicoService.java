package Servicos;

import Entidades.PacoteTuristicoEntity;
import Entidades.PasseioEntity;
import Entidades.RoteiroPersonalizadoEntity;
import Entidades.UsuarioEntity;
import Repositorio.PacoteTuristicoRepository;
import Repositorio.PasseioRepository;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PacoteTuristicoService {

    @Inject
    private PacoteTuristicoRepository pacoteTuristicoRepository;



    @Inject
    private PasseioRepository passeioRepository;

    @Inject
    private  PasseioService passeioService;



    private Scanner sc = new Scanner(System.in);

    public PacoteTuristicoService(PacoteTuristicoRepository pacoteTuristicoRepository, PasseioRepository passeioRepository, PasseioService passeioService) {
        this.pacoteTuristicoRepository = pacoteTuristicoRepository;
        this.passeioRepository = passeioRepository;
        this.passeioService = passeioService;
    }

    public BigDecimal somarValorPasseios(List<PasseioEntity> passeios) {
        BigDecimal valorPacote = BigDecimal.ZERO;
        for (PasseioEntity passeio : passeios) {
            valorPacote = valorPacote.add(passeio.getPreco());
        }
        return valorPacote;
    }


    public void cadastrarPacote() {
        PacoteTuristicoEntity pacoteNovo = new PacoteTuristicoEntity();
        String continuar = "sim";

        System.out.println("== CADASTRO DE PACOTE ==");

        System.out.print("Digite o título do pacote: ");
        pacoteNovo.setTitulo(sc.nextLine());

        System.out.print("Digite a categoria do pacote: ");
        pacoteNovo.setCategoria(sc.nextLine());

        do {
            System.out.println("=== Passeios disponíveis ===");
            passeioService.mostrarTodosPasseios(passeioRepository.buscarTodos());

            System.out.print("Informe o ID do passeio que deseja adicionar: ");
            Long idPasseioInformado = sc.nextLong();
            sc.nextLine(); // consumir o ENTER

            PasseioEntity passeioEscolhido = passeioRepository.findById(idPasseioInformado);

            if (passeioEscolhido != null) {
                pacoteNovo.addPasseio(passeioEscolhido);
                System.out.println(" Passeio adicionado com sucesso!");
            } else {
                System.out.println(" Passeio não encontrado.");
            }

            System.out.print("Deseja adicionar mais um passeio? (sim/nao): ");
            continuar = sc.nextLine().toLowerCase();

        } while (continuar.equals("sim"));

        BigDecimal precoTotal = somarValorPasseios(pacoteNovo.getpasseios());
        pacoteNovo.setPrecoTotal(precoTotal);

        pacoteTuristicoRepository.cadastrar(pacoteNovo);

        System.out.println("Pacote '" + pacoteNovo.getTitulo() + "' cadastrado com sucesso!");
    }

    public void imprimirPacotesDisponiveis(List<PacoteTuristicoEntity> pacotes) {
        System.out.println("==== Lista de Pacotes Turísticos ====");

        for (PacoteTuristicoEntity pacote : pacotes) {
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