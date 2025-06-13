package Controller;

import Model.Entidades.DiaEntity;
import Model.Entidades.PasseioEntity;
import Model.Entidades.RoteiroPersonalizadoEntity;
import Model.Entidades.UsuarioEntity;
import Model.Servicos.PasseioService;
import Model.Servicos.RoteiroPersonalizadoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class RoteiroController {

    private final RoteiroPersonalizadoService roteiroPersonalizadoService;
    private final PasseioService passeioService;
    private final PasseioController passeioController;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RoteiroController(RoteiroPersonalizadoService roteiroPersonalizadoService,
                             PasseioService passeioService,
                             PasseioController passeioController) {
        this.roteiroPersonalizadoService = roteiroPersonalizadoService;
        this.passeioService = passeioService;
        this.passeioController = passeioController;
    }

    // Cria roteiro com dados recebidos, retorna o roteiro criado
    public RoteiroPersonalizadoEntity criarRoteiro(UsuarioEntity usuario, String titulo,
                                                   String dataInicioTexto, int numeroDias) {
        LocalDate dataInicio = LocalDate.parse(dataInicioTexto, formatter);

        if (numeroDias < 1) {
            throw new IllegalArgumentException("Número de dias deve ser maior que zero.");
        }

        RoteiroPersonalizadoEntity roteiroNovo = new RoteiroPersonalizadoEntity();
        roteiroNovo.setTitulo(titulo);
        roteiroNovo.setusuario(usuario);
        roteiroNovo.setDataInicio(dataInicio);

        // Cria os dias
        for (int i = 0; i < numeroDias; i++) {
            DiaEntity dia = new DiaEntity();
            dia.setNumeroDoDia(i + 1);
            dia.setdataReal(dataInicio.plusDays(i));
            roteiroNovo.adicionarDia(dia);
        }

        // Salva roteiro no serviço
        roteiroPersonalizadoService.cadastrarRoteiro(roteiroNovo);

        // Associa roteiro ao usuário
        usuario.getRoteirosCriados().add(roteiroNovo);

        return roteiroNovo;
    }

    // Retorna todos os passeios disponíveis para listar na interface
    public List<PasseioEntity> listarTodosPasseios() {
        return passeioService.buscarTodos(); // Assumindo que seu serviço tem este método
    }

    // Adiciona passeio ao roteiro e opcionalmente a um dia específico
    public boolean adicionarPasseioAoRoteiro(RoteiroPersonalizadoEntity roteiro,
                                             Long idPasseio,
                                             Integer numeroDia) {

        PasseioEntity passeio = passeioService.buscarPorId(idPasseio);
        if (passeio == null) {
            return false; // Passeio não encontrado
        }

        roteiro.addPasseio(passeio);

        if (numeroDia != null) {
            if (numeroDia < 1 || numeroDia > roteiro.getDias().size()) {
                throw new IllegalArgumentException("Número do dia inválido.");
            }
            DiaEntity dia = roteiro.getDias().get(numeroDia - 1);

            // Validação se passeio já está no dia
            if (roteiroPersonalizadoService.validarPasseioNoDia(dia, passeio)) {
                return false; // Passeio já adicionado no dia
            } else {
                dia.addPasseio(passeio);
            }
        }

        roteiroPersonalizadoService.atualizar(roteiro);
        return true;
    }

    // Retorna uma lista com os dias e passeios do roteiro para a interface exibir
    public List<DiaEntity> listarDiasDoRoteiro(RoteiroPersonalizadoEntity roteiro) {
        return roteiro.getDias();
    }

    // Remove passeio do roteiro pelo ID do passeio
    public boolean removerPasseioDoRoteiro(RoteiroPersonalizadoEntity roteiro, Long idPasseio) {
        boolean sucesso = passeioService.removerPasseioRoteiroPorId(idPasseio, roteiro);
        if (sucesso) {
            roteiroPersonalizadoService.atualizar(roteiro);
        }
        return sucesso;
    }

    // Atualiza dados básicos do roteiro
    public void atualizarTituloRoteiro(RoteiroPersonalizadoEntity roteiro, String novoTitulo) {
        roteiro.setTitulo(novoTitulo);
        roteiroPersonalizadoService.atualizar(roteiro);
    }

    public void atualizarDataInicioRoteiro(RoteiroPersonalizadoEntity roteiro, String dataInicioTexto) {
        LocalDate dataInicio = LocalDate.parse(dataInicioTexto, formatter);
        roteiro.setDataInicio(dataInicio);
        roteiroPersonalizadoService.atualizar(roteiro);
    }

    // Altera o número de dias do roteiro e atualiza a lista de dias
    public void alterarNumeroDiasRoteiro(RoteiroPersonalizadoEntity roteiro, int novoNumeroDias) {
        if (novoNumeroDias < 1) {
            throw new IllegalArgumentException("Número de dias deve ser maior que zero.");
        }

        roteiro.getDias().clear();

        for (int i = 0; i < novoNumeroDias; i++) {
            DiaEntity dia = new DiaEntity();
            dia.setNumeroDoDia(i + 1);
            dia.setdataReal(roteiro.getDataInicio().plusDays(i));
            roteiro.adicionarDia(dia);
        }

        roteiroPersonalizadoService.atualizar(roteiro);
    }

    // Busca roteiro por ID
    public Optional<RoteiroPersonalizadoEntity> buscarRoteiroPorId(Long id) {
        return Optional.ofNullable(roteiroPersonalizadoService.buscarPorId(id));
    }

    // Retorna todos roteiros do usuário
    public List<RoteiroPersonalizadoEntity> listarRoteirosDoUsuario(UsuarioEntity usuario) {
        return usuario.getRoteirosCriados();
    }

}
