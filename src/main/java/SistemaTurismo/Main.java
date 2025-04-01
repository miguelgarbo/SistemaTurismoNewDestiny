package sistema;

import Passeio.java;
import Passeios_Pacotes.java;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE TURISMO FOZ DO IGUAÇU ===");

        //- Cadastro de passeios (Exemplo)
        Passeio cataratas = new Passeio(
            "Cataratas do Iguaçu",
            "Visita às quedas principais",
            100.0,
            "09:00 às 17:00",
            "Parque Nacional",
            "Natureza"
        );
        
        PasseioDAO.cadastrar(cataratas); // Salva no banco de dados

        //- Exibir informações
        System.out.println("\nPASSEIO CADASTRADO:");
        System.out.println(cataratas.getInfoBasica());

        //- Listar por categoria
        System.out.println("\nPASSEIOS DE NATUREZA:");
        List<Passeio> passeiosNatureza = PasseioDAO.listarPorCategoria("Natureza");
        for (Passeio passeio : passeiosNatureza) {
            System.out.println(passeio.getInfoBasica());
        }N

        //- Simulação de autenticação
        System.out.println("\nDigite seu e-mail e senha para login:");
    }
}
