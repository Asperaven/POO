package TFinalGUI;

import TFinalClasses.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class PesquisaMedicoPanel extends JPanel {
    private MainFrame mainFrame;
    private JTextField campoBusca;
    private JTable tabelaResultados;
    private DefaultTableModel modeloTabela;
    private ArrayList<Medico> resultados;

    public PesquisaMedicoPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.resultados = new ArrayList<>();
        setBackground(MainFrame.COR_FUNDO);
        setLayout(new BorderLayout());
        criarInterface();
    }

    private void criarInterface() {
        // Header
        JPanel header = criarHeader("🔍 Pesquisar Médicos");
        add(header, BorderLayout.NORTH);

        // Painel central
        JPanel centro = new JPanel(new BorderLayout(0, 16));
        centro.setBackground(MainFrame.COR_FUNDO);
        centro.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        // Barra de busca
        JPanel barraBusca = new JPanel(new BorderLayout(12, 0));
        barraBusca.setBackground(MainFrame.COR_FUNDO);

        campoBusca = MainFrame.criarCampoTexto("Buscar por nome ou especialidade...");
        campoBusca.setPreferredSize(new Dimension(400, 42));
        barraBusca.add(campoBusca, BorderLayout.CENTER);

        JButton botaoBuscar = MainFrame.criarBotao("Buscar", MainFrame.COR_PRIMARIA);
        botaoBuscar.setPreferredSize(new Dimension(120, 42));
        botaoBuscar.addActionListener(e -> realizarBusca());
        barraBusca.add(botaoBuscar, BorderLayout.EAST);

        centro.add(barraBusca, BorderLayout.NORTH);

        // Tabela de resultados
        String[] colunas = { "Nome", "Especialidade", "Avaliação", "Valor (R$)" };
        modeloTabela = new DefaultTableModel(colunas, 0) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tabelaResultados = criarTabela(modeloTabela);
        JScrollPane scroll = new JScrollPane(tabelaResultados);
        scroll.setBackground(MainFrame.COR_PAINEL);
        scroll.getViewport().setBackground(MainFrame.COR_PAINEL);
        scroll.setBorder(BorderFactory.createLineBorder(MainFrame.COR_BORDA));
        centro.add(scroll, BorderLayout.CENTER);

        // Botão agendar
        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rodape.setBackground(MainFrame.COR_FUNDO);
        JButton botaoAgendar = MainFrame.criarBotao("📅 Agendar Consulta", MainFrame.COR_SUCESSO);
        botaoAgendar.addActionListener(e -> {
            int linhaSelecionada = tabelaResultados.getSelectedRow();
            if (linhaSelecionada < 0) {
                JOptionPane.showMessageDialog(this,
                        "Selecione um médico para agendar.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Medico medicoSelecionado = resultados.get(linhaSelecionada);
            mainFrame.mostrarAgendamentoParaMedico(medicoSelecionado);
        });
        rodape.add(botaoAgendar);
        centro.add(rodape, BorderLayout.SOUTH);

        add(centro, BorderLayout.CENTER);
    }

    public void atualizar() {
        realizarBusca();
    }

    private void realizarBusca() {
        String termo = MainFrame.getTextoOuVazio(campoBusca, "Buscar por nome ou especialidade...");
        Object usuario = mainFrame.getUsuarioLogado();
        String plano = "";
        if (usuario instanceof Paciente) {
            plano = ((Paciente) usuario).getPlanoSaude();
        }
        resultados = mainFrame.getSistema().pesquisarMedicos(termo, plano);
        modeloTabela.setRowCount(0);
        for (Medico m : resultados) {
            double media = m.getMediaEstrelas();
            String estrelas = media > 0 ? String.format("%.1f ★", media) : "Sem avaliações";
            modeloTabela.addRow(new Object[] {
                    m.getNome(),
                    m.getEspecialidade(),
                    estrelas,
                    String.format("%.2f", m.getValorConsulta())
            });
        }
    }

    // ==================== COMPONENTES UTILITÁRIOS ====================

    static JPanel criarHeader(String titulo) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(MainFrame.COR_PAINEL);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, MainFrame.COR_BORDA),
                BorderFactory.createEmptyBorder(12, 24, 12, 24)));
        JLabel label = MainFrame.criarLabel(titulo, 18, true);
        header.add(label, BorderLayout.WEST);
        return header;
    }

    static JPanel criarHeaderComVoltar(String titulo, MainFrame mainFrame) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(MainFrame.COR_PAINEL);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, MainFrame.COR_BORDA),
                BorderFactory.createEmptyBorder(12, 24, 12, 24)));
        JLabel label = MainFrame.criarLabel(titulo, 18, true);
        header.add(label, BorderLayout.WEST);

        JButton botaoVoltar = MainFrame.criarBotao("← Voltar", MainFrame.COR_CARD);
        botaoVoltar.setPreferredSize(new Dimension(120, 36));
        botaoVoltar.addActionListener(e -> mainFrame.mostrarTela(MainFrame.DASHBOARD));
        header.add(botaoVoltar, BorderLayout.EAST);
        return header;
    }

    static JTable criarTabela(DefaultTableModel modelo) {
        JTable tabela = new JTable(modelo);
        tabela.setBackground(MainFrame.COR_PAINEL);
        tabela.setForeground(MainFrame.COR_TEXTO);
        tabela.setGridColor(MainFrame.COR_BORDA);
        tabela.setSelectionBackground(MainFrame.COR_PRIMARIA.darker());
        tabela.setSelectionForeground(Color.WHITE);
        tabela.setRowHeight(36);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabela.setShowVerticalLines(false);
        tabela.setIntercellSpacing(new Dimension(0, 1));

        // Header styling
        tabela.getTableHeader().setBackground(MainFrame.COR_CARD);
        tabela.getTableHeader().setForeground(MainFrame.COR_TEXTO);
        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabela.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, MainFrame.COR_BORDA));

        // Cell renderer
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        return tabela;
    }
}
