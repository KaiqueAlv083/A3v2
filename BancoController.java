package A3;

public class BancoController {

    private Cliente clienteLogado;

    public void cadastrar(String usuario, String senha) {
        clienteLogado = new Cliente(usuario, senha);
    }

    public boolean login(String usuario, String senha) {
        if (clienteLogado == null)
            return false;

        if (!clienteLogado.getUsuario().equals(usuario))
            return false;

        return clienteLogado.validarSenha(senha);
    }

    public Cliente getClienteLogado() {
        return clienteLogado;
    }
}