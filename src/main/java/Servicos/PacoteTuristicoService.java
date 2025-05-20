package Servicos;
import Model.Entidades.PacoteTuristicoEntity;
import Model.Entidades.PasseioEntity;
import Model.Repositorio.PacoteTuristicoRepository;
import Model.Repositorio.PasseioRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class PacoteTuristicoService {

    private final PacoteTuristicoRepository pacoteTuristicoRepository;

    private final PagamentoService pagamentoService;

    private final PasseioRepository passeioRepository;

    private final PasseioService passeioService;

    private final CategoriaService categoriaService;

    private final Scanner sc = new Scanner(System.in);

    public PacoteTuristicoService(PacoteTuristicoRepository pacoteTuristicoRepository, PagamentoService pagamentoService, PasseioRepository passeioRepository, PasseioService passeioService, CategoriaService categoriaService) {
        this.pacoteTuristicoRepository = pacoteTuristicoRepository;
        this.pagamentoService = pagamentoService;
        this.passeioRepository = passeioRepository;
        this.passeioService = passeioService;
        this.categoriaService = categoriaService;
    }

    public BigDecimal somarValorPasseios(List<PasseioEntity> passeios) {
        BigDecimal valorPacote = BigDecimal.ZERO;
        for (PasseioEntity passeio : passeios) {
            valorPacote = valorPacote.add(passeio.getPreco());
        }
        return valorPacote;
    }

    @Transactional
    public void cadastrar(PacoteTuristicoEntity pacoteTuristico) {
        if (pacoteTuristico.getTitulo() == null || pacoteTuristico.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("Titulo Não pode estar Vazio");
        }

        pacoteTuristicoRepository.cadastrar(pacoteTuristico);
    }

    @Transactional
    public void remover(Long id) {
        PacoteTuristicoEntity pacoteTuristico = pacoteTuristicoRepository.findById(id);
        if (pacoteTuristico != null) {
            pacoteTuristicoRepository.remover(pacoteTuristico);
        }
    }

    @Transactional
    public void atualizarPacote(PacoteTuristicoEntity pacoteTuristico) {
        if (pacoteTuristico.getId() == null) {
            throw new IllegalArgumentException("ID do Pacote não pode ser nulo");
        }
        if (pacoteTuristico.getTitulo() == null || pacoteTuristico.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("Titulo Não pode Ser Vazio");
        }
    }

    public PacoteTuristicoEntity buscarPorId(Long id) {
        return pacoteTuristicoRepository.findById(id);
    }

    public List<PacoteTuristicoEntity> buscarTodos() {
        return pacoteTuristicoRepository.buscarTodos();
    }


}