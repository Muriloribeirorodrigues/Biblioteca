//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import Controle.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        BibliotecaC biblioteca = new BibliotecaC();
        EmpregadoC empregado = new EmpregadoC();
        ClienteC cliente = new ClienteC();
        while (opcao!= 4){

            System.out.println("1-Menu livro");
            System.out.println("2-Menu empregado");
            System.out.println("3-Menu cliente");
            System.out.println("4-Sair");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> menuLivros(biblioteca);
                case 2 -> menuEmpregado(empregado, biblioteca, cliente);
                case 3 -> menuCliente(cliente, biblioteca);
                case 4 -> System.out.println("Saindo do programa");
                default -> System.out.println("Opcao invalida");
            }
        }

        sc.close();
    }
    private static void menuLivros(BibliotecaC biblioteca){
        Scanner sc = new Scanner(System.in);

        int opcao2 = 0;

        while (opcao2 != 7) {
            System.out.println("1-Cadastrar livro");
            System.out.println("2-Listar livros");
            System.out.println("3-Buscar livro");
            System.out.println("4-Remover livro");
            System.out.println("5-Atualizar dados do livro");
            System.out.println("6-Alterar estoque do livro");
            System.out.println("7-Voltar");

            opcao2 = sc.nextInt();
            sc.nextLine();

            switch (opcao2) {
                case 1 -> biblioteca.CadastrarLivro();
                case 2 -> biblioteca.ListarLivros();
                case 3 -> biblioteca.BuscarLivro();
                case 4 -> biblioteca.RemoveLivro();
                case 5 -> biblioteca.AtualizarDadosLivros();
                case 6 -> biblioteca.AlterarEstoque();
                case 7 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opcao invalida");
            }
        }
    }

private static void menuEmpregado(EmpregadoC empregado, BibliotecaC biblioteca, ClienteC cliente){

    int opcao2 = 0;
    Scanner sc = new Scanner(System.in);

    while (opcao2 != 8) {

        System.out.println("1-Cadastrar empregado");
        System.out.println("2-Listar empregados");
        System.out.println("3-Buscar empregado");
        System.out.println("4-Remover empregado");
        System.out.println("5-Atualizar dados do empregado");
        System.out.println("6-Emprestar livros");
        System.out.println("7-Listar empréstimos");
        System.out.println("8-Voltar");
        opcao2 = sc.nextInt();
        sc.nextLine();
        switch (opcao2){

        
        case 1 -> empregado.CadastrarEmpregado();
        case 2 -> empregado.ListarEmpregado();
        case 3 -> empregado.BuscarEmpregado();
        case 4 -> empregado.RemoveEmpregado();
        case 5 -> empregado.AtualizarDadosEmpregado();
        case 6 -> empregado.EmprestarLivros(biblioteca, cliente);
        case 7 -> System.out.println("Voltando ao menu principal...");
        default -> System.out.println("Opcao invalida");
        }
    }
}

    private static void menuCliente(ClienteC cliente, BibliotecaC biblioteca){

        int opcao2 = 0;
        Scanner sc = new Scanner(System.in);

        while (opcao2 != 7) {

        System.out.println("1-Cadastrar cliente");
        System.out.println("2-Listar clientes");
        System.out.println("3-Buscar cliente");
        System.out.println("4-Remover cliente");
        System.out.println("5-Atualizar dados do cliente");
        System.out.println("6-Devolver livro");
        System.out.println("7-Voltar");
            
        opcao2 = sc.nextInt();
        sc.nextLine();

            switch (opcao2) {
                case 1 -> cliente.CadastrarCliente();
                case 2 -> cliente.listarClientes();
                case 3 -> cliente.BuscarClientePorCPF();
                case 4 -> cliente.RemoverClientePorCPF();
                case 5 -> cliente.AtualizarDadosCliente();
                case 6 -> cliente.DevolverLivro(biblioteca);
                case 7 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opcao invalida");
            }
        }
    }
}
