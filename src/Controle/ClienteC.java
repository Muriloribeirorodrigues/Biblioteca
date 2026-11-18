package Controle;
import BibliotecaM.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

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

    public void CadastrarCliente(Scanner sc){

        System.out.println("Nome: ");
        String NomeCompleto = sc.nextLine();

        System.out.println("Telefone: ");
        String telefoneentrada = sc.nextLine();
        String telefone = ValidadorM.formatarTelefone(telefoneentrada);
        for (ClienteM cliente : vetClientes){
            if(cliente.getTelefone().equals(telefone)){
                System.out.println("Telefone já cadastrado");
                return;
            }
        }

        System.out.println("Cpf: ");
        String cpfentrada = sc.nextLine();
        String CPF = ValidadorM.formatarCPF(cpfentrada);
        for (ClienteM cliente : vetClientes){
            if(cliente.getCPF().equals(CPF)){
                System.out.println("CPF já cadastrado");
                return;
            }
        }

        System.out.println("DataNascimento :");
        String nascimentoentrada = sc.nextLine();
        String nascimento = ValidadorM.formatarData(nascimentoentrada);

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

    public void BuscarClientePorCPF(Scanner sc){
        if (SemClientes()) {
            return;
        }
        System.out.println("Digite o CPF do cliente que deseja buscar: ");
        String cpfcliente = sc.nextLine();
        String cpfFormatado = ValidadorM.formatarCPF(cpfcliente);
        for(ClienteM cliente : vetClientes){
            if(cliente.getCPF().equals(cpfFormatado)){
                cliente.PrintCliente();
                return;
            }
        }
        System.out.println("Cliente não encontrado.");
    }
    public void RemoverClientePorCPF(Scanner sc){
        if (SemClientes()) {
            return;
        }
        System.out.println("Digite o CPF do cliente que deseja remover: ");
        String cpfcliente = sc.nextLine();
        String cpfFormatado = ValidadorM.formatarCPF(cpfcliente);
        for(ClienteM cliente : vetClientes){
            if(cliente.getCPF().equals(cpfFormatado)){
                vetClientes.remove(cliente);
                System.out.println("Cliente removido com sucesso.");
                return;
            }
        }
    }
    public void AtualizarDadosCliente(Scanner sc){
        if (SemClientes()) {
            return;
        }
        System.out.println("Digite o CPF do cliente que deseja atualizar: ");
        String cpfcliente = sc.nextLine();
        for(ClienteM cliente : vetClientes){
            if(cliente.getCPF().equals(cpfcliente)){
                System.out.println("Digite o novo nome do cliente: ");
                String novoNome = sc.nextLine();
                cliente.setNome(novoNome);

                System.out.println("Digite o novo telefone do cliente: ");
                String novoTelefone = sc.nextLine();
                String telefoneFormatado = ValidadorM.formatarTelefone(novoTelefone);
                cliente.setTelefone(telefoneFormatado);

                System.out.println("Digite a nova data de nascimento do cliente (ddmmaaaa): ");
                String novaDataNascimento = sc.nextLine();
                String dataFormatada = ValidadorM.formatarData(novaDataNascimento);
                cliente.setNascimento(dataFormatada);

                System.out.println("Digite o novo sexo do cliente (M/F): ");
                char novoSexo = sc.next().charAt(0);
                cliente.setSexo(novoSexo);

                System.out.println("Dados do cliente atualizados com sucesso.");
                return;
            }
        }
    }
    public void DevolverLivro(BibliotecaC biblioteca, EmpregadoC empregado, EmprestimoC emprestimos, Scanner sc){
        if (SemClientes()) {
            return;
        }
        if(emprestimos.vetEmprestimo.isEmpty()){
            System.out.println("Não ha emprestimos registrados");
            return;
        }
        System.out.println("Digite o CPF do cliente que deseja devolver o livro: ");
        String cpfcliente = sc.nextLine();
        String cpfFormatado = ValidadorM.formatarCPF(cpfcliente);

        for(int i = 0; i < emprestimos.vetEmprestimo.size();i++){
            EmprestimoM emprestimo = emprestimos.vetEmprestimo.get(i);
            if(emprestimo.getCliente().getCPF().equals(cpfFormatado)){
                System.out.println("Digite o nome do livro que deseja devolver: ");
                String nomelivro = sc.nextLine();
                if(emprestimo.getLivro().getNomeDoLivro().equalsIgnoreCase(nomelivro)){
                    LocalDateTime dataDevolucaoReal = LocalDateTime.now();
                    if(emprestimo.verificarAtraso(dataDevolucaoReal)){
                        long diasAtraso = emprestimo.calcularDiasAtraso(dataDevolucaoReal);
                        System.out.println("Livro devolvido com atraso de " + diasAtraso + " dias");
                    }
                    else{
                        System.out.println("Livro devolvido dentro do prazo)");
                    }
                    for(Livro livro : biblioteca.vetBiblioteca){
                        if(livro.getNomeDoLivro().equalsIgnoreCase(nomelivro)){
                            livro.setQuantidade(livro.getQuantidade() + 1);
                            emprestimos.vetEmprestimo.remove(i);
                            System.out.println("Livro devolvido com sucesso.");
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("Emprestimo nao encontrado para esse cliente");
    }
    public void ConsultarEmprestimoPorCPF(EmpregadoC empregado, EmprestimoC emprestimos, Scanner sc){
        if (SemClientes()) {
            return;
        }
        if(emprestimos.vetEmprestimo.isEmpty()){
            System.out.println("Não ha emprestimos registrados");
            return;
        }
        System.out.println("Digite o CPF do cliente que deseja consultar os empréstimos: ");
        String cpfCliente = sc.nextLine();
        String cpfFormatado = ValidadorM.formatarCPF(cpfCliente);
        int encontrado = 0;
        for(EmprestimoM emprestimo : emprestimos.vetEmprestimo) {
            if (emprestimo.getCliente().getCPF().equals(cpfFormatado)) {
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