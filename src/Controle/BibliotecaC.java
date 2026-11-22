package Controle;
import BibliotecaM.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaC {
    private ArrayList<Livro> vetBiblioteca;

    public ArrayList<Livro> getVetBiblioteca() {
        return vetBiblioteca;
    }

    public BibliotecaC(){
        vetBiblioteca = new ArrayList<Livro>();
    }

    public void CadastrarLivro(Scanner sc){
        String NomeDoLivro = ValidadorM.lerTextoObrigatorio(sc, "Nome do Livro: ");
        if(BuscarLivro(NomeDoLivro) != null){
            System.out.println("Nome de livro ja existente");
            return;
        }

        String Autor = ValidadorM.lerTextoObrigatorio(sc, "Autor");

        String Genero = ValidadorM.lerTextoObrigatorio(sc, "Genero");

        System.out.println("Quantidade: ");
        int Quantidade = ValidadorM.lerInt(sc);

        Livro novolivro = new Livro(NomeDoLivro, Autor, Genero, Quantidade);

        vetBiblioteca.add(novolivro);

        System.out.println("Livro cadastrado");
    }
    public void ListarLivro(){
        if(SemLivro()){
            return;
        }
        for (Livro biblioteca : vetBiblioteca){
            biblioteca.PrintBiblioteca();
        }
    }
    public void BuscarLivro(Scanner sc){
        if(SemLivro()){
            return;
        }
        System.out.println("Digite o nome do livro que deseja buscar: ");
        String nomeLivro = sc.nextLine();
        for (Livro livro : vetBiblioteca) {
            if (livro.getNomeDoLivro().equalsIgnoreCase(nomeLivro)) {
                livro.PrintBiblioteca();
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }
    public Livro BuscarLivro(String NomeDoLivro){
        for (Livro livro : vetBiblioteca) {
            if (livro.getNomeDoLivro().equalsIgnoreCase(NomeDoLivro)) {
                return livro;
            }
        }
        return null;
    }   
    public void RemoveLivro(Scanner sc, EmprestimoC emprestimos){
        if(SemLivro()){
            return;
        }
        String Nomelivro = ValidadorM.lerTextoObrigatorio(sc, "Digite o nome do livro que deseja remover: ");
        Livro livro = BuscarPorNome(Nomelivro);

        if(livro == null){
            System.out.println("Livro nao encontrado");
            return;
        }

        if(emprestimos.possuiEmprestimoAtivoLivro(livro)){
            System.out.println("Livro possui emprestimo em andamento e nao pode ser removido");
            return;
        }

        vetBiblioteca.remove(livro);
        System.out.println("Livro removido com sucesso");
        
    }
    public void AtualizarDadosLivro(Scanner sc){
        if(SemLivro()){
            return;
        }
        int opcao = 0;
        while(opcao != 5){
            System.out.println("1 - Alterar nome de um livro");
            System.out.println("2 - Alterar autor de um livro");
            System.out.println("3 - Alterar genero de um livro");
            System.out.println("4 - Alterar tudo");
            System.out.println("5 - Voltar");
            opcao = ValidadorM.lerInt(sc);

            switch(opcao){
                case 1 -> AlterarNome(sc);
                case 2 -> AlterarAutor(sc);
                case 3 -> AlterarGenero(sc);
                case 4 -> AlterarTudo(sc);
                case 5 -> System.out.println("Voltar");
                default -> System.out.println("Opcao invalida");
            }
        }
    }
    public void AlterarEstoque(Scanner sc){
        if(SemLivro()){
            return;
        }
        System.out.println("Qual o nome do livro que precisa de alteração de estoque: ");
        String nomeLivro = sc.nextLine();
        for (Livro livro : vetBiblioteca){
            if (livro.getNomeDoLivro().equalsIgnoreCase(nomeLivro)){
                int opcao = 0;
                while (opcao != 3){
                    System.out.println("Qual ação foi executada: ");
                    System.out.println("1-Aumentar numero de Livro no estoque");
                    System.out.println("2-Diminuir numero de Livro no estoque");
                    System.out.println("3-Voltar");
                    
                    opcao = ValidadorM.lerInt(sc);

                    switch(opcao){
                        case 1 -> {System.out.println("Quanto Livro deseja adicionar? ");
                            int quantidadeAdicional = ValidadorM.lerInt(sc);
                            livro.setQuantidade(livro.getQuantidade() + quantidadeAdicional);
                        }
                        case 2 -> {System.out.println("Quantos Livro deseja retirar? ");
                            int quantidadeRetirada = ValidadorM.lerInt(sc) ;
                            if (quantidadeRetirada > livro.getQuantidade()){
                                System.out.println("Estoque insuficiente. Só há " + livro.getQuantidade() + " unidade no estoque.");
                            }
                            else{
                                livro.setQuantidade(livro.getQuantidade() - quantidadeRetirada);
                            }
                        }
                        case 3 -> System.out.println("Voltar");
                        default -> System.out.println("Opcao invalida");
                    }
                }
            }
        }
    }
    public void ListarLivroPorGenero(Scanner sc){
        if(SemLivro()){
            return;
        }
        System.out.println("Generos disponiveis:");
        for(Livro livro : vetBiblioteca){
            System.out.println("- " + livro.getGenero());
        }
        System.out.println("Digite o gênero que deseja listar os Livro: ");
        String genero = sc.nextLine();
        int encontrado = 0;
        for (Livro livro : vetBiblioteca){
            if(livro.getGenero().equalsIgnoreCase(genero)){
                livro.PrintBiblioteca();
                encontrado++;
            }
        }
        if(encontrado==0){
            System.out.println("Nao existe livro cadastrado com esse genero");
        }
    }
    public void ListarLivroPorAutor(Scanner sc){
        if(SemLivro()){
            return;
        }
        System.out.println("Autores disponiveis: ");
        for(Livro livro : vetBiblioteca){
            System.out.println("- " + livro.getAutor());
        }
        System.out.println("Digite o autor que deseja listar os Livro: ");
        String autor = sc.nextLine();
        int encontrado = 0;
        for (Livro livro : vetBiblioteca){
            if(livro.getAutor().equalsIgnoreCase(autor)){
                livro.PrintBiblioteca();
                encontrado++;
            }
        }
        if(encontrado==0){
            System.out.println("Nao existe livro cadastrado com esse genero");
        }
    }
    public void ListarLivroPor(Scanner sc){
        if(SemLivro()){
            return;
        }
        int opcao = 0;
        while(opcao!=3){
            System.out.println("1-Listar Livro por genero");
            System.out.println("2-Listar Livro por autor");
            System.out.println("3-Voltar");
            opcao = ValidadorM.lerInt(sc);
            switch(opcao){
                case 1 -> ListarLivroPorGenero(sc);
                case 2 -> ListarLivroPorAutor(sc);
                case 3 -> System.out.println("Voltando ao menu principal");
                default -> System.out.println("Opcao invalida");
            }
        }
    }
    private boolean SemLivro(){
        if (vetBiblioteca.isEmpty()){
            System.out.println("Sem Livro no estoque");
            return true;
        }
        return false;
    }
    public void AlterarNome(Scanner sc){
        String nomelivro = ValidadorM.lerTextoObrigatorio(sc, "Digite o nome do livro que deseja alterar");
        Livro livro = BuscarPorNome(nomelivro);
        if (livro == null){
            System.out.println("Livro nao cadastrado no estoque");
            return;
        }
        String novonome = ValidadorM.lerTextoObrigatorio(sc, "Digite o novo nome do livro: ");
        Livro existente = BuscarPorNome(novonome);
        if (existente != null && existente != livro){
            System.out.println("Ja existe outro livro com esse nome");
            return;
        }
        livro.SetNomeDoLivro(novonome);
        System.out.println("Alteracao feita com sucesso");
    }
    public void AlterarAutor(Scanner sc){
        String nomelivro = ValidadorM.lerTextoObrigatorio(sc, "Digite o nome do livro que deseja alterar");
        for (Livro livro : vetBiblioteca){
            if (livro.getNomeDoLivro().equalsIgnoreCase(nomelivro)){
                String novoautor = ValidadorM.lerTextoObrigatorio(sc, "Digite o novo nome do autor");
                livro.SetAutor(novoautor);
                System.out.println("Alteracao feita com sucesso");
                return;
            }
        }
        System.out.println("Livro nao cadastrado no estoque");
    }
    public void AlterarGenero(Scanner sc){
        String nomelivro = ValidadorM.lerTextoObrigatorio(sc, "Digite o nome do livro que deseja alterar");
        for (Livro livro : vetBiblioteca){
            if (livro.getNomeDoLivro().equalsIgnoreCase(nomelivro)){
                String novogenero = ValidadorM.lerTextoObrigatorio(sc, "Digite o novo nome do genero");
                livro.SetGenero(novogenero);
                System.out.println("Alteracao feita com sucesso");
                return;
            }
        }
        System.out.println("Livro nao cadastrado no estoque");
    }
    public void AlterarTudo(Scanner sc){
        String nomelivro = ValidadorM.lerTextoObrigatorio(sc, "Digite o nome do livro que deseja alterar");
        Livro livro = BuscarPorNome(nomelivro);
        if (livro == null){
            System.out.println("Livro nao cadastrado no estoque");
            return;
        }
        String novonome = ValidadorM.lerTextoObrigatorio(sc, "Digite o novo nome do livro: ");
        Livro existente = BuscarPorNome(novonome);
        if (existente != null && existente != livro){
        System.out.println("Ja existe outro livro com esse nome");
        return;
        }
        String novoautor = ValidadorM.lerTextoObrigatorio(sc, "Digite o novo nome do autor");
        livro.SetAutor(novoautor);
        String novogenero = ValidadorM.lerTextoObrigatorio(sc, "Digite o novo nome do genero");
        livro.SetGenero(novogenero);
        System.out.println("Digite a nova quantidade do livro no estoque");
        int novaquantidade = ValidadorM.lerInt(sc);
        livro.setQuantidade(novaquantidade);
        System.out.println("Alteracao feita com sucesso");
        } 
    public Livro BuscarPorNome(String nome){
        for (Livro livro : vetBiblioteca){
        if (livro.getNomeDoLivro().equalsIgnoreCase(nome)){
                return livro;
            }
        }
        return null;

    }
}


