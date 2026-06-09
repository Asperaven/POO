package TFinalGUI;

import TFinalClasses.*;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    private MainFrame mainFrame;
    private JPanel conteudo;
    private JLabel labelBoasVindas;

    public DashboardPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setBackground(MainFrame.COR_FUNDO);
        setLayout(new BorderLayout());
        criarInterface();
    }

    private void criarInterface() {
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(MainFrame.COR_PAINEL);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, MainFrame.COR_BORDA),
                BorderFactory.createEmptyBorder(12, 24, 12, 24)));

        labelBoasVindas = MainFrame.criarLabel("Bem-vindo!", 18, true);
        header.add(labelBoasVindas, BorderLayout.WEST);

        JButton botaoLogout = MainFrame.criarBotao("Sair", MainFrame.COR_ERRO);
        botaoLogout.setPreferredSize(new Dimension(100, 36));
        botaoLogout.addActionListener(e -> mainFrame.logout());
        header.add(botaoLogout, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // Conteúdo central
        conteudo = new JPanel(new GridBagLayout());
        conteudo.setBackground(MainFrame.COR_FUNDO);
        add(conteudo, BorderLayout.CENTER);
    }

    public void atualizar() {
        Object usuario = mainFrame.getUsuarioLogado();
        if (usuario == null)
            return;

        conteudo.removeAll();

        if (usuario instanceof Paciente) {
            Paciente p = (Paciente) usuario;
            labelBoasVindas.setText("👋 Olá, " + p.getNome() + "!");
            criarMenuPaciente();
        } else if (usuario instanceof Medico) {
            Medico m = (Medico) usuario;
            labelBoasVindas.setText("👋 Olá, Dr(a). " + m.getNome() + "!");
            criarMenuMedico();
        }

        conteudo.revalidate();
        conteudo.repaint();
    }

    private void criarMenuPaciente() {
        JPanel grid = new JPanel(new GridLayout(2, 2, 20, 20));
        grid.setBackground(MainFrame.COR_FUNDO);
        grid.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        grid.add(criarCardMenu("🔍", "Pesquisar Médicos",
                "Encontre médicos por nome ou especialidade",
                MainFrame.COR_PRIMARIA,
                () -> mainFrame.mostrarTela(MainFrame.PESQUISA_MEDICO)));

        grid.add(criarCardMenu("📅", "Meus Agendamentos",
                "Visualize e gerencie seus agendamentos",
                MainFrame.COR_SECUNDARIA,
                () -> mainFrame.mostrarTela(MainFrame.AGENDAMENTO)));

        grid.add(criarCardMenu("📋", "Meu Histórico",
                "Veja o prontuário de consultas realizadas",
                MainFrame.COR_SUCESSO,
                () -> mainFrame.mostrarTela(MainFrame.HISTORICO)));

        grid.add(criarCardMenu("⭐", "Avaliar Consulta",
                "Deixe uma avaliação para o médico",
                new Color(250, 204, 21),
                () -> mainFrame.mostrarTela(MainFrame.AVALIACAO)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        conteudo.add(grid, gbc);
    }

    private void criarMenuMedico() {
        JPanel grid = new JPanel(new GridLayout(2, 2, 20, 20));
        grid.setBackground(MainFrame.COR_FUNDO);
        grid.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        grid.add(criarCardMenu("📅", "Minha Agenda",
                "Veja os agendamentos do dia",
                MainFrame.COR_PRIMARIA,
                () -> mainFrame.mostrarTela(MainFrame.AGENDAMENTO)));

        grid.add(criarCardMenu("🩺", "Realizar Consulta",
                "Registre uma consulta com o paciente",
                MainFrame.COR_SUCESSO,
                () -> mainFrame.mostrarTela(MainFrame.CONSULTA)));

        grid.add(criarCardMenu("⭐", "Minhas Avaliações",
                "Veja as avaliações recebidas",
                new Color(250, 204, 21),
                () -> mainFrame.mostrarTela(MainFrame.AVALIACAO)));

        grid.add(criarCardMenu("📊", "Estatísticas",
                "Veja as estatísticas do sistema",
                MainFrame.COR_SECUNDARIA,
                () -> mainFrame.mostrarTela(MainFrame.ESTATISTICAS)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        conteudo.add(grid, gbc);
    }

    private JPanel criarCardMenu(String emoji, String titulo, String descricao,
            Color corAccent, Runnable acao) {
        JPanel card = MainFrame.criarPainelCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Borda colorida no topo
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(3, 0, 0, 0, corAccent),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        JLabel labelEmoji = new JLabel(emoji);
        labelEmoji.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        labelEmoji.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(labelEmoji);
        card.add(Box.createVerticalStrut(12));

        JLabel labelTitulo = MainFrame.criarLabel(titulo, 18, true);
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(labelTitulo);
        card.add(Box.createVerticalStrut(6));

        JLabel labelDescricao = MainFrame.criarLabel(descricao, 13, false);
        labelDescricao.setForeground(MainFrame.COR_TEXTO_SECUNDARIO);
        labelDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(labelDescricao);

        // Hover effect
        Color corOriginal = MainFrame.COR_PAINEL;
        Color corHover = MainFrame.COR_CARD;
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                card.setBackground(corHover);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                card.setBackground(corOriginal);
            }

            public void mouseClicked(java.awt.event.MouseEvent e) {
                acao.run();
            }
        });

        return card;
    }
}
