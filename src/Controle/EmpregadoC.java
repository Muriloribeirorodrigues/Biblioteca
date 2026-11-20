package Controle;
import BibliotecaM.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

public class EmpregadoC {
    private ArrayList<EmpregadoM> vetEmpregado;



    public EmpregadoC(){
        vetEmpregado = new ArrayList<EmpregadoM>();

    }

    public void CadastroDadosPessoais(EmpregadoM dpm){
        vetEmpregado.add(dpm);
    }

    public void CadastrarEmpregado(Scanner sc){

        System.out.println("Nome: ");
        String NomeCompleto = sc.nextLine();

        String telefone = ValidadorM.lerTelefone(sc, "Telefone");

        String CPF = ValidadorM.lerCPF(sc, "CPF: ");
        for (EmpregadoM empregado : vetEmpregado){
            if(empregado.getCPF().equals(CPF)){
                System.out.println("CPF já cadastrado");
                return;
            }
        }

        String nascimento = ValidadorM.lerData(sc, "Data de nascimento");

        System.out.println("Cargo :");
        String cargo = sc.nextLine();

        char sexo = ValidadorM.lerSexo(sc, "Digite M (Masculino) ou F (Feminino)");

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

        String cpf = ValidadorM.lerCPF(sc, "Digite o cpf do empregado que deseja remover: ");
        for (int i = 0; i < vetEmpregado.size(); i++) {
            if (vetEmpregado.get(i).getCPF().equalsIgnoreCase(cpf)) {
                vetEmpregado.remove(i);
                System.out.println("Empregado removido com sucesso!");
                return;
            }
        }
        System.out.println("Empregado nao encontrado");
    }
    public void AtualizarDadosEmpregado(Scanner sc){
        if(SemEmpregado()){
            return;
        }
        System.out.println("Digite o cpf do empregado que deseja atualizar: ");
        String CPF = ValidadorM.formatarCPF(sc.nextLine());
        for (EmpregadoM empregado : vetEmpregado) {
            if (empregado.getCPF().equalsIgnoreCase(CPF)) {
                System.out.println("Digite o novo nome do empregado: ");
                String novoNome = sc.nextLine();
                empregado.setNome(novoNome);
                
                String novoTelefone = ValidadorM.lerTelefone(sc, "Digite o novo telefone do empregado: ");
                empregado.setTelefone(novoTelefone);

                String novaDataNascimento = ValidadorM.lerData(sc, "Digite a nova data de nascimento do empregado: ");
                empregado.setNascimento(novaDataNascimento);

                System.out.println("Digite o novo cargo do empregado: ");
                String novoCargo = sc.nextLine();
                empregado.setCargo(novoCargo);

                
                char novoSexo = ValidadorM.lerSexo(sc, "Digite o novo sexo do empregado M/F: ");
                empregado.setSexo(novoSexo);

                System.out.println("Dados atualizados com sucesso!");
                return;
            }
        }
        System.out.println("Empregado nao encontrado");
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
                                        emprestimo.getVetEmprestimo().add(new EmprestimoM(
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
        System.out.println("Empregado não encontrado.");
    }
    public void ListarEmprestimo(EmprestimoC emprestimos){
        if (SemEmprestimo(emprestimos)){
            return;
        }
        System.out.println("Empréstimos realizados:");
        for (EmprestimoM emprestimo : emprestimos.getVetEmprestimo()) {
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
        for(EmprestimoM emprestimo : emprestimos.getVetEmprestimo()) {
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
        if (emprestimos.getVetEmprestimo().isEmpty()){
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
