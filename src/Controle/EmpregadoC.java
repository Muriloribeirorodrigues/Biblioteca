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

        String NomeCompleto = ValidadorM.lerTextoObrigatorio(sc, "Nome: ");

        String telefone = ValidadorM.lerTelefone(sc, "Telefone");

        String CPF = ValidadorM.lerCPF(sc, "CPF: ");
        for (EmpregadoM empregado : vetEmpregado){
            if(empregado.getCPF().equals(CPF)){
                System.out.println("CPF já cadastrado");
                return;
            }
        }

        String nascimento = ValidadorM.lerData(sc, "Data de nascimento");

        String cargo = ValidadorM.lerTextoObrigatorio(sc, "Cargo: ");

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
        String cpf = ValidadorM.lerCPF(sc, "Digite o cpf do empregado que deseja buscar: ");
        for (EmpregadoM empregado : vetEmpregado) {
            if (empregado.getCPF().equalsIgnoreCase(cpf)) {
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
        int opcao = 0;
        while (opcao != 6){
            System.out.println("1 - Atualizar nome do empregado");
            System.out.println("2 - Atualizar telefone do empregado");
            System.out.println("3 - Atualizar data de nascimento do empregado");
            System.out.println("4 - Atualizar sexo do empregado");
            System.out.println("5 - Atualizar cargo do empregado");
            System.out.println("6 - Alterar tudo");
            System.out.println("7 - Voltar");

            switch(opcao){
                case 1 -> AlterarNome(sc);
                case 2 -> AlterarTelefone(sc);
                case 3 -> AlterarDataNascimento(sc);
                case 4 -> AlterarSexo(sc);
                case 5 -> AlterarCargo(sc);
                case 6 -> AlterarTudo(sc);
                case 7 -> System.out.println("Voltar");
                default -> System.out.println("Opcao invalida");
            }
        }
    }   
    public void EmprestarLivros(BibliotecaC biblioteca, ClienteC clientes, EmprestimoC emprestimo, Scanner sc){
        if (vetEmpregado.isEmpty() || biblioteca.getVetBiblioteca().isEmpty() || clientes.getVetClientes().isEmpty()) {
            System.out.println("Sem empregados, livros ou clientes cadastrados");
            return;
        }
        String cpf = ValidadorM.lerCPF(sc, "Digite o cpf do empregado que deseja realizar o empréstimo: ");
        for (EmpregadoM empregado : vetEmpregado) {
            if (empregado.getCPF().equalsIgnoreCase(cpf)) {
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
                                            cliente, empregado, livro, dataEmprestimo, dataEmprestimo.plusDays(14), null));
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
        for (EmprestimoM emprestimo : emprestimos.getVetEmprestimo()) {
            if (emprestimo.isAtivo()) {
                System.out.println(emprestimo);
            }
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
    public void AlterarNome(Scanner sc){
        String cpfempregado = ValidadorM.lerCPF(sc,"Digite o CPF do cliente que deseja atualizar: ");
        for(EmpregadoM empragado : vetEmpregado){
            if(empragado.getCPF().equals(cpfempregado)){
                String novoNome = ValidadorM.lerTextoObrigatorio(sc,"Digite o novo nome do empragado: ");
                empragado.setNome(novoNome);
            }
            else{
                System.out.println("Empregado nao encontrado");
                return;
            }
        }
    }
    public void AlterarTelefone(Scanner sc){
        String cpfempragado = ValidadorM.lerCPF(sc,"Digite o CPF do empragado que deseja atualizar: ");
        for(EmpregadoM empragado : vetEmpregado){
            if(empragado.getCPF().equals(cpfempragado)){
                String novotelefone = ValidadorM.lerTelefone(sc,"Digite o novo telefone do empragado: ");
                empragado.setTelefone(novotelefone);
            }
            else{
                System.out.println("Empregado nao encontrado");
                return;
            }
        }
    }
    public void AlterarDataNascimento(Scanner sc){
        String cpfempragado = ValidadorM.lerCPF(sc,"Digite o CPF do empragado que deseja atualizar: ");
        for(EmpregadoM empragado : vetEmpregado){
            if(empragado.getCPF().equals(cpfempragado)){
                String novoNascimento = ValidadorM.lerData(sc,"Digite a nova data de nascimento do empragado: ");
                empragado.setNascimento(novoNascimento);
            }
            else{
                System.out.println("Empregado nao encontrado");
                return;
            }
        }
    }
    public void AlterarSexo(Scanner sc){
        String cpfempragado = ValidadorM.lerCPF(sc,"Digite o CPF do empragado que deseja atualizar: ");
        for(EmpregadoM empragado : vetEmpregado){
            if(empragado.getCPF().equals(cpfempragado)){
                char novosexo = ValidadorM.lerSexo(sc,"Digite o novo sexo do empragado: ");
                empragado.setSexo(novosexo);
            }
            else{
                System.out.println("Empregado nao encontrado");
                return;
            }
        }
    }
    public void AlterarCargo(Scanner sc){
        String cpfempregado = ValidadorM.lerCPF(sc,"Digite o CPF do empregado que deseja atualizar: ");
        for(EmpregadoM empragado : vetEmpregado){
            if(empragado.getCPF().equals(cpfempregado)){
                String novocargo = ValidadorM.lerTextoObrigatorio(sc,"Digite o novo cargo do empragado: ");
                empragado.setCargo(novocargo);
            }
            else{
                System.out.println("Empregado nao encontrado");
                return;
            }
        }
    }
    public void AlterarTudo(Scanner sc){
        String cpfempregado = ValidadorM.lerCPF(sc,"Digite o CPF do empregado que deseja atualizar: ");
        for(EmpregadoM empragado : vetEmpregado){
            if(empragado.getCPF().equals(cpfempregado)){
                String novoNome = ValidadorM.lerTextoObrigatorio(sc,"Digite o novo nome do empragado: ");
                empragado.setNome(novoNome);
                String novotelefone = ValidadorM.lerTelefone(sc,"Digite o novo telefone do empragado: ");
                empragado.setTelefone(novotelefone);
                String novoNascimento = ValidadorM.lerData(sc,"Digite a nova data de nascimento do empragado: ");
                empragado.setNascimento(novoNascimento);
                char novosexo = ValidadorM.lerSexo(sc,"Digite o novo sexo do empragado: ");
                empragado.setSexo(novosexo);
                String novocargo = ValidadorM.lerTextoObrigatorio(sc,"Digite o novo cargo do empragado: ");
                empragado.setCargo(novocargo);
            }
            else{
                System.out.println("Empregado nao encontrado");
                return;
            }
        }
    }
}
