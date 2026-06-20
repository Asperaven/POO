package TFinalGUI;

import TFinalClasses.*;
import TFinalExcecoes.AvaliacaoInvalidaException;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class AvaliacaoPanel extends JPanel {

    private MainFrame mainFrame;
    private JComboBox<String> comboConsultas;
    private JTextArea areaComentario;
    private JPanel painelEstrelas;
    private int estrelaSelecionada = 0;
    private JLabel[] labelEstrelas;
    private JPanel painelConteudo;
    private ArrayList<Consulta> consultasDisponiveis;

    public AvaliacaoPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.consultasDisponiveis = new ArrayList<>();
        setBackground(MainFrame.COR_FUNDO);
        setLayout(new BorderLayout());
        criarInterface();
    }

    private void criarInterface() {
        JPanel header = PesquisaMedicoPanel.criarHeaderComVoltar(
            "⭐ Avaliações",
            mainFrame
        );
        add(header, BorderLayout.NORTH);

        painelConteudo = new JPanel();
        painelConteudo.setBackground(MainFrame.COR_FUNDO);
        painelConteudo.setLayout(
            new BoxLayout(painelConteudo, BoxLayout.Y_AXIS)
        );
        painelConteudo.setBorder(
            BorderFactory.createEmptyBorder(20, 40, 20, 40)
        );

        JScrollPane scroll = new JScrollPane(painelConteudo);
        scroll.setBackground(MainFrame.COR_FUNDO);
        scroll.getViewport().setBackground(MainFrame.COR_FUNDO);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
    }

    public void atualizar() {
        painelConteudo.removeAll();
        Object usuario = mainFrame.getUsuarioLogado();

        if (usuario instanceof Paciente) {
            criarFormularioAvaliacao((Paciente) usuario);
        } else if (usuario instanceof Medico) {
            criarListaAvaliacoes((Medico) usuario);
        }

        painelConteudo.revalidate();
        painelConteudo.repaint();
    }

    private void criarFormularioAvaliacao(Paciente paciente) {
        JPanel card = MainFrame.criarPainelCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setMaximumSize(new Dimension(700, 500));

        JLabel titulo = MainFrame.criarLabel("Avaliar Consulta", 18, true);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(titulo);
        card.add(Box.createVerticalStrut(16));

        // Selecionar consulta
        JLabel labelConsulta = MainFrame.criarLabel(
            "Selecione a consulta:",
            14,
            false
        );
        labelConsulta.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(labelConsulta);
        card.add(Box.createVerticalStrut(4));

        comboConsultas = new JComboBox<>();
        comboConsultas.setBackground(MainFrame.COR_CARD);
        comboConsultas.setForeground(MainFrame.COR_TEXTO);
        comboConsultas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboConsultas.setMaximumSize(new Dimension(660, 40));
        comboConsultas.setAlignmentX(Component.LEFT_ALIGNMENT);

        ArrayList<Consulta> todasConsultas = mainFrame
            .getSistema()
            .getConsultasPaciente(paciente);
        consultasDisponiveis = new ArrayList<>();
        for (Consulta c : todasConsultas) {
            boolean jaAvaliado = false;
            for (Avaliacao av : c.getMedico().getAvalicoes()) {
                if (av.getPaciente().getNome().equals(paciente.getNome())) {
                    jaAvaliado = true;
                    break;
                }
            }
            if (!jaAvaliado) {
                consultasDisponiveis.add(c);
            }
        }

        if (consultasDisponiveis.isEmpty()) {
            comboConsultas.addItem("Nenhuma consulta pendente de avaliação");
        } else {
            for (Consulta c : consultasDisponiveis) {
                comboConsultas.addItem(
                    c.getData() +
                        " — " +
                        c.getMedico().getNome() +
                        " (" +
                        c.getMedico().getEspecialidade() +
                        ")"
                );
            }
        }
        card.add(comboConsultas);
        card.add(Box.createVerticalStrut(16));

        // Estrelas
        JLabel labelEstrelasTitulo = MainFrame.criarLabel("Nota:", 14, false);
        labelEstrelasTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(labelEstrelasTitulo);
        card.add(Box.createVerticalStrut(4));

        painelEstrelas = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        painelEstrelas.setBackground(MainFrame.COR_PAINEL);
        painelEstrelas.setAlignmentX(Component.LEFT_ALIGNMENT);

        labelEstrelas = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            final int nota = i + 1;
            labelEstrelas[i] = new JLabel("\u2606");
            labelEstrelas[i].setFont(
                new Font("Segoe UI Symbol", Font.PLAIN, 32)
            );
            labelEstrelas[i].setForeground(new Color(250, 204, 21));
            labelEstrelas[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            labelEstrelas[i].addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        estrelaSelecionada = nota;
                        atualizarEstrelas();
                    }

                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        for (int j = 0; j < 5; j++) {
                            labelEstrelas[j].setText(
                                j < nota ? "\u2605" : "\u2606"
                            );
                        }
                    }

                    public void mouseExited(java.awt.event.MouseEvent e) {
                        atualizarEstrelas();
                    }
                }
            );
            painelEstrelas.add(labelEstrelas[i]);
        }
        card.add(painelEstrelas);
        card.add(Box.createVerticalStrut(16));

        // Comentário
        JLabel labelComentario = MainFrame.criarLabel("Comentário:", 14, false);
        labelComentario.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(labelComentario);
        card.add(Box.createVerticalStrut(4));

        areaComentario = MainFrame.criarAreaTexto();
        areaComentario.setRows(4);
        JScrollPane scrollComentario = new JScrollPane(areaComentario);
        scrollComentario.setMaximumSize(new Dimension(660, 120));
        scrollComentario.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollComentario.setBorder(
            BorderFactory.createLineBorder(MainFrame.COR_BORDA)
        );
        card.add(scrollComentario);
        card.add(Box.createVerticalStrut(20));

        // Botão enviar
        JButton botaoEnviar = MainFrame.criarBotao(
            "⭐ Enviar Avaliação",
            new Color(250, 204, 21).darker()
        );
        botaoEnviar.setAlignmentX(Component.LEFT_ALIGNMENT);
        botaoEnviar.addActionListener(e -> enviarAvaliacao());
        card.add(botaoEnviar);

        painelConteudo.add(card);
    }

    private void criarListaAvaliacoes(Medico medico) {
        JPanel card = MainFrame.criarPainelCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setMaximumSize(new Dimension(700, 600));

        // Header com média
        JPanel linhaMedia = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        linhaMedia.setBackground(MainFrame.COR_PAINEL);
        JLabel labelMedia = MainFrame.criarLabel("Média geral:", 16, true);
        linhaMedia.add(labelMedia);

        double media = medico.getMediaEstrelas();
        JLabel labelNota = MainFrame.criarLabel(
            media > 0 ? String.format("%.1f \u2605", media) : "Sem avaliações",
            20,
            true
        );
        labelNota.setForeground(new Color(250, 204, 21));
        linhaMedia.add(labelNota);
        linhaMedia.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(linhaMedia);
        card.add(Box.createVerticalStrut(16));

        // Lista de avaliações
        ArrayList<Avaliacao> avaliacoes = medico.getAvalicoes();
        if (avaliacoes.isEmpty()) {
            JLabel labelVazio = MainFrame.criarLabel(
                "Nenhuma avaliação recebida ainda.",
                14,
                false
            );
            labelVazio.setForeground(MainFrame.COR_TEXTO_SECUNDARIO);
            labelVazio.setAlignmentX(Component.LEFT_ALIGNMENT);
            card.add(labelVazio);
        } else {
            for (Avaliacao av : avaliacoes) {
                JPanel cardAv = new JPanel();
                cardAv.setLayout(new BoxLayout(cardAv, BoxLayout.Y_AXIS));
                cardAv.setBackground(MainFrame.COR_CARD);
                cardAv.setBorder(
                    BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(MainFrame.COR_BORDA),
                        BorderFactory.createEmptyBorder(12, 16, 12, 16)
                    )
                );
                cardAv.setMaximumSize(new Dimension(660, 100));
                cardAv.setAlignmentX(Component.LEFT_ALIGNMENT);

                JPanel linhaAv = new JPanel(
                    new FlowLayout(FlowLayout.LEFT, 8, 0)
                );
                linhaAv.setBackground(MainFrame.COR_CARD);
                linhaAv.add(
                    MainFrame.criarLabel(av.getPaciente().getNome(), 14, true)
                );
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 5; i++) sb.append(
                    i < av.getEstrelas() ? "\u2605" : "\u2606"
                );
                JLabel estrelas = new JLabel(sb.toString());
                estrelas.setForeground(new Color(250, 204, 21));
                estrelas.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
                linhaAv.add(estrelas);
                linhaAv.setAlignmentX(Component.LEFT_ALIGNMENT);
                cardAv.add(linhaAv);

                JLabel labelDesc = MainFrame.criarLabel(
                    "\"" + av.getDescricao() + "\"",
                    13,
                    false
                );
                labelDesc.setForeground(MainFrame.COR_TEXTO_SECUNDARIO);
                labelDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
                cardAv.add(labelDesc);

                card.add(cardAv);
                card.add(Box.createVerticalStrut(8));
            }
        }

        painelConteudo.add(card);
    }

    private void atualizarEstrelas() {
        for (int i = 0; i < 5; i++) {
            labelEstrelas[i].setText(
                i < estrelaSelecionada ? "\u2605" : "\u2606"
            );
        }
    }

    private void enviarAvaliacao() {
        if (consultasDisponiveis.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Você não tem consultas para avaliar.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (estrelaSelecionada == 0) {
            JOptionPane.showMessageDialog(
                this,
                "Selecione uma nota de 1 a 5 estrelas.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String comentario = areaComentario.getText().trim();
        if (comentario.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Escreva um comentário sobre a consulta.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int idx = comboConsultas.getSelectedIndex();
        if (idx < 0 || idx >= consultasDisponiveis.size()) return;

        Consulta consulta = consultasDisponiveis.get(idx);
        Paciente paciente = (Paciente) mainFrame.getUsuarioLogado();

        try {
            mainFrame
                .getSistema()
                .registrarAvaliacao(
                    consulta.getMedico(),
                    paciente,
                    comentario,
                    estrelaSelecionada
                );
            JOptionPane.showMessageDialog(
                this,
                "Avaliação enviada com sucesso!",
                "Sucesso ⭐",
                JOptionPane.INFORMATION_MESSAGE
            );
            estrelaSelecionada = 0;
            atualizarEstrelas();
            areaComentario.setText("");
            atualizar();
        } catch (AvaliacaoInvalidaException ex) {
            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Erro ao registrar avaliação no banco de dados: " +
                    ex.getMessage(),
                "Erro no Banco de Dados",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
