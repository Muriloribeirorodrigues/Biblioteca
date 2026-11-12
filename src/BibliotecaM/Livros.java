package BibliotecaM;

public class Livros {
     private String NomeDoLivro;
     private String Autor;
     private String Genero;
     private int Quantidade;

     public Livros(String nomedolivro, String autor, String genero, int quantidade){
          this.NomeDoLivro = nomedolivro;
          this.Autor = autor;
          this.Genero = genero;
          this.Quantidade = quantidade;
     }

     public String getNomeDoLivro(){
          return NomeDoLivro;
     }

     public void SetNomeDoLivro (String nomedolivro){
          NomeDoLivro = nomedolivro;
     }

     public String getAutor(){
          return Autor;
     }

     public void SetAutor (String autor){
          Autor = autor;
     }

     public String getGenero(){
          return Genero;
     }

     public void SetGenero (String genero){
          Genero = genero;
     }

     public int getQuantidade(){
          return Quantidade;
     }

     public void setQuantidade(int quantidade){
          Quantidade = quantidade;
     }

     public void PrintBiblioteca(){
          System.out.println("NomeDoLivro: "+this.getNomeDoLivro());
          System.out.println("Autor: "+this.getAutor());
          System.out.println("Genero: "+this.getGenero());
          System.out.println("Quantidade: "+this.getQuantidade());
     }
}
