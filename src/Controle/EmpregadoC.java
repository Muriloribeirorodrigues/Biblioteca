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

        String NomeCompleto = ValidadorM.lerTextoSemNumero(sc, "Nome: ");

        String telefone = ValidadorM.lerTelefone(sc, "Telefone: ");

        String nascimento = ValidadorM.lerData(sc, "Data de nascimento: ");

        String cargo = ValidadorM.lerTextoSemNumero(sc, "Cargo: ");

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
                case 1 -> {empregado.setNome(ValidadorM.lerTextoSemNumero(sc, "Digite o novo nome do empregado: "));
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
                case 6 -> {empregado.setNome(ValidadorM.lerTextoSemNumero(sc, "Digite o novo nome do empregado: "));
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
    public void EmprestarLivros(BibliotecaC biblioteca, ClienteC clientes, EmprestimoC emprestimo, Scanner sc) {

        if (biblioteca == null || clientes == null || emprestimo == null) {
            System.out.println("Erro ao acessar os cadastros");
            return;
        }

        if (biblioteca.getVetBiblioteca().isEmpty()) {
            System.out.println("Nao existem livros cadastrados");
            return;
        }

        String cpf = ValidadorM.lerCPF(sc, "Digite o CPF do empregado: ");

        EmpregadoM empregadoSelecionado = BuscarEmpregadoPorCPF(cpf);

        if (empregadoSelecionado == null) {
            System.out.println("Empregado nao encontrado ou inativo");
            return;
        }

        System.out.println("Livros disponiveis para emprestimo:");

        for (Livro livro : biblioteca.getVetBiblioteca()) {
            if (livro.getQuantidade() > 0) {
                System.out.println("- " + livro.getNomeDoLivro());
            }
        }

        String nomeLivro = ValidadorM.lerTextoObrigatorio(
            sc, "Digite o nome do livro que deseja emprestar: "
        );

        Livro livroSelecionado = null;

        for (Livro livro : biblioteca.getVetBiblioteca()) {
            if (livro.getNomeDoLivro().equalsIgnoreCase(nomeLivro)) {
                livroSelecionado = livro;
                break;
            }
        }

        if (livroSelecionado == null) {
            System.out.println("Livro nao encontrado");
            return;
        }

        if (livroSelecionado.getQuantidade() <= 0) {
            System.out.println("Livro sem exemplares disponiveis");
            return;
        }

        String cpfCliente = ValidadorM.lerCPF(
            sc, "Digite o CPF do cliente: "
        );

        ClienteM clienteSelecionado = clientes.BuscarPorCPF(cpfCliente);

        if (clienteSelecionado == null) {
            System.out.println("Cliente nao encontrado ou inativo");
            return;
        }

        LocalDateTime dataEmprestimo = LocalDateTime.now();

        EmprestimoM novoEmprestimo = new EmprestimoM(
            clienteSelecionado,
            empregadoSelecionado,
            livroSelecionado,
            dataEmprestimo,
            dataEmprestimo.plusDays(14),
            null
        );

        emprestimo.getVetEmprestimo().add(novoEmprestimo);

        livroSelecionado.setQuantidade(
            livroSelecionado.getQuantidade() - 1
        );

        System.out.println("Emprestimo realizado com sucesso!");
    }
    public void ListarEmprestimosAtivos(EmprestimoC emprestimos){
        if (SemEmprestimo(emprestimos)){
            return;
        }
        int encontrados = 0;
        for (EmprestimoM emprestimo : emprestimos.getVetEmprestimo()) {
            if (emprestimo.isAtivo() && emprestimo.getCliente().isAtivo()) {
                System.out.println(emprestimo);
                encontrados++;
            }
        }
        if(encontrados == 0){
            System.out.println("Sem emprestimos ativos");
        }
    }
    public void HistoricoDeEmprestimos(EmprestimoC emprestimos){
        if(SemEmprestimo(emprestimos)){
            return;
        }
        for (EmprestimoM emprestimo : emprestimos.getVetEmprestimo()){
            System.out.println(emprestimo);
        }
    }
    public void ListarEmprestimos(Scanner sc, EmprestimoC emprestimos){
        if(SemEmprestimo(emprestimos)){
            return;
        }
        int opcao = 0;
        while(opcao!=3){
            System.out.println("1 - Listar emprestimos ativos");
            System.out.println("2 - Mostrar Historico de emprestimos");
            System.out.println("3 - Voltar");

            opcao = ValidadorM.lerInt(sc);

            switch(opcao){
                case 1 -> ListarEmprestimosAtivos(emprestimos);
                case 2 -> HistoricoDeEmprestimos(emprestimos);
                case 3 -> System.out.println("Voltar");
                default -> System.out.println("Opcao invalida");
            }
        }
    }
    public void ConsultarEmprestimosAtivosPorCPF(EmprestimoC emprestimos, Scanner sc){
        if (SemEmprestimo(emprestimos)){
            return;
        }
        String cpfFormatado = ValidadorM.lerCPF(sc, "Digite o cpf que deseja consultar emprestimos");
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
    public void ConsultarHistoricoDeEmprestimoPorCPF(EmprestimoC emprestimos, Scanner sc){
        if (SemEmprestimo(emprestimos)){
            return;
        }
        String cpfFormatado = ValidadorM.lerCPF(sc, "Digite o cpf que deseja consultar emprestimos");
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
    public void ConsultarEmprestimoPorCPF(EmprestimoC emprestimos, Scanner sc){
        if(SemEmprestimo(emprestimos)){
            return;
        }
        int opcao = 0;
        while(opcao !=3){
            System.out.println("1 - Listar emprestimos ativos de um cpf");
            System.out.println("2 - Listar historico de emprestimos de um cpf");
            System.out.println("3 - Voltar");

            opcao = ValidadorM.lerInt(sc);
            switch(opcao){
                case 1 -> ConsultarEmprestimosAtivosPorCPF(emprestimos, sc);
                case 2 -> ConsultarHistoricoDeEmprestimoPorCPF(emprestimos, sc);
                case 3 -> System.out.println("Voltar");
                default -> System.out.println("Opcao invalida");
            }
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
