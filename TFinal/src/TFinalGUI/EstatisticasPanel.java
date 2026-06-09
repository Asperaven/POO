package TFinalGUI;

import TFinalClasses.*;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class EstatisticasPanel extends JPanel {
        private MainFrame mainFrame;
        private JPanel painelCards;

        public EstatisticasPanel(MainFrame mainFrame) {
                this.mainFrame = mainFrame;
                setBackground(MainFrame.COR_FUNDO);
                setLayout(new BorderLayout());
                criarInterface();
        }

        private void criarInterface() {
                JPanel header = PesquisaMedicoPanel.criarHeaderComVoltar("📊 Estatísticas do Sistema", mainFrame);
                add(header, BorderLayout.NORTH);

                painelCards = new JPanel();
                painelCards.setBackground(MainFrame.COR_FUNDO);
                painelCards.setLayout(new BoxLayout(painelCards, BoxLayout.Y_AXIS));
                painelCards.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

                JScrollPane scroll = new JScrollPane(painelCards);
                scroll.setBackground(MainFrame.COR_FUNDO);
                scroll.getViewport().setBackground(MainFrame.COR_FUNDO);
                scroll.setBorder(null);
                add(scroll, BorderLayout.CENTER);
        }

        public void atualizar() {
                painelCards.removeAll();
                SistemaClinica sistema = mainFrame.getSistema();

                // Linha 1: Cards de resumo
                JPanel linhaResumo = new JPanel(new GridLayout(1, 4, 16, 0));
                linhaResumo.setBackground(MainFrame.COR_FUNDO);
                linhaResumo.setMaximumSize(new Dimension(900, 130));
                linhaResumo.setAlignmentX(Component.LEFT_ALIGNMENT);

                // Melhor médico
                Medico melhor = sistema.getMedicoMaisBemAvaliado();
                String nomeMelhor = melhor != null && melhor.getMediaEstrelas() > 0
                                ? melhor.getNome()
                                : "N/A";
                String notaMelhor = melhor != null && melhor.getMediaEstrelas() > 0
                                ? String.format("%.1f ★", melhor.getMediaEstrelas())
                                : "—";
                linhaResumo.add(criarCardEstatistica("🏆", "Melhor Avaliado",
                                nomeMelhor, notaMelhor, new Color(250, 204, 21)));

                // Especialidade mais procurada
                String espPopular = sistema.getEspecialidadeMaisProcurada();
                linhaResumo.add(criarCardEstatistica("🔥", "Mais Procurada",
                                espPopular, "especialidade", MainFrame.COR_ERRO));

                // Total de consultas
                int total = sistema.getTotalConsultas();
                linhaResumo.add(criarCardEstatistica("📋", "Total Consultas",
                                String.valueOf(total), "realizadas", MainFrame.COR_PRIMARIA));

                // Média geral
                double mediaGeral = sistema.getMediaGeralAvaliacoes();
                linhaResumo.add(criarCardEstatistica("⭐", "Média Geral",
                                mediaGeral > 0 ? String.format("%.1f", mediaGeral) : "N/A",
                                "estrelas", MainFrame.COR_SUCESSO));

                painelCards.add(linhaResumo);
                painelCards.add(Box.createVerticalStrut(24));

                // Consultas por médico
                JPanel cardGrafico = MainFrame.criarPainelCard();
                cardGrafico.setLayout(new BoxLayout(cardGrafico, BoxLayout.Y_AXIS));
                cardGrafico.setMaximumSize(new Dimension(900, 400));
                cardGrafico.setAlignmentX(Component.LEFT_ALIGNMENT);

                JLabel tituloGrafico = MainFrame.criarLabel("📊 Consultas por Médico", 16, true);
                tituloGrafico.setAlignmentX(Component.LEFT_ALIGNMENT);
                cardGrafico.add(tituloGrafico);
                cardGrafico.add(Box.createVerticalStrut(16));

                Map<String, Integer> consultasPorMedico = sistema.getConsultasPorMedico();
                int maxConsultas = consultasPorMedico.values().stream()
                                .mapToInt(Integer::intValue).max().orElse(1);
                if (maxConsultas == 0)
                        maxConsultas = 1;

                for (Map.Entry<String, Integer> entry : consultasPorMedico.entrySet()) {
                        JPanel linhaBarra = new JPanel(new BorderLayout(12, 0));
                        linhaBarra.setBackground(MainFrame.COR_PAINEL);
                        linhaBarra.setMaximumSize(new Dimension(820, 36));
                        linhaBarra.setAlignmentX(Component.LEFT_ALIGNMENT);

                        JLabel labelNome = MainFrame.criarLabel(entry.getKey(), 13, false);
                        labelNome.setPreferredSize(new Dimension(160, 30));
                        linhaBarra.add(labelNome, BorderLayout.WEST);

                        // Barra de progresso
                        int valor = entry.getValue();
                        final int maxVal = maxConsultas;
                        JPanel barra = new JPanel() {
                                @Override
                                protected void paintComponent(Graphics g) {
                                        super.paintComponent(g);
                                        Graphics2D g2d = (Graphics2D) g;
                                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                                        RenderingHints.VALUE_ANTIALIAS_ON);

                                        int largura = (int) ((double) valor / maxVal * (getWidth() - 60));
                                        if (largura < 4)
                                                largura = 4;

                                        // Gradiente
                                        GradientPaint gradient = new GradientPaint(0, 0, MainFrame.COR_PRIMARIA,
                                                        largura, 0, MainFrame.COR_SECUNDARIA);
                                        g2d.setPaint(gradient);
                                        g2d.fillRoundRect(0, 6, largura, 20, 10, 10);

                                        // Valor
                                        g2d.setColor(MainFrame.COR_TEXTO);
                                        g2d.setFont(new Font("Segoe UI", Font.BOLD, 12));
                                        g2d.drawString(String.valueOf(valor), largura + 8, 22);
                                }
                        };
                        barra.setBackground(MainFrame.COR_PAINEL);
                        barra.setPreferredSize(new Dimension(400, 32));
                        linhaBarra.add(barra, BorderLayout.CENTER);

                        cardGrafico.add(linhaBarra);
                        cardGrafico.add(Box.createVerticalStrut(4));
                }

                painelCards.add(cardGrafico);

                painelCards.revalidate();
                painelCards.repaint();
        }

        private JPanel criarCardEstatistica(String emoji, String titulo, String valor,
                        String subtitulo, Color corAccent) {
                JPanel card = MainFrame.criarPainelCard();
                card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
                card.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createMatteBorder(3, 0, 0, 0, corAccent),
                                BorderFactory.createEmptyBorder(16, 16, 16, 16)));

                JLabel labelEmoji = new JLabel(emoji);
                labelEmoji.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
                labelEmoji.setAlignmentX(Component.LEFT_ALIGNMENT);
                labelEmoji.setForeground(MainFrame.COR_PRIMARIA);
                card.add(labelEmoji);
                card.add(Box.createVerticalStrut(6));

                JLabel labelTitulo = MainFrame.criarLabel(titulo, 12, false);
                labelTitulo.setForeground(MainFrame.COR_TEXTO_SECUNDARIO);
                labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
                card.add(labelTitulo);
                card.add(Box.createVerticalStrut(4));

                JLabel labelValor = MainFrame.criarLabel(valor, 20, true);
                labelValor.setForeground(corAccent);
                labelValor.setAlignmentX(Component.LEFT_ALIGNMENT);
                card.add(labelValor);

                JLabel labelSub = MainFrame.criarLabel(subtitulo, 11, false);
                labelSub.setForeground(MainFrame.COR_TEXTO_SECUNDARIO);
                labelSub.setAlignmentX(Component.LEFT_ALIGNMENT);
                card.add(labelSub);

                return card;
        }
}
