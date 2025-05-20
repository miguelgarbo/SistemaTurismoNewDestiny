package Servicos;

import Model.Entidades.DiaEntity;
import Model.Entidades.PasseioEntity;
import Model.Entidades.RoteiroPersonalizadoEntity;
import Model.Repositorio.RoteiroPersonalizadoRepository;

import java.util.List;

public class RoteiroPersonalizadoService {

    private RoteiroPersonalizadoRepository repository;

    public RoteiroPersonalizadoService(RoteiroPersonalizadoRepository repository) {
        this.repository = repository;
    }

    public void cadastrarRoteiro(RoteiroPersonalizadoEntity roteiro) {

               if (roteiro.getTitulo() == null || roteiro.getTitulo().isEmpty()) {

                    throw new IllegalArgumentException("Nome não pode ser Vazio");
                }

                if (roteiro.getusuario() == null) {

                   throw new IllegalArgumentException("O Roteiro deve pertenter a um usuario");
                }

        repository.cadastrar(roteiro);

    }


    public void atualizar(RoteiroPersonalizadoEntity roteiroPersonalizado){
         repository.atualizar(roteiroPersonalizado);
    }


    public void adicionarDiaAoRoteiro(RoteiroPersonalizadoEntity roteiro, DiaEntity dia) {
        roteiro.getDias().add(dia);
        repository.atualizar(roteiro);
    }

    public void adicionarPasseioAoDia(DiaEntity dia, PasseioEntity passeio) {
        dia.getPasseios().add(passeio);

        RoteiroPersonalizadoEntity roteiro = dia.getRoteiro();
        repository.atualizar(roteiro);
    }


    public boolean validarPasseioNoDia(DiaEntity dia, PasseioEntity passeioSelecionado) {
              return dia.getPasseios() != null && dia.getPasseios().contains(passeioSelecionado);
    }

    public List<RoteiroPersonalizadoEntity> buscarTodos() {
        return repository.buscarTodos();
    }

    public RoteiroPersonalizadoEntity buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<RoteiroPersonalizadoEntity> buscarPorTituloInicial(String prefixo) {
        return repository.buscarPorTituloInicial(prefixo);
    }

    public void removerRoteiro(RoteiroPersonalizadoEntity roteiro) {
        repository.remover(roteiro);
    }
}

