package Telas;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import A3.BancoController;
import A3.Movimentacao;

public class TelaExtrato extends JPanel {

    private JTextPane areaExtrato;
    private StyledDocument doc;

    public TelaExtrato(BancoController controller, MainApp app) {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(230, 242, 242)); // Verde claro padronizado

        // 🔹 JTextPane para permitir cores e formatação
        areaExtrato = new JTextPane();
        areaExtrato.setEditable(false);
        areaExtrato.setBackground(Color.WHITE);
        areaExtrato.setFont(new Font("SansSerif", Font.PLAIN, 14));

        doc = areaExtrato.getStyledDocument();

        JScrollPane scroll = new JScrollPane(areaExtrato);
        scroll.setBorder(BorderFactory.createTitledBorder("Extrato da Conta"));

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> app.mudarTela("menu"));

        add(scroll, BorderLayout.CENTER);
        add(btnVoltar, BorderLayout.SOUTH);
    }

    // 🔹 Estilos para cada tipo de texto
    private void appendStyled(String texto, Color cor) {
        Style style = areaExtrato.addStyle("estilo", null);
        StyleConstants.setForeground(style, cor);
        StyleConstants.setBold(style, true);

        try {
            doc.insertString(doc.getLength(), texto, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void appendNormal(String texto, Color cor) {
        Style style = areaExtrato.addStyle("normal", null);
        StyleConstants.setForeground(style, cor);

        try {
            doc.insertString(doc.getLength(), texto, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    // 🔹 Chamada antes de mostrar o extrato
    public void atualizarExtrato(BancoController controller) {

        doc = areaExtrato.getStyledDocument();
        areaExtrato.setText(""); // Limpa

        var lista = controller
                .getClienteLogado()
                .getConta()
                .getExtrato();

        if (lista.isEmpty()) {
            appendStyled("Nenhuma movimentação registrada.\n", Color.GRAY);
            return;
        }

        for (Movimentacao mov : lista) {

            // Cor da operação
            Color cor;
            if (mov.formatar().contains("Depósito")) {
                cor = new Color(0, 128, 0); // verde
            } else {
                cor = new Color(200, 0, 0); // vermelho
            }

            // 🔸 Data em cinza
            String data = mov.formatar().split("]")[0] + "] ";
            appendNormal(data, new Color(100, 100, 100));

            // 🔸 Tipo + valor com cor
            String info = mov.formatar().substring(data.length());
            appendStyled(info, cor);

            // Quebra de linha
            appendNormal("\n", Color.BLACK);
        }

        areaExtrato.setCaretPosition(0);
    }
}