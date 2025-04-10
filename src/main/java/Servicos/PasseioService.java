package Servicos;

import Entidades.PasseioEntity;
import Repositorio.PasseioRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public class PasseioService {

    @Inject
    private PasseioRepository passeioRepository;


    @Transactional
    public void cadastrarPasseio(PasseioEntity passeio) {

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
}

