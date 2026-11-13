package Controle;
import BibliotecaM.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EmpregadoC {
    public ArrayList<EmpregadoM> vetEmpregado;

    public ArrayList<String> vetEmprestimo;

    public EmpregadoC(){
        vetEmpregado = new ArrayList<EmpregadoM>();
        vetEmprestimo = new ArrayList<String>();
    }

    public void CadastroDadosPessoais(EmpregadoM dpm){
        vetEmpregado.add(dpm);
    }

    public void CadastrarEmpregado(){
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

        System.out.println("Cargo :");
        String cargo = sc.nextLine();

        System.out.println("Sexo :");
        char sexo = sc.next().charAt(0);

        EmpregadoM NovoEmpregado = new EmpregadoM(NomeCompleto, telefone, CPF, nascimento, cargo, sexo);

        vetEmpregado.add(NovoEmpregado);

        System.out.println("Empregado cadastrado");
    }
    private String formatarCPF(String cpf){
        String cpfFormatado = cpf.replaceAll("[^\\d]", "");
        if(cpfFormatado.length() == 11){
            return cpfFormatado.substring(0,3) + "." + cpfFormatado.substring(3,6) + "." + cpfFormatado.substring(6,9) + "-" + cpfFormatado.substring(9,11);
        }
        else if (cpfFormatado.length() != 11) {
            throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos.");
        }
        return cpfFormatado;
    }
    private String formatarData(String data){
        String dataformatada = data.replaceAll("[^\\d]", "");
        if(dataformatada.length() == 8){
            return dataformatada.substring(0,2) + "/" + dataformatada.substring(2,4) + "/" + dataformatada.substring(4,8);
        }
        else {
            throw new IllegalArgumentException("Data inválida. Deve conter 8 dígitos.");
        }
    }
    private String formatarTelefone(String telefone){
        String telefoneFormatado = telefone.replaceAll("[^\\d]", "");
        if(telefoneFormatado.length() == 11){
            return "(" + telefoneFormatado.substring(0,2) + ")" + telefoneFormatado.substring(2,7) + "-" + telefoneFormatado.substring(7,11);
        }
        throw new IllegalArgumentException("Telefone inválido. Deve conter 11 dígitos.");
    }
    public void ListarEmpregado(){
        if(SemEmpregado()){
            return;
        }
        for (EmpregadoM empregado : vetEmpregado){
            empregado.PrintEmpregado();
        }
    }
    public void BuscarEmpregado(){
        if(SemEmpregado()){
            return;
        }
        Scanner sc = new Scanner(System.in);
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
    public void RemoveEmpregado(){
        if(SemEmpregado()){
            return;
        }
        Scanner sc = new Scanner(System.in);
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
    public void AtualizarDadosEmpregado(){
        if(SemEmpregado()){
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do empregado que deseja atualizar: ");
        String nomeEmpregado = sc.nextLine();
        for (EmpregadoM empregado : vetEmpregado) {
            if (empregado.getNome().equalsIgnoreCase(nomeEmpregado)) {
                System.out.println("Digite o novo nome do empregado: ");
                String novoNome = sc.nextLine();
                empregado.setNome(novoNome);

                System.out.println("Digite o novo telefone do empregado: ");
                String telefoneentrada = sc.nextLine();
                String novoTelefone = formatarTelefone(telefoneentrada);
                empregado.setTelefone(novoTelefone);

                System.out.println("Digite o novo CPF do empregado: ");
                String cpfentrada = sc.nextLine();
                String novoCPF = formatarCPF(cpfentrada);
                empregado.setCPF(novoCPF);

                System.out.println("Digite a nova data de nascimento do empregado: ");
                String nascimentoentrada = sc.nextLine();
                String novaDataNascimento = formatarData(nascimentoentrada);
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
    public void EmprestarLivros(BibliotecaC biblioteca, ClienteC clientes){
        if (vetEmpregado.isEmpty() || biblioteca.getVetBiblioteca().isEmpty() || clientes.getVetClientes().isEmpty()) {
            System.out.println("Sem empregados, livros ou clientes cadastrados");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do empregado que deseja realizar o empréstimo: ");
        String nomeEmpregado = sc.nextLine();
        for (EmpregadoM empregado : vetEmpregado) {
            if (empregado.getNome().equalsIgnoreCase(nomeEmpregado)) {
                System.out.println("Livros disponiveis para emprestimo: ");
                for (Livros livro : biblioteca.getVetBiblioteca()) {
                    System.out.println("- " + livro.getNomeDoLivro());
                }
                System.out.println("Digite o nome do livro que deseja emprestar: ");
                String nomeLivro = sc.nextLine();
                for (Livros livro : biblioteca.getVetBiblioteca()) {
                    if (livro.getNomeDoLivro().equalsIgnoreCase(nomeLivro)) {
                        System.out.println("Digite o CPF do cliente que deseja realizar o empréstimo: ");
                        String cpfCliente = sc.nextLine();
                        String cpfFormatado = formatarCPF(cpfCliente);
                        for (ClienteM cliente : clientes.getVetClientes()) {
                            if (cliente.getCPF().equals(cpfFormatado)) {
                                if (livro.getQuantidade() > 0) {
                                    livro.setQuantidade(livro.getQuantidade() - 1);

                                    vetEmprestimo.add("Empregado: " + empregado.getNome() + ", Livro: " + livro.getNomeDoLivro() + ", Cliente: " + cliente.getNome() + ", CPF: " + cliente.getCPF());
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
    public void ListarEmprestimo(){
        if (SemEmprestimo()){
            return;
        }
        System.out.println("Empréstimos realizados:");
        for (String emprestimo : vetEmprestimo) {
            System.out.println(emprestimo);
        }
    }
    public void ConsultarEmprestimoPorCPF(){
        if (SemEmprestimo()){
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o CPF do cliente que deseja consultar os empréstimos: ");
        String cpfCliente = sc.nextLine();
        String cpfFormatado = formatarCPF(cpfCliente);
        int encontrado = 0;
        for(String emprestimo : vetEmprestimo) {
            if (emprestimo.contains(cpfFormatado)) {
                System.out.println(emprestimo);
                encontrado++;
            }
        }
        if(encontrado == 0){
        System.out.println("Nao existe emprestimo para o CPF informado.");
        }
    }
    private boolean SemEmprestimo(){
        if (vetEmprestimo.isEmpty()){
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
