package TFinalGUI;

import TFinalClasses.*;
import TFinalExcecoes.SaldoInsuficienteException;
import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ContaPanel extends JPanel {

    private MainFrame mainFrame;
    private JPasswordField campoSenhaAtual;
    private JPasswordField campoNovaSenha;
    private JComboBox<String> comboPlano;
    private JComboBox<String> comboEspecialidade;
    private JTextField campoCreditos;
    private JButton botaoVerificar;
    private JButton botaoAlterarSenha;
    private JButton botaoDeletarConta;
    private JButton botaoAlterarPlano;
    private JButton botaoAdicionarCreditos;
    private JButton botaoAlterarEspecialidade;
    private boolean senhaVerificada = false;

    private JPanel cardPaciente;
    private JPanel cardMedico;

    public ContaPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setBackground(MainFrame.COR_FUNDO);
        setLayout(new BorderLayout());
        criarInterface();
    }

    private void criarInterface() {
        JPanel header = PesquisaMedicoPanel.criarHeaderComVoltar("⚙️ Minha Conta", mainFrame);
        add(header, BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setBackground(MainFrame.COR_FUNDO);
        centro.setBorder(BorderFactory.createEmptyBorder(24, 40, 24, 40));
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

        JPanel cardVerificacao = MainFrame.criarPainelCard();
        cardVerificacao.setLayout(new BoxLayout(cardVerificacao, BoxLayout.Y_AXIS));
        cardVerificacao.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardVerificacao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));

        JLabel tituloVerificacao = MainFrame.criarLabel("Verificação de Senha", 16, true);
        tituloVerificacao.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardVerificacao.add(tituloVerificacao);
        cardVerificacao.add(Box.createVerticalStrut(12));

        JPanel linhaSenha = new JPanel(new BorderLayout(12, 0));
        linhaSenha.setBackground(MainFrame.COR_PAINEL);
        campoSenhaAtual = new JPasswordField();
        campoSenhaAtual.setBackground(MainFrame.COR_CARD);
        campoSenhaAtual.setForeground(MainFrame.COR_TEXTO);
        campoSenhaAtual.setCaretColor(MainFrame.COR_TEXTO);
        campoSenhaAtual.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(MainFrame.COR_BORDA, 1),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
            )
        );
        campoSenhaAtual.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoSenhaAtual.setEchoChar('●');
        campoSenhaAtual.setText("");
        linhaSenha.add(campoSenhaAtual, BorderLayout.CENTER);

        botaoVerificar = MainFrame.criarBotao(
            "Verificar",
            MainFrame.COR_PRIMARIA
        );
        botaoVerificar.setPreferredSize(new Dimension(120, 40));
        botaoVerificar.addActionListener(e -> verificarSenha());
        linhaSenha.add(botaoVerificar, BorderLayout.EAST);
        linhaSenha.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardVerificacao.add(linhaSenha);

        JLabel infoVerificacao = MainFrame.criarLabel(
            "Informe sua senha atual para habilitar as ações abaixo.",
            12,
            false
        );
        infoVerificacao.setForeground(MainFrame.COR_TEXTO_SECUNDARIO);
        infoVerificacao.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardVerificacao.add(Box.createVerticalStrut(6));
        cardVerificacao.add(infoVerificacao);

        centro.add(cardVerificacao);
        centro.add(Box.createVerticalStrut(16));

        JPanel cardAcoes = MainFrame.criarPainelCard();
        cardAcoes.setLayout(new BoxLayout(cardAcoes, BoxLayout.Y_AXIS));
        cardAcoes.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardAcoes.setMaximumSize(new Dimension(Integer.MAX_VALUE, 800));

        JLabel tituloSenha = MainFrame.criarLabel("Alterar Senha", 16, true);
        tituloSenha.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardAcoes.add(tituloSenha);
        cardAcoes.add(Box.createVerticalStrut(8));

        JPanel linhaNovaSenha = new JPanel(new BorderLayout(12, 0));
        linhaNovaSenha.setBackground(MainFrame.COR_PAINEL);
        campoNovaSenha = new JPasswordField();
        campoNovaSenha.setBackground(MainFrame.COR_CARD);
        campoNovaSenha.setForeground(MainFrame.COR_TEXTO);
        campoNovaSenha.setCaretColor(MainFrame.COR_TEXTO);
        campoNovaSenha.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(MainFrame.COR_BORDA, 1),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
            )
        );
        campoNovaSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoNovaSenha.setEchoChar('●');
        campoNovaSenha.setText("");
        campoNovaSenha.setEnabled(false);
        linhaNovaSenha.add(campoNovaSenha, BorderLayout.CENTER);

        botaoAlterarSenha = MainFrame.criarBotao(
            "Alterar",
            MainFrame.COR_SUCESSO
        );
        botaoAlterarSenha.setEnabled(false);
        botaoAlterarSenha.setPreferredSize(new Dimension(120, 40));
        botaoAlterarSenha.addActionListener(e -> alterarSenha());
        linhaNovaSenha.add(botaoAlterarSenha, BorderLayout.EAST);
        linhaNovaSenha.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardAcoes.add(linhaNovaSenha);
        cardAcoes.add(Box.createVerticalStrut(16));

        botaoDeletarConta = MainFrame.criarBotao(
            "🗑️ Deletar Minha Conta",
            MainFrame.COR_ERRO
        );
        botaoDeletarConta.setEnabled(false);
        botaoDeletarConta.setAlignmentX(Component.LEFT_ALIGNMENT);
        botaoDeletarConta.addActionListener(e -> deletarConta());
        cardAcoes.add(botaoDeletarConta);
        cardAcoes.add(Box.createVerticalStrut(16));

        centro.add(cardAcoes);
        centro.add(Box.createVerticalStrut(16));

        cardPaciente = MainFrame.criarPainelCard();
        cardPaciente.setLayout(new BoxLayout(cardPaciente, BoxLayout.Y_AXIS));
        cardPaciente.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardPaciente.setMaximumSize(new Dimension(Integer.MAX_VALUE, 240));

        JLabel tituloPaciente = MainFrame.criarLabel("Área do Paciente", 16, true);
        tituloPaciente.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardPaciente.add(tituloPaciente);
        cardPaciente.add(Box.createVerticalStrut(8));

        JPanel linhaPlano = new JPanel(new BorderLayout(12, 0));
        linhaPlano.setBackground(MainFrame.COR_PAINEL);
        comboPlano = new JComboBox<>(new String[] {
            "Nenhum", "Unimed", "Hapvida"
        });
        comboPlano.setBackground(MainFrame.COR_CARD);
        comboPlano.setForeground(MainFrame.COR_TEXTO);
        comboPlano.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboPlano.setEnabled(false);
        linhaPlano.add(comboPlano, BorderLayout.CENTER);

        botaoAlterarPlano = MainFrame.criarBotao(
            "Alterar Plano",
            MainFrame.COR_PRIMARIA
        );
        botaoAlterarPlano.setEnabled(false);
        botaoAlterarPlano.setPreferredSize(new Dimension(140, 40));
        botaoAlterarPlano.addActionListener(e -> alterarPlano());
        linhaPlano.add(botaoAlterarPlano, BorderLayout.EAST);
        linhaPlano.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardPaciente.add(linhaPlano);
        cardPaciente.add(Box.createVerticalStrut(12));

        JPanel linhaCreditos = new JPanel(new BorderLayout(12, 0));
        linhaCreditos.setBackground(MainFrame.COR_PAINEL);
        campoCreditos = new JTextField();
        campoCreditos.setBackground(MainFrame.COR_CARD);
        campoCreditos.setForeground(MainFrame.COR_TEXTO);
        campoCreditos.setCaretColor(MainFrame.COR_TEXTO);
        campoCreditos.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(MainFrame.COR_BORDA, 1),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
            )
        );
        campoCreditos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoCreditos.setText("");
        campoCreditos.setEnabled(false);
        linhaCreditos.add(campoCreditos, BorderLayout.CENTER);

        botaoAdicionarCreditos = MainFrame.criarBotao(
            "Adicionar Créditos",
            MainFrame.COR_SUCESSO
        );
        botaoAdicionarCreditos.setEnabled(false);
        botaoAdicionarCreditos.setPreferredSize(new Dimension(160, 40));
        botaoAdicionarCreditos.addActionListener(e -> adicionarCreditos());
        linhaCreditos.add(botaoAdicionarCreditos, BorderLayout.EAST);
        linhaCreditos.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardPaciente.add(linhaCreditos);

        centro.add(cardPaciente);
        centro.add(Box.createVerticalStrut(16));

        cardMedico = MainFrame.criarPainelCard();
        cardMedico.setLayout(new BoxLayout(cardMedico, BoxLayout.Y_AXIS));
        cardMedico.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardMedico.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));

        JLabel tituloMedico = MainFrame.criarLabel("Área do Médico", 16, true);
        tituloMedico.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardMedico.add(tituloMedico);
        cardMedico.add(Box.createVerticalStrut(8));

        JPanel linhaEspecialidade = new JPanel(new BorderLayout(12, 0));
        linhaEspecialidade.setBackground(MainFrame.COR_PAINEL);
        comboEspecialidade = new JComboBox<>(new String[] {
            "Cardiologista", "Dermatologista", "Pediatra"
        });
        comboEspecialidade.setBackground(MainFrame.COR_CARD);
        comboEspecialidade.setForeground(MainFrame.COR_TEXTO);
        comboEspecialidade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboEspecialidade.setEnabled(false);
        linhaEspecialidade.add(comboEspecialidade, BorderLayout.CENTER);

        botaoAlterarEspecialidade = MainFrame.criarBotao(
            "Alterar Especialidade",
            MainFrame.COR_PRIMARIA
        );
        botaoAlterarEspecialidade.setEnabled(false);
        botaoAlterarEspecialidade.setPreferredSize(new Dimension(180, 40));
        botaoAlterarEspecialidade.addActionListener(e -> alterarEspecialidade());
        linhaEspecialidade.add(botaoAlterarEspecialidade, BorderLayout.EAST);
        linhaEspecialidade.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardMedico.add(linhaEspecialidade);

        centro.add(cardMedico);
        centro.add(Box.createVerticalStrut(16));

        JScrollPane scroll = new JScrollPane(centro);
        scroll.setBackground(MainFrame.COR_FUNDO);
        scroll.getViewport().setBackground(MainFrame.COR_FUNDO);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
    }

    public void atualizar() {
        senhaVerificada = false;
        campoSenhaAtual.setText("");
        campoNovaSenha.setText("");
        campoNovaSenha.setEnabled(false);
        botaoAlterarSenha.setEnabled(false);
        botaoDeletarConta.setEnabled(false);
        botaoAlterarSenha.setText("Alterar");

        Object usuario = mainFrame.getUsuarioLogado();
        cardPaciente.setVisible(usuario instanceof Paciente);
        cardMedico.setVisible(usuario instanceof Medico);

        if (usuario instanceof Paciente) {
            Paciente p = (Paciente) usuario;
            comboPlano.setSelectedItem(
                p.getPlanoSaude() == null || p.getPlanoSaude().isEmpty()
                    ? "Nenhum" : p.getPlanoSaude()
            );
            comboPlano.setEnabled(false);
            botaoAlterarPlano.setEnabled(false);
            campoCreditos.setText(String.valueOf(p.getCreditos()));
            campoCreditos.setEnabled(false);
            botaoAdicionarCreditos.setEnabled(false);
        } else if (usuario instanceof Medico) {
            Medico m = (Medico) usuario;
            comboEspecialidade.setSelectedItem(m.getEspecialidade());
            comboEspecialidade.setEnabled(false);
            botaoAlterarEspecialidade.setEnabled(false);
        }
    }

    private void verificarSenha() {
        String senha = new String(campoSenhaAtual.getPassword());
        Object usuario = mainFrame.getUsuarioLogado();
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Nenhum usuário logado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String nome = usuario instanceof Paciente
            ? ((Paciente) usuario).getNome()
            : ((Medico) usuario).getNome();

        if (mainFrame.getSistema().login(nome, senha) != null) {
            senhaVerificada = true;
            habilitarCampos(true);
            JOptionPane.showMessageDialog(this, "Senha verificada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            senhaVerificada = false;
            habilitarCampos(false);
            JOptionPane.showMessageDialog(this, "Senha atual incorreta.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void habilitarCampos(boolean habilitar) {
        Object usuario = mainFrame.getUsuarioLogado();
        if (usuario instanceof Paciente) {
            campoNovaSenha.setEnabled(habilitar);
            botaoAlterarSenha.setEnabled(habilitar);
            botaoDeletarConta.setEnabled(habilitar);
            comboPlano.setEnabled(habilitar);
            botaoAlterarPlano.setEnabled(habilitar);
            campoCreditos.setEnabled(habilitar);
            botaoAdicionarCreditos.setEnabled(habilitar);
        } else if (usuario instanceof Medico) {
            campoNovaSenha.setEnabled(habilitar);
            botaoAlterarSenha.setEnabled(habilitar);
            botaoDeletarConta.setEnabled(habilitar);
            comboEspecialidade.setEnabled(habilitar);
            botaoAlterarEspecialidade.setEnabled(habilitar);
        }
    }

    private void alterarSenha() {
        String novaSenha = new String(campoNovaSenha.getPassword());
        if (novaSenha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite a nova senha.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Object usuario = mainFrame.getUsuarioLogado();
        String nome = usuario instanceof Paciente
            ? ((Paciente) usuario).getNome()
            : ((Medico) usuario).getNome();
        String senhaAtual = new String(campoSenhaAtual.getPassword());

        try {
            mainFrame.getSistema().alterarSenha(nome, senhaAtual, novaSenha);
            JOptionPane.showMessageDialog(this, "Senha alterada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            campoNovaSenha.setText("");
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarConta() {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Tem certeza que deseja deletar sua conta? Esta ação não pode ser desfeita.",
            "Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION
        );
        if (confirm != JOptionPane.YES_OPTION) return;

        String senhaAtual = new String(campoSenhaAtual.getPassword());
        Object usuario = mainFrame.getUsuarioLogado();
        String nome = usuario instanceof Paciente
            ? ((Paciente) usuario).getNome()
            : ((Medico) usuario).getNome();

        try {
            mainFrame.getSistema().deletarConta(nome, senhaAtual);
            JOptionPane.showMessageDialog(this, "Conta deletada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            mainFrame.logout();
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterarPlano() {
        Object usuario = mainFrame.getUsuarioLogado();
        if (!(usuario instanceof Paciente)) return;
        Paciente p = (Paciente) usuario;
        String novoPlano = (String) comboPlano.getSelectedItem();
        if (novoPlano.equals("Nenhum")) novoPlano = "";
        String senhaAtual = new String(campoSenhaAtual.getPassword());

        try {
            mainFrame.getSistema().alterarPlanoPaciente(p.getNome(), novoPlano, senhaAtual);
            JOptionPane.showMessageDialog(this, "Plano alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarCreditos() {
        Object usuario = mainFrame.getUsuarioLogado();
        if (!(usuario instanceof Paciente)) return;
        String valorStr = campoCreditos.getText().trim();
        if (valorStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o valor dos créditos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double valor;
        try {
            valor = Double.parseDouble(valorStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (valor <= 0) {
            JOptionPane.showMessageDialog(this, "O valor deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String senhaAtual = new String(campoSenhaAtual.getPassword());
        Paciente p = (Paciente) usuario;

        try {
            mainFrame.getSistema().adicionarCreditos(p.getNome(), valor, senhaAtual);
            JOptionPane.showMessageDialog(
                this,
                "R$ " + new DecimalFormat("#,##0.00").format(valor) + " adicionados aos seus créditos!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
            );
            campoCreditos.setText(String.valueOf(p.getCreditos()));
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterarEspecialidade() {
        Object usuario = mainFrame.getUsuarioLogado();
        if (!(usuario instanceof Medico)) return;
        Medico m = (Medico) usuario;
        String novaEsp = (String) comboEspecialidade.getSelectedItem();
        String senhaAtual = new String(campoSenhaAtual.getPassword());

        try {
            mainFrame.getSistema().alterarEspecialidadeMedico(m.getNome(), novaEsp, senhaAtual);
            JOptionPane.showMessageDialog(this, "Especialidade alterada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
