package BibliotecaM;
import java.time.LocalDateTime;

public class EmprestimoM{
    private ClienteM cliente;
    private EmpregadoM empregado;
    private Livro livro;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;

    public EmprestimoM(ClienteM cliente, EmpregadoM empregado, Livro livro, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao){
        this.cliente = cliente;
        this.empregado = empregado;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public ClienteM getCliente(){
        return cliente;
    }

    public EmpregadoM getEmpregado(){
        return empregado;
    }

    public Livro getLivro(){
        return livro;
    }

    public LocalDateTime getDataEmprestimo(){
        return dataEmprestimo;
    }

    public LocalDateTime getDataDevolucao(){
        return dataDevolucao;
    }
    @Override
    public String toString() {
        return "Cliente: " + cliente.getNome() +
                "\nLivro: " + livro.getNomeDoLivro() +
                "\nEmpregado: " + empregado.getNome() +
                "\nData do empréstimo: " + dataEmprestimo.format(
                    java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +
                "\nData de devolução: " + dataDevolucao.format(
                    java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public boolean verificarAtraso(LocalDateTime dataDevolucaoReal){
        return dataDevolucaoReal.isAfter(dataDevolucao);
    }
    public long calcularDiasAtraso(LocalDateTime dataDevolucaoReal){
        if(verificarAtraso(dataDevolucaoReal)){
            return java.time.Duration.between(dataDevolucao, dataDevolucaoReal).toDays();
        }
        return 0;
    }
}