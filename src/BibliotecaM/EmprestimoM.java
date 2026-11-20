package BibliotecaM;
import java.time.Duration;
import java.time.LocalDateTime;

public class EmprestimoM{
    private ClienteM cliente;
    private EmpregadoM empregado;
    private Livro livro;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime prazoDevolucao;
    private LocalDateTime dataDevolucao;

    public EmprestimoM(ClienteM cliente, EmpregadoM empregado, Livro livro, LocalDateTime dataEmprestimo, LocalDateTime prazoDevolucao, LocalDateTime dataDevolucao){
        this.cliente = cliente;
        this.empregado = empregado;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.prazoDevolucao = prazoDevolucao;
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

    public LocalDateTime getprazoDevolucao(){
        return prazoDevolucao;
    }

    public LocalDateTime getdataDevolucao(){
        return dataDevolucao;
    }
    @Override
    public String toString() {
        String situacao =isAtivo() ? "Ainda nao devolvido" : "Devolvido em: " + dataDevolucao.format(
                    java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        return "Cliente: " + cliente.getNome() +
                "\nLivro: " + livro.getNomeDoLivro() +
                "\nEmpregado: " + empregado.getNome() +
                "\nData do empréstimo: " + dataEmprestimo.format(
                    java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +
                "\nPrazo de devolução: " + prazoDevolucao.format(
                    java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + 
                "\nSituacao: " + situacao;
    }

    public boolean isAtivo(){
        return dataDevolucao == null;
    }

    public void devolver(){
        if(!isAtivo()){
        throw new IllegalStateException("Este emprestimo ja foi devolvido.");
        }
        dataDevolucao = LocalDateTime.now();
    }
    private LocalDateTime referencia(){
    return isAtivo() ? LocalDateTime.now() : dataDevolucao;
    }

    public boolean estaAtrasado(){
        return referencia().isAfter(prazoDevolucao);
    }

    public long diasDeAtraso(){
        if(!estaAtrasado()){
            return 0;
        }
        return Duration.between(prazoDevolucao, referencia()).toDays();
    }
}