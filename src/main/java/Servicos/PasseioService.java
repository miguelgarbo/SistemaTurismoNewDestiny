package Servicos;

import Model.Entidades.*;
import Repositorio.PasseioRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public class PasseioService {

    private final PasseioRepository passeioRepository;

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
        passeioRepository.cadastrar(passeio);
    }

    @Transactional
    public void removerPasseio(Long id) {
        PasseioEntity passeio = passeioRepository.findById(id);
        if (passeio != null) {
            passeioRepository.remover(passeio);
        }
    }

    @Transactional
    public void atualizarPasseio(PasseioEntity passeio) {
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

    public PasseioEntity buscarPorId(Long id) {
        return passeioRepository.findById(id);
    }

    public List<PasseioEntity> buscarTodos() {
        return passeioRepository.buscarTodos();
    }

    public void removerPasseioRoteiroPorId(Long idPasseio, RoteiroPersonalizadoEntity roteiroPersonalizado) {
        if (idPasseio != null) {
            PasseioEntity passeio = passeioRepository.findById(idPasseio);
            if (passeio != null) {
                roteiroPersonalizado.getPasseios().remove(passeio);
                if (roteiroPersonalizado.getDias() != null) {
                    for (DiaEntity dia : roteiroPersonalizado.getDias()) {
                        dia.getPasseios().remove(passeio);
                    }
                }
            }
        }
    }
}
