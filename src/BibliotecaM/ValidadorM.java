package BibliotecaM;

public final class ValidadorM {

    private ValidadorM() {}

    public static String formatarCPF(String cpf) {
        String digitos = cpf.replaceAll("[^\\d]", "");
        if (digitos.length() != 11) {
            throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos.");
        }
        return digitos.substring(0, 3) + "." + digitos.substring(3, 6) + "." + digitos.substring(6, 9) + "-" + digitos.substring(9, 11);
    }

    public static String formatarData(String data){
        String digitos = data.replaceAll("[^\\d]","");
        if(digitos.length()!=8){
            throw new IllegalArgumentException("Data inválida. Deve conter 8 digitos.");
        }
        return digitos.substring(0, 2) + "/" + digitos.substring(2, 4) + "/" + digitos.substring(4, 8);

    }

    public static String formatarTelefone(String telefone){
        String telefoneFormatado = telefone.replaceAll("[^\\d]", "");
        if(telefoneFormatado.length() != 11){
            throw new IllegalArgumentException("Telefone inválido. Deve conter 11 dígitos.");
        }
        return "(" + telefoneFormatado.substring(0,2) + ")" + telefoneFormatado.substring(2,7) + "-" + telefoneFormatado.substring(7,11);
    }
}