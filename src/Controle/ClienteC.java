package Controle;
import BibliotecaM.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ClienteC {
    private ArrayList<ClienteM> vetClientes;

    public ArrayList<ClienteM> getVetClientes() {
        return vetClientes;
    }

    public ClienteC() {
        this.vetClientes = new ArrayList<ClienteM>();
    }

    public void CadastroDadosPessoais(ClienteM dpm) {
        vetClientes.add(dpm);
    }

    public void CadastrarCliente(Scanner sc){

        String CPF = ValidadorM.lerCPF(sc, "CPF: ");
        
        ClienteM existente = BuscaClientesPorCPFIncluindoInativos(CPF);

        if (existente != null){
            if(existente.isAtivo()){
                System.out.println("Cpf ja cadastrado");
                return;
            }
            System.out.println("Ja existe um cadastro inativo com esse cpf");
            System.out.println("Deseja reativar esse cadastro? (S/N)");
            String resposta = sc.nextLine().trim();
            if(resposta.equalsIgnoreCase("S")){
                existente.ativar();
                System.out.println("Cliente reativado com sucesso");
            }
            else{
                System.out.println("Operacao cancelada");
            }
            return;
        }

        String NomeCompleto = ValidadorM.lerTextoObrigatorio(sc, "Nome: ");

        String telefone = ValidadorM.lerTelefone(sc, "Telefone: ");

        String nascimento = ValidadorM.lerData(sc, "Data de nascimento: ddmmaaaa");

        char sexo = ValidadorM.lerSexo(sc, "Digite M (Masculino) ou F (Feminino)");

        ClienteM NovoCliente = new ClienteM(NomeCompleto, telefone, CPF, nascimento, sexo);

        vetClientes.add(NovoCliente);

        System.out.println("Cliente cadastrado");
    }

    public void listarClientes() {
        if (SemClientes()) {
            return;
        }
        for (ClienteM cliente : vetClientes) {
            if(cliente.isAtivo()){
                cliente.PrintCliente();
            }
        }
    }

    public void BuscarClientePorCPF(Scanner sc){
        if (SemClientes()) {
            return;
        }
        String cpfFormatado = ValidadorM.lerCPF(sc, "Digite o CPF do cliente que deseja buscar: ");
        ClienteM cliente = BuscarPorCPF(cpfFormatado);
        if(cliente == null){
            System.out.println("Cliente nao encontrado");
            return;
        }
        cliente.PrintCliente();
    }

    public ClienteM BuscarPorCPF(String cpf){
        for (ClienteM cliente : vetClientes) {
            if (cliente.getCPF().equals(cpf) && cliente.isAtivo()) {
                return cliente;
            }
        }
        return null;
    }

    private ClienteM BuscaClientesPorCPFIncluindoInativos(String cpf){
        for(ClienteM cliente : vetClientes){
            if(cliente.getCPF().equals(cpf)){
                return cliente;
            }
        }
        return null;
    }

    public void RemoverClientePorCPF(Scanner sc, EmprestimoC emprestimos){
        if (SemClientes()) {
            return;
        }
        String cpf = ValidadorM.lerCPF(sc, "Digite o cpf do cliente que deseja remover: ");
        ClienteM cliente = BuscarPorCPF(cpf);
        if(cliente == null){
            System.out.println("Cliente nao encontrado");
                return;
            }
        if(emprestimos.possuiEmprestimosAtivos(cpf)){
            System.out.println("Cliente possui um emprestimo ativo e nao pode ser removido");
            return;
        }
        cliente.desativar();
        System.out.println("Cliente removido com sucesso!");
    }

    public void AtualizarDadosCliente(Scanner sc){
        if (SemClientes()) {
            return;
        }
        String cpfcliente = ValidadorM.lerCPF(sc,"Digite o CPF do cliente que deseja atualizar: ");
        ClienteM cliente = BuscarPorCPF(cpfcliente);
            if(cliente == null){
                System.out.println("Cliente nao encontrado");
                return;
            }
            
        int opcao = 0;
        while (opcao != 6){
            System.out.println("1 - Atualizar nome do cliente");
            System.out.println("2 - Atualizar telefone do cliente");
            System.out.println("3 - Atualizar data de nascimento do cliente");
            System.out.println("4 - Atualizar sexo do cliente");
            System.out.println("5 - Atualizar tudo");
            System.out.println("6 - Voltar");
            opcao = ValidadorM.lerInt(sc);

            switch(opcao){
                case 1 -> {cliente.setNome(ValidadorM.lerTextoObrigatorio(sc, "Digite o novo nome do cliente: "));
                    System.out.println("Alteracao feita com sucesso");
                }
                case 2 -> {cliente.setTelefone(ValidadorM.lerTelefone(sc, "Digite o novo telefone do cliente: "));
                    System.out.println("Alteracao feita com sucesso");
                }
                case 3 -> {cliente.setNascimento(ValidadorM.lerData(sc, "Digite a nova data de nascimento do cliente: "));
                    System.out.println("Alteracao feita com sucesso");
                }
                case 4 -> {cliente.setSexo(ValidadorM.lerSexo(sc, "Digite o novo sexo do cliente: "));
                    System.out.println("Alteracao feita com sucesso");
                }
                case 5 -> {cliente.setNome(ValidadorM.lerTextoObrigatorio(sc, "Digite o novo nome do cliente: "));
                           cliente.setTelefone(ValidadorM.lerTelefone(sc, "Digite o novo telefone do cliente: "));
                           cliente.setNascimento(ValidadorM.lerData(sc, "Digite a nova data de nascimento do cliente: "));
                           cliente.setSexo(ValidadorM.lerSexo(sc, "Digite o novo sexo do cliente: "));
                           System.out.println("Alteracao feita com sucesso");
                }
                case 6 -> System.out.println("Voltar");
                default -> System.out.println("Opcao invalida");
            }
        }
    } 
    public void DevolverLivro(BibliotecaC biblioteca, EmpregadoC empregado, EmprestimoC emprestimos, Scanner sc){
        if (SemClientes()) {
            return;
        }
        if(emprestimos.getVetEmprestimo().isEmpty()){
            System.out.println("Não ha emprestimos registrados");
            return;
        }
        String cpfFormatado = ValidadorM.lerCPF(sc, "Digite o CPF do cliente que deseja devolver o livro: ");
        System.out.println("Digite o nome do livro que deseja devolver: ");
                String nomelivro = sc.nextLine();
        for(EmprestimoM emprestimo :  emprestimos.getVetEmprestimo()){
            if(emprestimo.isAtivo() && emprestimo.getCliente().getCPF().equals(cpfFormatado) && emprestimo.getLivro().getNomeDoLivro().equalsIgnoreCase(nomelivro)){
                emprestimo.devolver();
                Livro livro = emprestimo.getLivro();
                livro.setQuantidade(livro.getQuantidade()+1);
                if(emprestimo.estaAtrasado()){
                    System.out.println("Livro devolvido com atraso de " + emprestimo.diasDeAtraso() + " dias");
                }
                else{
                    System.out.println("Livro devolvido dentro do prazo");
                }
                System.out.println("Livro devolvido com sucesso.");
                return;
            }
        }
        System.out.println("Emprestimo nao encontrado para esse cliente");
    }
    public void ConsultarEmprestimoPorCPF(EmpregadoC empregado, EmprestimoC emprestimos, Scanner sc){
        if (SemClientes()) {
            return;
        }
        if(emprestimos.getVetEmprestimo().isEmpty()){
            System.out.println("Não ha emprestimos registrados");
            return;
        }
        System.out.println("Digite o CPF do cliente que deseja consultar os empréstimos: ");
        String cpfCliente = sc.nextLine();
        String cpfFormatado = ValidadorM.formatarCPF(cpfCliente);
        int encontrado = 0;
        for(EmprestimoM emprestimo : emprestimos.getVetEmprestimo()) {
            if (emprestimo.getCliente().getCPF().equals(cpfFormatado) && emprestimo.isAtivo() && emprestimo.getCliente().isAtivo()) {
                System.out.println(emprestimo);
                encontrado++;
            }
        }
        if (encontrado == 0) {
            System.out.println("Nao existe emprestimo para o CPF informado.");
        }
    }
    private boolean SemClientes(){
        for(ClienteM cliente : vetClientes){
            if(cliente.isAtivo()){
                return false;
            }
        }
        System.out.println("Sem clientes ativos");
        return true;
    }
    public void ReativarCadastro(Scanner sc){
        boolean encontrou = false;
        for(ClienteM cliente : vetClientes){
            if(!cliente.isAtivo()){
                cliente.PrintCliente();
                encontrou = true;
            }
        }
        if(!encontrou){
            System.out.println("Nao existem clientes inativos");
            return;
        }
        String cpf = ValidadorM.lerCPF(sc, "Digite o cpf que deseja reativar");
        for(ClienteM cliente : vetClientes){
            if(cliente.getCPF().equals(cpf)){
                if(cliente.isAtivo()){
                System.out.println("Cliente ja possui cpf ativado");
                return;
                }
            cliente.ativar();
            System.out.println("Cliente reativado com sucesso");
            return;
            }
        }
        System.out.println("Nao existe esse cpf no sistema");
    }
}