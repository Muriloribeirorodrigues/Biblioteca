package Controle;
import BibliotecaM.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

public class EmpregadoC {
    public ArrayList<EmpregadoM> vetEmpregado;



    public EmpregadoC(){
        vetEmpregado = new ArrayList<EmpregadoM>();

    }

    public void CadastroDadosPessoais(EmpregadoM dpm){
        vetEmpregado.add(dpm);
    }

    public void CadastrarEmpregado(Scanner sc){

        System.out.println("Nome: ");
        String NomeCompleto = sc.nextLine();

        System.out.println("Telefone: ");
        String telefoneentrada = sc.nextLine();
        String telefone = ValidadorM.formatarTelefone(telefoneentrada);
        for (EmpregadoM empregado : vetEmpregado){
            if(empregado.getTelefone().equals(telefone)){
                System.out.println("Telefone já cadastrado");
                return;
            }
        }

        System.out.println("Cpf: ");
        String cpfentrada = sc.nextLine();
        String CPF = ValidadorM.formatarCPF(cpfentrada);
        for (EmpregadoM empregado : vetEmpregado){
            if(empregado.getCPF().equals(CPF)){
                System.out.println("Telefone já cadastrado");
                return;
            }
        }


        System.out.println("DataNascimento :");
        String nascimentoentrada = sc.nextLine();
        String nascimento = ValidadorM.formatarData(nascimentoentrada);

        System.out.println("Cargo :");
        String cargo = sc.nextLine();

        System.out.println("Sexo :");
        char sexo = sc.next().charAt(0);

        EmpregadoM NovoEmpregado = new EmpregadoM(NomeCompleto, telefone, CPF, nascimento, cargo, sexo);

        vetEmpregado.add(NovoEmpregado);

        System.out.println("Empregado cadastrado");
    }

    public void ListarEmpregado(){
        if(SemEmpregado()){
            return;
        }
        for (EmpregadoM empregado : vetEmpregado){
            empregado.PrintEmpregado();
        }
    }
    public void BuscarEmpregado(Scanner sc){
        if(SemEmpregado()){
            return;
        }
        System.out.println("Digite o nome do empregado que deseja buscar: ");
        String nomeEmpregado = sc.nextLine();
        for (EmpregadoM empregado : vetEmpregado) {
            if (empregado.getNome().equalsIgnoreCase(nomeEmpregado)) {
                empregado.PrintEmpregado();
                return;
            }
        }
        System.out.println("Empregado não encontrado.");
    }
    public void RemoveEmpregado(Scanner sc){
        if(SemEmpregado()){
            return;
        }
        System.out.println("Digite o nome do empregado que deseja remover: ");
        String nomeEmpregado = sc.nextLine();
        for (int i = 0; i < vetEmpregado.size(); i++) {
            if (vetEmpregado.get(i).getNome().equalsIgnoreCase(nomeEmpregado)) {
                vetEmpregado.remove(i);
                System.out.println("Empregado removido com sucesso!");
                return;
            }
        }
    }
    public void AtualizarDadosEmpregado(Scanner sc){
        if(SemEmpregado()){
            return;
        }
        System.out.println("Digite o nome do empregado que deseja atualizar: ");
        String nomeEmpregado = sc.nextLine();
        for (EmpregadoM empregado : vetEmpregado) {
            if (empregado.getNome().equalsIgnoreCase(nomeEmpregado)) {
                System.out.println("Digite o novo nome do empregado: ");
                String novoNome = sc.nextLine();
                empregado.setNome(novoNome);

                System.out.println("Digite o novo telefone do empregado: ");
                String telefoneentrada = sc.nextLine();
                String novoTelefone = ValidadorM.formatarTelefone(telefoneentrada);
                empregado.setTelefone(novoTelefone);

                System.out.println("Digite o novo CPF do empregado: ");
                String cpfentrada = sc.nextLine();
                String novoCPF = ValidadorM.formatarCPF(cpfentrada);
                empregado.setCPF(novoCPF);

                System.out.println("Digite a nova data de nascimento do empregado: ");
                String nascimentoentrada = sc.nextLine();
                String novaDataNascimento = ValidadorM.formatarData(nascimentoentrada);
                empregado.setNascimento(novaDataNascimento);

                System.out.println("Digite o novo cargo do empregado: ");
                String novoCargo = sc.nextLine();
                empregado.setCargo(novoCargo);

                System.out.println("Digite o novo sexo do empregado: ");
                char novoSexo = sc.next().charAt(0);
                empregado.setSexo(novoSexo);

                System.out.println("Dados atualizados com sucesso!");
                return;
            }
        }
    }   
    public void EmprestarLivros(BibliotecaC biblioteca, ClienteC clientes, EmprestimoC emprestimo, Scanner sc){
        if (vetEmpregado.isEmpty() || biblioteca.getVetBiblioteca().isEmpty() || clientes.getVetClientes().isEmpty()) {
            System.out.println("Sem empregados, livros ou clientes cadastrados");
            return;
        }
        System.out.println("Digite o nome do empregado que deseja realizar o empréstimo: ");
        String nomeEmpregado = sc.nextLine();
        for (EmpregadoM empregado : vetEmpregado) {
            if (empregado.getNome().equalsIgnoreCase(nomeEmpregado)) {
                System.out.println("Livros disponiveis para emprestimo: ");
                for (Livro livro : biblioteca.getVetBiblioteca()) {
                    System.out.println("- " + livro.getNomeDoLivro());
                }
                System.out.println("Digite o nome do livro que deseja emprestar: ");
                String nomeLivro = sc.nextLine();
                for (Livro livro : biblioteca.getVetBiblioteca()) {
                    if (livro.getNomeDoLivro().equalsIgnoreCase(nomeLivro)) {
                        System.out.println("Digite o CPF do cliente que deseja realizar o empréstimo: ");
                        String cpfCliente = sc.nextLine();
                        String cpfFormatado = ValidadorM.formatarCPF(cpfCliente);
                        for (ClienteM cliente : clientes.getVetClientes()) {
                            if (cliente.getCPF().equals(cpfFormatado)) {
                                if (livro.getQuantidade() > 0) {
                                    livro.setQuantidade(livro.getQuantidade() - 1);

                                        LocalDateTime dataEmprestimo = LocalDateTime.now();
                                        emprestimo.vetEmprestimo.add(new EmprestimoM(
                                            cliente, empregado, livro, dataEmprestimo, dataEmprestimo.plusDays(14)));
                                    System.out.println("Empréstimo realizado com sucesso!");
                                    return;
                                } else {
                                    System.out.println("Livro indisponível para empréstimo.");
                                    return;
                                }
                            }
                        }
                        System.out.println("Cliente não encontrado.");
                        return;
                    }
                }
                System.out.println("Livro não encontrado.");
                return;
            }
        }
    }
    public void ListarEmprestimo(EmprestimoC emprestimos){
        if (SemEmprestimo(emprestimos)){
            return;
        }
        System.out.println("Empréstimos realizados:");
        for (EmprestimoM emprestimo : emprestimos.vetEmprestimo) {
            System.out.println(emprestimo);
        }
    }
    public void ConsultarEmprestimoPorCPF(EmprestimoC emprestimos, Scanner sc){
        if (SemEmprestimo(emprestimos)){
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
        if(encontrado == 0){
        System.out.println("Nao existe emprestimo para o CPF informado.");
        }
    }
    private boolean SemEmprestimo(EmprestimoC emprestimos){
        if (emprestimos.vetEmprestimo.isEmpty()){
            System.out.println("Sem emprestimos realizados");
            return true;
        }
        return false;
    }
    private boolean SemEmpregado(){
        if (vetEmpregado.isEmpty()){
            System.out.println("Sem empregados cadastrados");
            return true;
        }
        return false;
    }
}
