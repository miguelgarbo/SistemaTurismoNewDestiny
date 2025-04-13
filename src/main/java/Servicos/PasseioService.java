package Servicos;

import Entidades.PacoteTuristicoEntity;
import Entidades.PasseioEntity;
import Repositorio.PasseioRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class PasseioService {

    @Inject
    private PasseioRepository passeioRepository;

    private Scanner sc = new Scanner(System.in);

    public PasseioService(PasseioRepository passeioRepository) {
        this.passeioRepository = passeioRepository;
    }

    @Transactional
    public void cadastrar(PasseioEntity passeio) {

        if (passeio.getTitulo() == null || passeio.getTitulo().isEmpty()) {

            throw new IllegalArgumentException("Titulo Não pode estar Vazio");
        }

        if (passeio.getPreco() == null || passeio.getPreco().compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException("Preço Não pode ser Negativo Nem Vazio");
        }
    }

    @Transactional
    public void removerpasseio(Long id) {
        PasseioEntity passeio = passeioRepository.findById(id);
        if (passeio != null) {
            passeioRepository.remover(passeio);
        }
    }

    @Transactional
    public void atualizarpasseio(PasseioEntity passeio) {

        if (passeio.getId() == null) {
            throw new IllegalArgumentException("ID do Passeio não pode ser nulo");
        }

        if (passeio.getTitulo() == null || passeio.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("Titulo Não pode Ser Vazio");
        }

        if (passeio.getPreco() == null || passeio.getPreco().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço Não pode ser Negativo Nem Vazio");
        }
    }


    public void mostrarTodosPasseios(List<PasseioEntity> passeios) {
        System.out.println("==PASSEIOS DISPONIVEIS==");
        for (PasseioEntity passeio : passeios) {
            System.out.println("ID: " + passeio.getId());
            System.out.println("Título: " + passeio.getTitulo());
            System.out.println("Descrição: " + passeio.getDescricao());
            System.out.println("Localização: " + passeio.getLocalizacao());
            System.out.println("Preço: " + passeio.getPreco());
            System.out.println("Duração: " + passeio.getDuracao());
            System.out.println("-------------------------------------");
        }
    }

    public void cadastrarPasseio() {
        PasseioEntity passeioNovo = new PasseioEntity();

        System.out.println("== CADASTRO DE PASSEIO ==");

        System.out.print("Digite o título do Passeio: ");
        passeioNovo.setTitulo(sc.nextLine());

        System.out.print("Digite a Descrição do Passeio: ");
        passeioNovo.setDescricao(sc.nextLine());

        System.out.print("Digite a Duração do Passeio:(ex: 2h30min) ");
        passeioNovo.setDuracao(sc.nextLine());

        System.out.print("Digite o Preço do Passeio: ");
        passeioNovo.setPreco(sc.nextBigDecimal());

        sc.nextLine();

        System.out.println("Digite a Localização do Passeio: ");
        passeioNovo.setLocalizacao(sc.nextLine());

        System.out.println("Digite os Horarios Disponiveis: (ex: De 8h até as 16h)");
        passeioNovo.setHorarios(sc.nextLine());

        passeioRepository.cadastrar(passeioNovo);

        System.out.println("Passeio '" + passeioNovo.getTitulo() + "' cadastrado com sucesso!");
    }

}

