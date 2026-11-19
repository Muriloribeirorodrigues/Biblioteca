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
}
