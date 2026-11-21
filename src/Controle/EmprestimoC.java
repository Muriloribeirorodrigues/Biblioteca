package Controle;
import BibliotecaM.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EmprestimoC {
    private ArrayList<EmprestimoM> vetEmprestimo;

    public ArrayList<EmprestimoM> getVetEmprestimo(){
        return vetEmprestimo;
    }

    public EmprestimoC() {
        vetEmprestimo = new ArrayList<EmprestimoM>();
    }

    public boolean possuiEmprestimosAtivos(String cpf){
        for(EmprestimoM emprestimo : vetEmprestimo){
            if(emprestimo.isAtivo() && emprestimo.getCliente().getCPF().equals(cpf)){
                return true;
            }
        }
        return false;
    }
}
