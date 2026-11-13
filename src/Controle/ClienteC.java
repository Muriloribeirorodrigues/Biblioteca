package Controle;
import BibliotecaM.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ClienteC {
    public ArrayList<ClienteM> vetClientes;

    public ArrayList<ClienteM> getVetClientes() {
        return vetClientes;
    }

    public ClienteC() {
        this.vetClientes = new ArrayList<ClienteM>();
    }

    public void CadastroDadosPessoais(ClienteM dpm) {
        vetClientes.add(dpm);
    }

    public void CadastrarCliente() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Nome: ");
        String NomeCompleto = sc.nextLine();

        System.out.println("Telefone: ");
        String telefoneentrada = sc.nextLine();
        String telefone = formatarTelefone(telefoneentrada);

        System.out.println("Cpf: ");
        String cpfentrada = sc.nextLine();
        String CPF = formatarCPF(cpfentrada);

        System.out.println("DataNascimento :");
        String nascimentoentrada = sc.nextLine();
        String nascimento = formatarData(nascimentoentrada);

        System.out.println("Sexo :");
        char sexo = sc.next().charAt(0);

        ClienteM NovoCliente = new ClienteM(NomeCompleto, telefone, CPF, nascimento, sexo);

        vetClientes.add(NovoCliente);

        System.out.println("Cliente cadastrado");
    }

    public void listarClientes() {
        if (SemClientes()) {
            return;
        }
        for (ClienteM cliente : vetClientes) {
            cliente.PrintCliente();
        }
    }

    private String formatarCPF(String cpf) {
        String cpfFormatado = cpf.replaceAll("[^\\d]", "");
        if (cpfFormatado.length() == 11) {
            return cpfFormatado.substring(0, 3) + "." + cpfFormatado.substring(3, 6) + "." + cpfFormatado.substring(6, 9) + "-" + cpfFormatado.substring(9, 11);
        } else if (cpfFormatado.length() != 11) {
            throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos.");
        }
        return cpfFormatado;
    }

    private String formatarData(String data) {
        String dataformatada = data.replaceAll("[^\\d]", "");
        if (dataformatada.length() == 8) {
            return dataformatada.substring(0, 2) + "/" + dataformatada.substring(2, 4) + "/" + dataformatada.substring(4, 8);
        } else if (dataformatada.length() != 8) {
            throw new IllegalArgumentException("Data inválida. Deve conter 8 dígitos.");
        }
        return dataformatada;
    }

    private String formatarTelefone(String telefone) {
        String telefoneFormatado = telefone.replaceAll("[^\\d]", "");
        if (telefoneFormatado.length() == 11) {
            return "(" + telefoneFormatado.substring(0, 2) + ") " + telefoneFormatado.substring(2, 7) + "-" + telefoneFormatado.substring(7, 11);
        } else {
            throw new IllegalArgumentException("Telefone inválido. Deve conter 11 dígitos.");
        }
    }
    public void BuscarClientePorCPF(){
        if (SemClientes()) {
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o CPF do cliente que deseja buscar: ");
        String cpfcliente = sc.nextLine();
        String cpfFormatado = formatarCPF(cpfcliente);
        for(ClienteM cliente : vetClientes){
            if(cliente.getCPF().equals(cpfFormatado)){
                cliente.PrintCliente();
                return;
            }
        }
        System.out.println("Cliente não encontrado.");
    }
    public void RemoverClientePorCPF(){
        if (SemClientes()) {
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o CPF do cliente que deseja remover: ");
        String cpfcliente = sc.nextLine();
        String cpfFormatado = formatarCPF(cpfcliente);
        for(ClienteM cliente : vetClientes){
            if(cliente.getCPF().equals(cpfFormatado)){
                vetClientes.remove(cliente);
                System.out.println("Cliente removido com sucesso.");
                return;
            }
        }
    }
    public void AtualizarDadosCliente(){
        if (SemClientes()) {
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o CPF do cliente que deseja atualizar: ");
        String cpfcliente = sc.nextLine();
        for(ClienteM cliente : vetClientes){
            if(cliente.getCPF().equals(cpfcliente)){
                System.out.println("Digite o novo nome do cliente: ");
                String novoNome = sc.nextLine();
                cliente.setNome(novoNome);

                System.out.println("Digite o novo telefone do cliente: ");
                String novoTelefone = sc.nextLine();
                String telefoneFormatado = formatarTelefone(novoTelefone);
                cliente.setTelefone(telefoneFormatado);

                System.out.println("Digite a nova data de nascimento do cliente (ddmmaaaa): ");
                String novaDataNascimento = sc.nextLine();
                String dataFormatada = formatarData(novaDataNascimento);
                cliente.setNascimento(dataFormatada);

                System.out.println("Digite o novo sexo do cliente (M/F): ");
                char novoSexo = sc.next().charAt(0);
                cliente.setSexo(novoSexo);

                System.out.println("Dados do cliente atualizados com sucesso.");
                return;
            }
        }
    }
    public void DevolverLivro(BibliotecaC biblioteca, EmpregadoC empregado){
        if (SemClientes()) {
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o CPF do cliente que deseja devolver o livro: ");
        String cpfcliente = sc.nextLine();
        String cpfFormatado = formatarCPF(cpfcliente);

        for(ClienteM cliente : vetClientes){
            if(cliente.getCPF().equals(cpfFormatado)){
                System.out.println("Digite o nome do livro a ser devolvido: ");
                String nomelivro = sc.nextLine();
                for(Livros livro : biblioteca.getVetBiblioteca()){
                    if(livro.getNomeDoLivro().equals(nomelivro)){
                        livro.setQuantidade(livro.getQuantidade()+1);
                        for(int i = 0; i < empregado.vetEmprestimo.size(); i++){
                            if(empregado.vetEmprestimo.get(i).contains(cliente.getNome()) && empregado.vetEmprestimo.get(i).contains(livro.getNomeDoLivro())){
                                empregado.vetEmprestimo.remove(i);
                                System.out.println("Livro devolvido com sucesso.");
                                return;
                            }
                        }
                        System.out.println("Livro não encontrado nos empréstimos do cliente.");
                    }
                }
            }
       }
    }
    public void ConsultarEmprestimoPorCPF(EmpregadoC empregado){
        if (SemClientes()) {
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o CPF do cliente que deseja consultar os empréstimos: ");
        String cpfCliente = sc.nextLine();
        String cpfFormatado = formatarCPF(cpfCliente);
        int encontrado = 0;
        for(String emprestimo : empregado.vetEmprestimo) {
            if (emprestimo.contains(cpfFormatado)) {
                System.out.println(emprestimo);
                encontrado++;
            }
        }
        if (encontrado == 0) {
            System.out.println("Nao existe emprestimo para o CPF informado.");
        }
    }
    private boolean SemClientes(){
        if (vetClientes.isEmpty()){
            System.out.println("Sem clintes cadastrados");
            return true;
        }
        return false;
    }
}