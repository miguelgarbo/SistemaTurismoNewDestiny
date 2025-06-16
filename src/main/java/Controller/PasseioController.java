package Controller;

import Model.Entidades.*;
import Model.Repositorio.DiaRepository;
import Model.Servicos.CategoriaService;
import Model.Servicos.PasseioService;
import View.TelaConteudoSelecionado;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class PasseioController {

    private final PasseioService passeioService;
    private final CategoriaService categoriaService;
    private final CategoriaController categoriaController;
    private final PagamentoController pagamentoController;
    private final DiaRepository diaRepository;
    private final Scanner sc = new Scanner(System.in);

    public PasseioController(PasseioService passeioService, CategoriaService categoriaService, CategoriaController categoriaController, PagamentoController pagamentoController, DiaRepository diaRepository) {
        this.passeioService = passeioService;
        this.categoriaService = categoriaService;
        this.categoriaController = categoriaController;
        this.pagamentoController = pagamentoController;
        this.diaRepository = diaRepository;
    }

    public void exibirTodosPasseios(UsuarioEntity usuario) {

        exibirTodosPasseios();

        System.out.println("1 - realizar compra /// 2 - Sair");
        int op = sc.nextInt();
        sc.nextLine();

        if(op == 1){
            pagamentoController.menuPagarPasseio(usuario);
        }
    }

    public List<PasseioEntity> listaPasseiosCadastrados(){
        return passeioService.buscarTodos();

    }

   public PasseioService passeioService(){

        return passeioService;
   }


    public void exibirTodosPasseios() {

        System.out.println("==PASSEIOS DISPONIVEIS==");
        for (PasseioEntity passeio : listaPasseiosCadastrados()) {
            System.out.println("ID PASSEIO: " + passeio.getId());
            System.out.println("Título: " + passeio.getTitulo());
            System.out.println("Descrição: " + passeio.getDescricao());
            System.out.println("Localização: " + passeio.getLocalizacao());
            System.out.println("Preço: " + passeio.getPreco());
            System.out.println("Duração: " + passeio.getDuracao());
            System.out.println("-------------------------------------");

            if(passeio.getCategorias() != null || !passeio.getCategorias().isEmpty()){
                System.out.println("Categorias Inclusas: ");
                for(CategoriaEntity categoria : passeio.getCategorias()){
                    System.out.println("Titulo: "+ categoria.getNome());
                    System.out.println("-------------------------------------");
                }
            }else {
                System.out.println("SEM CATEGORIAS");
            }
        }
    }

    public PasseioEntity findById(Long id){
        return passeioService.buscarPorId(id);
    }

    public CategoriaService categoriaService(){
        return  categoriaService;
    }

    public DiaRepository diaRepository(){
        return  diaRepository;
    }

    public void cadastrarPasseio() {

        String continuar;
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

        do{
            System.out.println("== Categorias Disponiveis ==");
            categoriaController.exibirTodasCategorias();

            System.out.println("Informe o ID da Categoria: ");
            Long idCategoria = sc.nextLong();
            sc.nextLine();
            CategoriaEntity categoriaSelecionada = categoriaService.findById(idCategoria);

            if (categoriaSelecionada != null) {
                passeioNovo.addCategoria(categoriaSelecionada);
                categoriaService.atualizar(categoriaSelecionada);
                System.out.println(" Categoria adicionada com sucesso!");
            } else {
                System.out.println(" Categoria não encontrada.");
            }
            System.out.print("Deseja adicionar mais uma categoria? (sim/nao): ");
            continuar = sc.nextLine().toLowerCase();

        } while(continuar.equals("sim"));

        passeioService.cadastrar(passeioNovo);

        System.out.println("Passeio '" + passeioNovo.getTitulo() + "' cadastrado com sucesso!");
    }
}
