package Controle;
import BibliotecaM.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

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

        String NomeCompleto = ValidadorM.lerTextoObrigatorio(sc, "Nome: ");

        String telefone = ValidadorM.lerTelefone(sc, "Telefone");

        String CPF = ValidadorM.lerCPF(sc, "CPF: ");
        for (ClienteM cliente : vetClientes){
            if(cliente.getCPF().equals(CPF)){
                System.out.println("CPF já cadastrado");
                return;
            }
        }

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
        String cpf = ValidadorM.lerCPF(sc, "Digite o cpf do cliente que deseja remover: ");
        for (int i = 0; i < vetClientes.size(); i++) {
            if (vetClientes.get(i).getCPF().equalsIgnoreCase(cpf)) {
                vetClientes.remove(i);
                System.out.println("Cliente removido com sucesso!");
                return;
            }
        }
        System.out.println("Cliente nao encontrado");
    }

    public void AtualizarDadosCliente(Scanner sc){
        if (SemClientes()) {
            return;
        }
        String cpfcliente = ValidadorM.lerCPF(sc,"Digite o CPF do cliente que deseja atualizar: ");
        for(ClienteM cliente : vetClientes){
            if(cliente.getCPF().equals(cpfcliente)){
                String novoNome = ValidadorM.lerTextoObrigatorio(sc,"Digite o novo nome do cliente: ");
                cliente.setNome(novoNome);

                String novoTelefone = ValidadorM.lerTelefone(sc,"Digite o novo telefone do cliente: ");
                cliente.setTelefone(novoTelefone);

                String novaDataNascimento = ValidadorM.lerData(sc,"Digite a nova data de nascimento do cliente: ");
                cliente.setNascimento(novaDataNascimento);

                char novoSexo = ValidadorM.lerSexo(sc,"Digite o novo sexo do cliente M/F: ");
                cliente.setSexo(novoSexo);

                System.out.println("Dados do cliente atualizados com sucesso.");
                return;
            }
        }
        System.out.println("Cliente nao encontrado");
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
            System.out.println("Sem clientes cadastrados");
            return true;
        }
        return false;
    }
}