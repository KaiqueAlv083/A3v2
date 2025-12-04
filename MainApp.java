package Telas;

import javax.swing.*;
import java.awt.*;
import A3.BancoController;

public class MainApp extends JFrame {

    private CardLayout layout;
    private JPanel container;

    private BancoController controller;

    // Referências das telas que precisam atualizar conteúdo
    private TelaMenu telaMenu;
    private TelaExtrato telaExtrato;

    public MainApp() {

        // 🎨 Look and Feel Nimbus
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao aplicar Look and Feel: " + e.getMessage());
        }

        // 🎨 Estilos Globais do Tema Verde Banco
        UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 14));
        UIManager.put("Label.font", new Font("SansSerif", Font.PLAIN, 14));
        UIManager.put("TextField.font", new Font("SansSerif", Font.PLAIN, 14));
        UIManager.put("Panel.background", new Color(230, 242, 242)); // Verde claro
        UIManager.put("Button.background", new Color(27, 142, 142)); // Verde médio
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.focus", new Color(10, 61, 61));        // Verde escuro

        // 🔧 Inicializa o controller (regra de negócio)
        controller = new BancoController();

        setTitle("A3 - Sistema Bancário");
        setSize(500, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        layout = new CardLayout();
        container = new JPanel(layout);

        // ======================
        // CRIAÇÃO DAS TELAS
        // ======================

        TelaLogin telaLogin = new TelaLogin(controller, this);
        TelaCadastro telaCadastro = new TelaCadastro(controller, this);

        telaMenu = new TelaMenu(controller, this);
        TelaDeposito telaDeposito = new TelaDeposito(controller, this);
        TelaSaque telaSaque = new TelaSaque(controller, this);

        telaExtrato = new TelaExtrato(controller, this);

        // ======================
        // REGISTRO NO LAYOUT
        // ======================

        container.add(telaLogin, "login");
        container.add(telaCadastro, "cadastro");
        container.add(telaMenu, "menu");
        container.add(telaDeposito, "deposito");
        container.add(telaSaque, "saque");
        container.add(telaExtrato, "extrato");

        add(container);

        layout.show(container, "login");
    }

    // ======================
    // TROCA DE TELAS
    // ======================
    public void mudarTela(String nome) {

        if (nome.equals("menu")) {
            telaMenu.atualizarSaldo(controller);
        }

        if (nome.equals("extrato")) {
            telaExtrato.atualizarExtrato(controller);
        }

        layout.show(container, nome);
    }

    // ======================
    // MAIN
    // ======================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp().setVisible(true));
    }
}