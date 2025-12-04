package Telas;

import javax.swing.*;
import java.awt.*;
import A3.BancoController;

public class TelaSaque extends JPanel {

    public TelaSaque(BancoController controller, MainApp app) {

        setLayout(new GridLayout(3, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblValor = new JLabel("Valor do saque:");
        JTextField txtValor = new JTextField();

        JButton btnVoltar = new JButton("Voltar");
        JButton btnConfirmar = new JButton("Sacar");

        // Evento: Sacar
        btnConfirmar.addActionListener(e -> {

            try {
                double valor = Double.parseDouble(txtValor.getText());

                if (valor <= 0) {
                    JOptionPane.showMessageDialog(this, "Digite um valor maior que zero.");
                    return;
                }

                // Tentativa de saque REAL via model
                controller.getClienteLogado().getConta().sacar(valor);

                JOptionPane.showMessageDialog(this,
                        String.format("Saque de R$ %.2f realizado com sucesso!", valor));

                txtValor.setText("");

                // Volta ao menu
                app.mudarTela("menu");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um valor válido (somente números).");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        // Evento: Voltar ao menu
        btnVoltar.addActionListener(e -> app.mudarTela("menu"));

        add(lblValor);
        add(txtValor);
        add(btnVoltar);
        add(btnConfirmar);
    }
}