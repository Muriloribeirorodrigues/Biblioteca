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

        String CPF = ValidadorM.lerCPF(sc, "CPF: ");
        
        EmpregadoM existente = BuscaEmpregadosPorCPFIncluindoInativos(CPF);

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
                System.out.println("Empregado reativado com sucesso");
            }
            else{
                System.out.println("Operacao cancelada");
            }
            return;
        }

        String NomeCompleto = ValidadorM.lerTextoObrigatorio(sc, "Nome: ");

        String telefone = ValidadorM.lerTelefone(sc, "Telefone: ");

        String nascimento = ValidadorM.lerData(sc, "Data de nascimento: ");

        String cargo = ValidadorM.lerTextoObrigatorio(sc, "Cargo: ");

        char sexo = ValidadorM.lerSexo(sc, "Digite M (Masculino) ou F (Feminino): ");

        EmpregadoM NovoEmpregado = new EmpregadoM(NomeCompleto, telefone, CPF, nascimento, cargo, sexo);

        vetEmpregado.add(NovoEmpregado);

        System.out.println("Empregado cadastrado");
    }

    public void ListarEmpregado(){
        if(SemEmpregado()){
            return;
        }
        for (EmpregadoM empregado : vetEmpregado){
            if(empregado.isAtivo()){
            empregado.PrintEmpregado();
            }
        }
    }
    public void BuscarEmpregado(Scanner sc){
        if(SemEmpregado()){
            return;
        }
        String cpf = ValidadorM.lerCPF(sc, "Digite o cpf do empregado que deseja buscar: ");
        for (EmpregadoM empregado : vetEmpregado) {
            if (empregado.getCPF().equals(cpf) && empregado.isAtivo()) {
                empregado.PrintEmpregado();
                return;
            }
        }
        System.out.println("Empregado não encontrado.");
    }
    public EmpregadoM BuscarEmpregadoPorCPF(String cpf){
        for (EmpregadoM empregado : vetEmpregado) {
            if (empregado.getCPF().equals(cpf) && empregado.isAtivo()) {
                return empregado;
            }
        }
        return null;
    }
    private EmpregadoM BuscaEmpregadosPorCPFIncluindoInativos(String cpf){
        for(EmpregadoM empregado : vetEmpregado){
            if(empregado.getCPF().equals(cpf)){
                return empregado;
            }
        }
        return null;
    }
    public void RemoveEmpregado(Scanner sc, EmprestimoC emprestimos){
        if(SemEmpregado()){
            return;
        }

        String cpf = ValidadorM.lerCPF(sc, "Digite o cpf do empregado que deseja remover: ");
        EmpregadoM Empregado = BuscarEmpregadoPorCPF(cpf);
        if(Empregado == null){
            System.out.println("Empregado nao encontrado");
                return;
            }
        if(emprestimos.possuiEmprestimosAtivosEmpregado(cpf)){
            System.out.println("Empregado possui um emprestimo ativo e nao pode ser removido");
            return;
        }
        Empregado.desativar();
        System.out.println("Empregado removido com sucesso!");
    }
    public void AtualizarDadosEmpregado(Scanner sc){
        if(SemEmpregado()){
          return;
        }
            String cpfempregado = ValidadorM.lerCPF(sc,"Digite o CPF do empregado que deseja atualizar: ");
            EmpregadoM empregado = BuscarEmpregadoPorCPF(cpfempregado);
                if(empregado == null){
                    System.out.println("empregado nao encontrado");
                    return;
                }
            int opcao = 0;
            while (opcao != 7){
                System.out.println("1 - Atualizar nome do empregado");
                System.out.println("2 - Atualizar telefone do empregado");
                System.out.println("3 - Atualizar data de nascimento do empregado");
                System.out.println("4 - Atualizar sexo do empregado");
                System.out.println("5 - Atualizar cargo do empregado");
                System.out.println("6 - Alterar tudo");
                System.out.println("7 - Voltar");
                opcao = ValidadorM.lerInt(sc);

            switch(opcao){
                case 1 -> {empregado.setNome(ValidadorM.lerTextoObrigatorio(sc, "Digite o novo nome do empregado: "));
                           System.out.println("Alteracao feita com sucesso");
                          }
                case 2 -> {empregado.setTelefone(ValidadorM.lerTelefone(sc, "Digite o novo telefone do empregado: "));
                    System.out.println("Alteracao feita com sucesso");
                          }
                case 3 -> {empregado.setNascimento(ValidadorM.lerData(sc, "Digite a nova data de nascimento do empregado: "));
                    System.out.println("Alteracao feita com sucesso");
                          }
                case 4 -> {empregado.setSexo(ValidadorM.lerSexo(sc, "Digite o novo sexo do empregado: "));
                    System.out.println("Alteracao feita com sucesso");
                          }
                case 5 -> {empregado.setCargo(ValidadorM.lerTextoObrigatorio(sc, "Digite o novo cargo do empregado: "));
                    System.out.println("Alteracao feita com sucesso");
                          }
                case 6 -> {empregado.setNome(ValidadorM.lerTextoObrigatorio(sc, "Digite o novo nome do empregado: "));
                           empregado.setTelefone(ValidadorM.lerTelefone(sc, "Digite o novo telefone do empregado: "));
                           empregado.setNascimento(ValidadorM.lerData(sc, "Digite a nova data de nascimento do empregado: "));
                           empregado.setSexo(ValidadorM.lerSexo(sc, "Digite o novo Sexo do empregado: "));
                           empregado.setCargo(ValidadorM.lerTextoObrigatorio(sc, "Digite o novo cargo do empregado: "));
                           System.out.println("Alteracao feita com sucesso");
                          }
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
            if (empregado.getCPF().equalsIgnoreCase(cpf) && empregado.isAtivo()) {
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
                            if (cliente.getCPF().equals(cpfFormatado) && cliente.isAtivo()) {
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
            if (emprestimo.isAtivo() && emprestimo.getCliente().isAtivo()) {
                System.out.println(emprestimo);
            }
        }
        System.out.println("Sem emprestimos ativos");
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
            if (emprestimo.getCliente().getCPF().equals(cpfFormatado) && emprestimo.isAtivo() && emprestimo.getCliente().isAtivo()) {
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
        for(EmpregadoM empregado : vetEmpregado)
            if (empregado.isAtivo()){
                return false;
            }
        System.out.println("Sem empregados cadastrados");
        return true;
    }
    public void ReativarCadastro(Scanner sc){
        boolean encontrou = false;
        for(EmpregadoM Empregado : vetEmpregado){
            if(!Empregado.isAtivo()){
                Empregado.PrintEmpregado();
                encontrou = true;
            }
        }
        if(!encontrou){
            System.out.println("Nao existem Empregados inativos");
            return;
        }
        String cpf = ValidadorM.lerCPF(sc, "Digite o cpf que deseja reativar");
        for(EmpregadoM Empregado : vetEmpregado){
            if(Empregado.getCPF().equals(cpf)){
                if(Empregado.isAtivo()){
                System.out.println("Empregado ja possui cpf ativado");
                return;
                }
            Empregado.ativar();
            System.out.println("Empregado reativado com sucesso");
            return;
            }
        }
        System.out.println("Nao existe esse cpf no sistema");
    }
}
