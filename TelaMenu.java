package Telas;

import javax.swing.*;
import java.awt.*;
import A3.BancoController;

public class TelaMenu extends JPanel {

    private JLabel lblSaldo;

    public TelaMenu(BancoController controller, MainApp app) {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Label do Saldo
        lblSaldo = new JLabel("Saldo atual: R$ 0.00", SwingConstants.CENTER);
        lblSaldo.setFont(new Font("SansSerif", Font.BOLD, 16));

        // Painel com os botões
        JPanel botoes = new JPanel(new GridLayout(5, 1, 10, 10));

        JButton btnDeposito = new JButton("Depositar");
        JButton btnSaque = new JButton("Sacar");
        JButton btnExtrato = new JButton("Extrato");
        JButton btnLogout = new JButton("Sair");

        // EVENTOS ------------------------------------------------------

        btnDeposito.addActionListener(e ->
                app.mudarTela("deposito"));

        btnSaque.addActionListener(e ->
                app.mudarTela("saque"));

        btnExtrato.addActionListener(e ->
                app.mudarTela("extrato"));

        btnLogout.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Sessão encerrada.");
            app.mudarTela("login");
        });

        // Adiciona botões
        botoes.add(btnDeposito);
        botoes.add(btnSaque);
        botoes.add(btnExtrato);
        botoes.add(btnLogout);

        // Montagem da tela
        add(lblSaldo, BorderLayout.NORTH);
        add(botoes, BorderLayout.CENTER);
    }

    // Chamado pelo MainApp TODA VEZ que o usuário entra no menu
    public void atualizarSaldo(BancoController controller) {

        double saldo = controller.getClienteLogado()
                                 .getConta()
                                 .getSaldo();

        lblSaldo.setText(String.format("Saldo atual: R$ %.2f", saldo));
    }
}