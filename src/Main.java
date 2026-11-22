import BibliotecaM.ValidadorM;
import Controle.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        BibliotecaC biblioteca = new BibliotecaC();
        EmpregadoC empregado = new EmpregadoC();
        ClienteC cliente = new ClienteC();
        EmprestimoC emprestimo = new EmprestimoC();
        
        while (opcao!= 4){

            System.out.println("1-Menu livro");
            System.out.println("2-Menu empregado");
            System.out.println("3-Menu cliente");
            System.out.println("4-Sair");

            opcao = ValidadorM.lerInt(sc);

            switch (opcao) {
                case 1 -> executar(() -> menuLivros(sc, biblioteca));
                case 2 -> executar(() -> menuEmpregado(sc, empregado, biblioteca, cliente, emprestimo));
                case 3 -> executar (() -> menuCliente(sc, cliente, biblioteca, empregado, emprestimo));
                case 4 -> System.out.println("Saindo do programa");
                default -> System.out.println("Opcao invalida");
            }
        }

        sc.close();
    }
    private static void menuLivros(Scanner sc, BibliotecaC biblioteca){

        int opcao2 = 0;

        while (opcao2 != 8) {
            System.out.println("1-Cadastrar livro");
            System.out.println("2-Listar livros");
            System.out.println("3-Buscar livro");
            System.out.println("4-Remover livro");
            System.out.println("5-Atualizar dados do livro");
            System.out.println("6-Alterar estoque do livro");
            System.out.println("7-Listar livros por genero ou autor");
            System.out.println("8-Voltar");

            opcao2 = ValidadorM.lerInt(sc);

            switch (opcao2) {
                case 1 -> executar(() -> biblioteca.CadastrarLivro(sc));
                case 2 -> executar(() -> biblioteca.ListarLivro());
                case 3 -> executar(() -> biblioteca.BuscarLivro(sc));
                case 4 -> executar(() -> biblioteca.RemoveLivro(sc));
                case 5 -> executar(() -> biblioteca.AtualizarDadosLivro(sc));
                case 6 -> executar(() -> biblioteca.AlterarEstoque(sc));
                case 7 -> executar(() -> biblioteca.ListarLivroPor(sc));
                case 8 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opcao invalida");
            }
        }
    }

private static void menuEmpregado(Scanner sc, EmpregadoC empregado, BibliotecaC biblioteca, ClienteC cliente, EmprestimoC emprestimo){

    int opcao2 = 0;

    while (opcao2 != 10) {

        System.out.println("1-Cadastrar empregado");
        System.out.println("2-Listar empregados");
        System.out.println("3-Buscar empregado");
        System.out.println("4-Remover empregado");
        System.out.println("5-Atualizar dados do empregado");
        System.out.println("6-Emprestar livros");
        System.out.println("7-Listar empréstimos");
        System.out.println("8-Consultar empréstimos por CPF");
        System.out.println("9-Reativar cadastro");
        System.out.println("10-Voltar");
        
        opcao2 = ValidadorM.lerInt(sc);
        
        switch (opcao2){

        
        case 1 -> executar(() -> empregado.CadastrarEmpregado(sc));
        case 2 -> executar(() -> empregado.ListarEmpregado());
        case 3 -> executar(() -> empregado.BuscarEmpregado(sc));
        case 4 -> executar(() -> empregado.RemoveEmpregado(sc, emprestimo));
        case 5 -> executar(() -> empregado.AtualizarDadosEmpregado(sc));
        case 6 -> executar(() -> empregado.EmprestarLivros(biblioteca, cliente, emprestimo, sc));
        case 7 -> executar(() -> empregado.ListarEmprestimo(emprestimo));
        case 8 -> executar(() -> empregado.ConsultarEmprestimoPorCPF(emprestimo, sc));
        case 9 -> executar(() -> empregado.ReativarCadastro(sc));
        case 10 -> System.out.println("Voltando ao menu principal...");
        default -> System.out.println("Opcao invalida");
        }
    }
}

    private static void menuCliente(Scanner sc, ClienteC cliente, BibliotecaC biblioteca, EmpregadoC empregado, EmprestimoC emprestimo) {

        int opcao2 = 0;
        while (opcao2 != 9) {

        System.out.println("1-Cadastrar cliente");
        System.out.println("2-Listar clientes");
        System.out.println("3-Buscar cliente");
        System.out.println("4-Remover cliente");
        System.out.println("5-Atualizar dados do cliente");
        System.out.println("6-Devolver livro");
        System.out.println("7-Consultar empréstimos por CPF");
        System.out.println("8-Reativar cadastros");
        System.out.println("9-Voltar");
            
        opcao2 = ValidadorM.lerInt(sc);

            switch (opcao2) {
                case 1 -> executar(() -> cliente.CadastrarCliente(sc));
                case 2 -> executar(() -> cliente.listarClientes());
                case 3 -> executar(() -> cliente.BuscarClientePorCPF(sc));
                case 4 -> executar(() -> cliente.RemoverClientePorCPF(sc, emprestimo));
                case 5 -> executar(() -> cliente.AtualizarDadosCliente(sc));
                case 6 -> executar(() -> cliente.DevolverLivro(biblioteca, empregado, emprestimo, sc));
                case 7 -> executar(() -> cliente.ConsultarEmprestimoPorCPF(empregado, emprestimo, sc));
                case 8 -> executar(() -> cliente.ReativarCadastro(sc));
                case 9 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opcao invalida");
            }
        }
    }

    private static void executar(Runnable acao) {
        try {
            acao.run();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

}   
