package Controle;
import BibliotecaM.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaC {
    public ArrayList<Livros> vetBiblioteca;

    public ArrayList<Livros> getVetBiblioteca() {
        return vetBiblioteca;
    }

    public BibliotecaC(){
        vetBiblioteca = new ArrayList<Livros>();
    }

    public void CadastrarLivro(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Nome do Livro: ");
        String NomeDoLivro = sc.nextLine();

        System.out.println("Autor: ");
        String Autor = sc.nextLine();

        System.out.println("Genero: ");
        String Genero = sc.nextLine();

        System.out.println("Quantidade: ");
        int Quantidade = sc.nextInt();

        Livros novolivro = new Livros(NomeDoLivro, Autor, Genero, Quantidade);

        vetBiblioteca.add(novolivro);

        System.out.println("Livro cadastrado");
    }
    public void ListarLivros(){
        if(SemLivros()){
            return;
        }
        for (Livros biblioteca : vetBiblioteca){
            biblioteca.PrintBiblioteca();
        }
    }
    public void BuscarLivro(){
        if(SemLivros()){
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do livro que deseja buscar: ");
        String nomeLivro = sc.nextLine();
        for (Livros livro : vetBiblioteca) {
            if (livro.getNomeDoLivro().equalsIgnoreCase(nomeLivro)) {
                livro.PrintBiblioteca();
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }   
    public void RemoveLivro(){
        if(SemLivros()){
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do livro que deseja remover: ");
        String nomeLivro = sc.nextLine();
        for (int i = 0; i < vetBiblioteca.size(); i++) {
            if (vetBiblioteca.get(i).getNomeDoLivro().equalsIgnoreCase(nomeLivro)) {
                vetBiblioteca.remove(i);
                System.out.println("Livro removido com sucesso.");
                return;
            }
        }
    }
    public void AtualizarDadosLivros(){
        if(SemLivros()){
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do livro que deseja atualizar: ");
        String nomeLivro = sc.nextLine();
        for (Livros livro : vetBiblioteca) {
            if (livro.getNomeDoLivro().equalsIgnoreCase(nomeLivro)) {
                System.out.println("Digite o novo nome do livro: ");
                String novoNome = sc.nextLine();
                livro.SetNomeDoLivro(novoNome);

                System.out.println("Digite o novo autor do livro: ");
                String novoAutor = sc.nextLine();
                livro.SetAutor(novoAutor);

                System.out.println("Digite o novo gênero do livro: ");
                String novoGenero = sc.nextLine();
                livro.SetGenero(novoGenero);

                System.out.println("Digite a nova quantidade do livro: ");
                int novaQuantidade = sc.nextInt();
                livro.setQuantidade(novaQuantidade);

                System.out.println("Dados do livro atualizados com sucesso.");
                return;
            }
        }
    }
    public void AlterarEstoque(){
        if(SemLivros()){
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual o nome do livro que precisa de alteração de estoque: ");
        String nomeLivro = sc.nextLine();
        for (Livros livro : vetBiblioteca){
            if (livro.getNomeDoLivro().equalsIgnoreCase(nomeLivro)){
                int opcao = 0;
                while (opcao != 3){
                    System.out.println("Qual ação foi executada: ");
                    System.out.println("1-Aumentar numero de livros no estoque");
                    System.out.println("2-Diminuir numero de livros no estoque");
                    System.out.println("3-Voltar");
                    
                    opcao = sc.nextInt();
                    sc.nextLine();

                    switch(opcao){
                        case 1 -> {System.out.println("Quanto livros deseja adicionar? ");
                            int quantidadeAdicional = sc.nextInt();
                            livro.setQuantidade(livro.getQuantidade() + quantidadeAdicional);
                        }
                        case 2 -> {System.out.println("Quantos livros deseja retirar? ");
                            int quantidadeAdicional = sc.nextInt();
                            livro.setQuantidade(livro.getQuantidade() - quantidadeAdicional);
                        }
                        case 3 -> System.out.println("Voltar");
                        default -> System.out.println("Opcao invalida");
                    }
                }
            }
        }
    }
    public void ListarLivrosPorGenero(){
        if(SemLivros()){
            return;
        }
        System.out.println("Generos disponiveis:");
        for(Livros livro : vetBiblioteca){
            System.out.println("- " + livro.getGenero());
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o gênero que deseja listar os livros: ");
        String genero = sc.nextLine();
        int encontrado = 0;
        for (Livros livro : vetBiblioteca){
            if(livro.getGenero().equalsIgnoreCase(genero)){
                livro.PrintBiblioteca();
                encontrado++;
            }
        }
        if(encontrado==0){
            System.out.println("Nao existe livro cadastrado com esse genero");
        }
    }
    public void ListarLivrosPorAutor(){
        if(SemLivros()){
            return;
        }
        System.out.println("Autores disponiveis: ");
        for(Livros livro : vetBiblioteca){
            System.out.println("- " + livro.getAutor());
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o autor que deseja listar os livros: ");
        String autor = sc.nextLine();
        int encontrado = 0;
        for (Livros livro : vetBiblioteca){
            if(livro.getAutor().equalsIgnoreCase(autor)){
                livro.PrintBiblioteca();
                encontrado++;
            }
        }
        if(encontrado==0){
            System.out.println("Nao existe livro cadastrado com esse genero");
        }
    }
    public void ListarLivrosPor(){
        if(SemLivros()){
            return;
        }
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        while(opcao!=3){
            System.out.println("1-Listar livros por genero");
            System.out.println("2-Listar livros por autor");
            System.out.println("3-Voltar");
            opcao = sc.nextInt();
            sc.nextLine();
            switch(opcao){
                case 1 -> ListarLivrosPorGenero();
                case 2 -> ListarLivrosPorAutor();
                case 3 -> System.out.println("Voltando ao menu principal");
                default -> System.out.println("Opcao invalida");
            }
        }
    }
    private boolean SemLivros(){
        if (vetBiblioteca.isEmpty()){
            System.out.println("Sem livros no estoque");
            return true;
        }
        return false;
    }
}


