package Telas;

import javax.swing.*;
import java.awt.*;
import A3.BancoController;

public class TelaLogin extends JPanel {

    public TelaLogin(BancoController controller, MainApp app) {

        setLayout(new GridLayout(4, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblUser = new JLabel("Usuário:");
        JTextField txtUser = new JTextField();

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField();

        JButton btnLogin = new JButton("Entrar");
        JButton btnCadastro = new JButton("Criar Conta");

        // Evento: Login
        btnLogin.addActionListener(e -> {

            String usuario = txtUser.getText();
            String senha = new String(txtSenha.getPassword());

            if (controller.login(usuario, senha)) {

                // Login OK → vai para o menu
                app.mudarTela("menu");

            } else {
                JOptionPane.showMessageDialog(this,
                        "Usuário ou senha incorretos!",
                        "Falha no Login",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Evento: Ir para tela de cadastro
        btnCadastro.addActionListener(e -> {
            app.mudarTela("cadastro");
        });

        add(lblUser);
        add(txtUser);
        add(lblSenha);
        add(txtSenha);
        add(btnCadastro);
        add(btnLogin);
    }
}