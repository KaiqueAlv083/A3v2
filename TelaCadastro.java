package Telas;

import javax.swing.*;
import java.awt.*;
import A3.BancoController;

public class TelaCadastro extends JPanel {

    public TelaCadastro(BancoController controller, MainApp app) {

        setLayout(new GridLayout(4, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblUsuario = new JLabel("Novo usuário:");
        JTextField txtUsuario = new JTextField();

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField();

        JLabel lblSenha2 = new JLabel("Confirmar senha:");
        JPasswordField txtSenha2 = new JPasswordField();

        JButton btnVoltar = new JButton("Voltar");
        JButton btnRegistrar = new JButton("Registrar");

        // Evento: Registrar usuário
        btnRegistrar.addActionListener(e -> {

            String usuario = txtUsuario.getText();
            String senha1 = new String(txtSenha.getPassword());
            String senha2 = new String(txtSenha2.getPassword());

            // Validações simples
            if (usuario.isEmpty() || senha1.isEmpty() || senha2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }

            if (!senha1.equals(senha2)) {
                JOptionPane.showMessageDialog(this, "As senhas não coincidem!");
                return;
            }

            // Chama o controller para registrar o cliente
            controller.cadastrar(usuario, senha1);

            JOptionPane.showMessageDialog(this,
                    "Conta criada com sucesso!",
                    "Cadastro",
                    JOptionPane.INFORMATION_MESSAGE);

            // Volta para tela de login
            app.mudarTela("login");
        });

        // Evento: voltar ao login
        btnVoltar.addActionListener(e -> app.mudarTela("login"));

        add(lblUsuario);
        add(txtUsuario);
        add(lblSenha);
        add(txtSenha);
        add(lblSenha2);
        add(txtSenha2);
        add(btnVoltar);
        add(btnRegistrar);
    }
}