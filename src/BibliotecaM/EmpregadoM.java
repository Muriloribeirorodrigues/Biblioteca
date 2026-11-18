package BibliotecaM;

public class EmpregadoM extends Pessoa{
    
    private String Cargo;

    public EmpregadoM(String nome, String telefone, String cpf, String nascimento, String cargo, char sexo){
        super(nome, telefone, cpf, nascimento, sexo);
        this.Cargo = cargo;
    }

    public String getCargo(){
        return Cargo;
    }

    public void setCargo(String cargo){
        this.Cargo = cargo;
    }


    public void PrintEmpregado(){
        System.out.println("Nome: "+this.getNome());
        System.out.println("Telefone: "+this.getTelefone());
        System.out.println("CPF: "+this.getCPF());
        System.out.println("Nascimento: "+this.getNascimento());
        System.out.println("Cargo: "+this.getCargo());
        System.out.println("Sexo: "+this.getSexo());
    }
}
