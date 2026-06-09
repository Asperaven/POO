package TFinalGUI;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private MainFrame mainFrame;
    private JTextField campoNome;
    private JPasswordField campoSenha;
    private boolean modoCadastro = false;

    // Campos extras para cadastro
    private JTextField campoIdade;
    private JTextField campoPlano;
    private JComboBox<String> comboTipo;
    private JTextField campoValor;
    private JPanel painelCadastroExtra;
    private JLabel labelTitulo;
    private JButton botaoEntrar;
    private JButton botaoAlternar;

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setBackground(MainFrame.COR_FUNDO);
        setLayout(new GridBagLayout());
        criarInterface();
    }

    private void criarInterface() {
        JPanel cardLogin = MainFrame.criarPainelCard();
        cardLogin.setLayout(new BoxLayout(cardLogin, BoxLayout.Y_AXIS));
        cardLogin.setPreferredSize(new Dimension(420, 520));

        // Ícone / Título
        JLabel icone = new JLabel("🏥", SwingConstants.CENTER);
        icone.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        icone.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardLogin.add(icone);
        cardLogin.add(Box.createVerticalStrut(8));

        labelTitulo = MainFrame.criarLabel("Clínica Médica", 24, true);
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardLogin.add(labelTitulo);

        JLabel subtitulo = MainFrame.criarLabel("Sistema de Gestão", 14, false);
        subtitulo.setForeground(MainFrame.COR_TEXTO_SECUNDARIO);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardLogin.add(subtitulo);
        cardLogin.add(Box.createVerticalStrut(24));

        // Campos de login
        campoNome = MainFrame.criarCampoTexto("Nome de usuário");
        campoNome.setMaximumSize(new Dimension(360, 42));
        campoNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardLogin.add(campoNome);
        cardLogin.add(Box.createVerticalStrut(12));

        campoSenha = MainFrame.criarCampoSenha("Senha");
        campoSenha.setMaximumSize(new Dimension(360, 42));
        campoSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardLogin.add(campoSenha);
        cardLogin.add(Box.createVerticalStrut(12));

        // Painel de cadastro extra (inicialmente oculto)
        painelCadastroExtra = new JPanel();
        painelCadastroExtra.setLayout(new BoxLayout(painelCadastroExtra, BoxLayout.Y_AXIS));
        painelCadastroExtra.setBackground(MainFrame.COR_PAINEL);
        painelCadastroExtra.setVisible(false);
        painelCadastroExtra.setMaximumSize(new Dimension(360, 200));

        comboTipo = new JComboBox<>(new String[] { "Paciente", "Cardiologista", "Pediatra", "Dermatologista" });
        comboTipo.setBackground(MainFrame.COR_CARD);
        comboTipo.setForeground(MainFrame.COR_TEXTO);
        comboTipo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboTipo.setMaximumSize(new Dimension(360, 42));
        comboTipo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCadastroExtra.add(comboTipo);
        painelCadastroExtra.add(Box.createVerticalStrut(8));

        campoIdade = MainFrame.criarCampoTexto("Idade");
        campoIdade.setMaximumSize(new Dimension(360, 42));
        campoIdade.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCadastroExtra.add(campoIdade);
        painelCadastroExtra.add(Box.createVerticalStrut(8));

        campoPlano = MainFrame.criarCampoTexto("Plano de Saúde (ou vazio)");
        campoPlano.setMaximumSize(new Dimension(360, 42));
        campoPlano.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCadastroExtra.add(campoPlano);
        painelCadastroExtra.add(Box.createVerticalStrut(8));

        campoValor = MainFrame.criarCampoTexto("Valor da consulta");
        campoValor.setMaximumSize(new Dimension(360, 42));
        campoValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoValor.setVisible(false);
        painelCadastroExtra.add(campoValor);

        comboTipo.addActionListener(e -> {
            boolean isMedico = !comboTipo.getSelectedItem().equals("Paciente");
            campoValor.setVisible(isMedico);
            campoIdade.setVisible(!isMedico);
            campoPlano.setVisible(!isMedico);
            painelCadastroExtra.revalidate();
        });

        cardLogin.add(painelCadastroExtra);
        cardLogin.add(Box.createVerticalStrut(16));

        // Botão Entrar/Cadastrar
        botaoEntrar = MainFrame.criarBotao("Entrar", MainFrame.COR_PRIMARIA);
        botaoEntrar.setMaximumSize(new Dimension(360, 44));
        botaoEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoEntrar.addActionListener(e -> {
            if (modoCadastro) {
                realizarCadastro();
            } else {
                realizarLogin();
            }
        });
        cardLogin.add(botaoEntrar);
        cardLogin.add(Box.createVerticalStrut(12));

        // Link para alternar entre login/cadastro
        botaoAlternar = new JButton("Não tem conta? Cadastre-se");
        botaoAlternar.setBackground(MainFrame.COR_PAINEL);
        botaoAlternar.setForeground(MainFrame.COR_PRIMARIA);
        botaoAlternar.setBorderPainted(false);
        botaoAlternar.setFocusPainted(false);
        botaoAlternar.setContentAreaFilled(false);
        botaoAlternar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoAlternar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        botaoAlternar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoAlternar.addActionListener(e -> alternarModo());
        cardLogin.add(botaoAlternar);

        add(cardLogin);
    }

    private void alternarModo() {
        modoCadastro = !modoCadastro;
        painelCadastroExtra.setVisible(modoCadastro);
        labelTitulo.setText(modoCadastro ? "Criar Conta" : "Clínica Médica");
        botaoEntrar.setText(modoCadastro ? "Cadastrar" : "Entrar");
        botaoAlternar.setText(modoCadastro ? "Já tem conta? Faça login" : "Não tem conta? Cadastre-se");
        revalidate();
        repaint();
    }

    private void realizarLogin() {
        String nome = MainFrame.getTextoOuVazio(campoNome, "Nome de usuário");
        String senha = String.valueOf(campoSenha.getPassword());
        if (senha.equals("Senha"))
            senha = "";

        if (nome.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha todos os campos.",
                    "Campos obrigatórios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object usuario = mainFrame.getSistema().login(nome, senha);
        if (usuario != null) {
            mainFrame.setUsuarioLogado(usuario);
            mainFrame.mostrarTela(MainFrame.DASHBOARD);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Nome ou senha incorretos!",
                    "Erro de Login", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void realizarCadastro() {
        String nome = MainFrame.getTextoOuVazio(campoNome, "Nome de usuário");
        String senha = String.valueOf(campoSenha.getPassword());
        if (senha.equals("Senha"))
            senha = "";
        String tipo = (String) comboTipo.getSelectedItem();

        if (nome.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha nome e senha.",
                    "Campos obrigatórios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (tipo.equals("Paciente")) {
            String idadeStr = MainFrame.getTextoOuVazio(campoIdade, "Idade");
            String plano = MainFrame.getTextoOuVazio(campoPlano, "Plano de Saúde (ou vazio)");
            int idade;
            try {
                idade = Integer.parseInt(idadeStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Idade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            mainFrame.getSistema().cadastrarPaciente(nome, idade, plano, senha);
        } else {
            String valorStr = MainFrame.getTextoOuVazio(campoValor, "Valor da consulta");
            double valor;
            try {
                valor = Double.parseDouble(valorStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            mainFrame.getSistema().cadastrarMedico(nome, valor, senha, tipo);
        }

        JOptionPane.showMessageDialog(this,
                "Cadastro realizado com sucesso! Faça login.",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        alternarModo();
    }
}
