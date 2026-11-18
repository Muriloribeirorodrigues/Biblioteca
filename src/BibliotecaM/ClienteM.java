package BibliotecaM;

public class ClienteM extends Pessoa {

    public ClienteM(String nome, String telefone, String cpf, String nascimento, char sexo){
        super(nome, telefone, cpf, nascimento, sexo);
    }

    public void PrintCliente(){
        System.out.println("Nome: "+this.getNome());
        System.out.println("Telefone: "+this.getTelefone());
        System.out.println("CPF: "+this.getCPF());
        System.out.println("Nascimento: "+this.getNascimento());
        System.out.println("Sexo: "+this.getSexo());
    }
}
