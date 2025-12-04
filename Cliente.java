package A3;

public class Cliente {

    private String usuario;
    private String senha;
    private ContaBancaria conta;

    public Cliente(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
        this.conta = new ContaBancaria();
    }

    public String getUsuario() {
        return usuario;
    }

    public boolean validarSenha(String senhaDigitada) {
        return senha.equals(senhaDigitada);
    }

    public ContaBancaria getConta() {
        return conta;
    }
}