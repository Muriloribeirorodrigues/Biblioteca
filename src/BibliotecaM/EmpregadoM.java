package BibliotecaM;

public class EmpregadoM {
    private String CPF;
    private String Cargo;
    private String Nascimento;
    private String Nome;
    private char Sexo;
    private String Telefone;

    public EmpregadoM(String nome, String telefone, String cpf, String nascimento, String cargo, char sexo){
        this.CPF = cpf;
        this.Cargo = cargo;
        this.Nascimento = nascimento;
        this.Nome = nome;
        this.Sexo = sexo;
        this.Telefone = telefone;
    }
    public String getCPF(){
        return CPF;
    }

    public void setCPF(String cpf){
        this.CPF = cpf;
    }

    public String getCargo(){
        return Cargo;
    }

    public void setCargo(String cargo){
        this.Cargo = cargo;
    }

    public String getNascimento(){
        return Nascimento;
    }

    public void setNascimento(String nascimento){
        this.Nascimento = nascimento;
    }

    public String getNome(){
        return Nome;
    }

    public void setNome(String nome){
        this.Nome = nome;
    }

    public char getSexo(){
        return Sexo;
    }

    public void setSexo(char sexo){
        this.Sexo = sexo;
    }

    public String getTelefone(){
        return Telefone;
    }

    public void setTelefone(String telefone){
        this.Telefone = telefone;
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
