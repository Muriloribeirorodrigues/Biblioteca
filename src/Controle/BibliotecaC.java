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
        System.out.println("Nome do Livro: ");
        String NomeDoLivro = sc.nextLine();

        System.out.println("Autor: ");
        String Autor = sc.nextLine();

        System.out.println("Genero: ");
        String Genero = sc.nextLine();

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
    public void RemoveLivro(Scanner sc){
        if(SemLivro()){
            return;
        }
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
    public void AtualizarDadosLivro(Scanner sc){
        if(SemLivro()){
            return;
        }
        System.out.println("Digite o nome do livro que deseja atualizar: ");
        String nomeLivro = sc.nextLine();
        for (Livro livro : vetBiblioteca) {
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
                int novaQuantidade = ValidadorM.lerInt(sc);
                livro.setQuantidade(novaQuantidade);

                System.out.println("Dados do livro atualizados com sucesso.");
                return;
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
                                System.out.println("Estoque insuficiente. Só há" + livro.getQuantidade() + "unidade no estoque");
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
}


