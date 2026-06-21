package TFinalGUI;

import TFinalClasses.*;
import TFinalExcecoes.AgendaLotadaException;
import TFinalExcecoes.PlanoInvalidoException;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AgendamentoPanel extends JPanel {

    private MainFrame mainFrame;
    private Medico medicoSelecionado;

    private JLabel labelMedico;
    private JTextField campoData;
    private JTable tabelaAgendamentos;
    private DefaultTableModel modeloTabela;
    private ArrayList<Agendamento> listaAgendamentos;

    public AgendamentoPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.listaAgendamentos = new ArrayList<>();
        setBackground(MainFrame.COR_FUNDO);
        setLayout(new BorderLayout());
        criarInterface();
    }

    private void criarInterface() {
        JPanel header = PesquisaMedicoPanel.criarHeaderComVoltar(
            "📅 Agendamentos",
            mainFrame
        );
        add(header, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(0, 16));
        centro.setBackground(MainFrame.COR_FUNDO);
        centro.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        // Painel de novo agendamento
        JPanel painelNovo = MainFrame.criarPainelCard();
        painelNovo.setLayout(new BoxLayout(painelNovo, BoxLayout.Y_AXIS));

        JLabel tituloNovo = MainFrame.criarLabel("Novo Agendamento", 16, true);
        tituloNovo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelNovo.add(tituloNovo);
        painelNovo.add(Box.createVerticalStrut(12));

        // Médico selecionado
        JPanel linhaMedico = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linhaMedico.setBackground(MainFrame.COR_PAINEL);
        linhaMedico.add(MainFrame.criarLabel("Médico: ", 14, true));
        labelMedico = MainFrame.criarLabel("Nenhum selecionado", 14, false);
        labelMedico.setForeground(MainFrame.COR_PRIMARIA);
        linhaMedico.add(labelMedico);
        linhaMedico.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelNovo.add(linhaMedico);
        painelNovo.add(Box.createVerticalStrut(8));

        // Data
        JPanel linhaData = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        linhaData.setBackground(MainFrame.COR_PAINEL);
        linhaData.add(MainFrame.criarLabel("Data (AAAA-MM-DD):", 14, false));
        campoData = MainFrame.criarCampoTexto("2026-06-15");
        campoData.setPreferredSize(new Dimension(180, 40));
        linhaData.add(campoData);

        JButton botaoAgendar = MainFrame.criarBotao(
            "Agendar",
            MainFrame.COR_SUCESSO
        );
        botaoAgendar.setPreferredSize(new Dimension(130, 40));
        botaoAgendar.addActionListener(e -> realizarAgendamento());
        linhaData.add(botaoAgendar);
        linhaData.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelNovo.add(linhaData);

        centro.add(painelNovo, BorderLayout.NORTH);

        // Tabela de agendamentos existentes
        String[] colunas = { "Médico", "Paciente", "Data" };
        modeloTabela = new DefaultTableModel(colunas, 0) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tabelaAgendamentos = PesquisaMedicoPanel.criarTabela(modeloTabela);
        JScrollPane scroll = new JScrollPane(tabelaAgendamentos);
        scroll.setBackground(MainFrame.COR_PAINEL);
        scroll.getViewport().setBackground(MainFrame.COR_PAINEL);
        scroll.setBorder(BorderFactory.createLineBorder(MainFrame.COR_BORDA));
        centro.add(scroll, BorderLayout.CENTER);

        // Botão cancelar
        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rodape.setBackground(MainFrame.COR_FUNDO);
        JButton botaoCancelar = MainFrame.criarBotao(
            "❌ Cancelar Agendamento",
            MainFrame.COR_ERRO
        );
        botaoCancelar.addActionListener(e -> cancelarAgendamento());
        rodape.add(botaoCancelar);
        centro.add(rodape, BorderLayout.SOUTH);

        add(centro, BorderLayout.CENTER);
    }

    public void setMedicoSelecionado(Medico medico) {
        this.medicoSelecionado = medico;
        if (medico != null) {
            labelMedico.setText(
                medico.getNome() +
                    " — R$ " +
                    String.format("%.2f", medico.getValorConsulta())
            );
        }
    }

    public void atualizar() {
        Object usuario = mainFrame.getUsuarioLogado();
        if (usuario == null) return;

        modeloTabela.setRowCount(0);
        if (usuario instanceof Paciente) {
            listaAgendamentos = mainFrame
                .getSistema()
                .getAgendamentosPaciente((Paciente) usuario);
        } else if (usuario instanceof Medico) {
            listaAgendamentos = mainFrame
                .getSistema()
                .getAgendamentosMedico((Medico) usuario);
        }

        for (Agendamento ag : listaAgendamentos) {
            modeloTabela.addRow(new Object[] {
                ag.getMedico().getNome(),
                ag.getPaciente().getNome(),
                ag.getDataConsulta().toString(),
            });
        }
    }

    private void realizarAgendamento() {
        if (medicoSelecionado == null) {
            JOptionPane.showMessageDialog(
                this,
                "Selecione um médico primeiro (use a tela de Pesquisa).",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Object usuario = mainFrame.getUsuarioLogado();
        if (!(usuario instanceof Paciente)) {
            JOptionPane.showMessageDialog(
                this,
                "Apenas pacientes podem agendar consultas.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String dataStr = MainFrame.getTextoOuVazio(campoData, "2026-06-15");
        LocalDate data;
        try {
            data = LocalDate.parse(dataStr);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Data inválida! Use o formato AAAA-MM-DD.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (data.isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(
                this,
                "Não é possível agendar para uma data passada.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Paciente paciente = (Paciente) usuario;
        try {
            mainFrame
                .getSistema()
                .agendarConsulta(medicoSelecionado, paciente, data);
            JOptionPane.showMessageDialog(
                this,
                "Agendamento realizado com sucesso!",
                "Sucesso ✅",
                JOptionPane.INFORMATION_MESSAGE
            );
            atualizar();
        } catch (PlanoInvalidoException ex) {
            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Plano Inválido",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (AgendaLotadaException ex) {
            int opcao = JOptionPane.showConfirmDialog(
                this,
                ex.getMessage() + "\n\nDeseja entrar na lista de espera?",
                "Agenda Lotada",
                JOptionPane.YES_NO_OPTION
            );
            if (opcao == JOptionPane.YES_OPTION) {
                mainFrame
                    .getSistema()
                    .entrarListaEspera(medicoSelecionado, paciente, data);
                JOptionPane.showMessageDialog(
                    this,
                    "Você foi adicionado à lista de espera!",
                    "Lista de Espera",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Erro ao salvar agendamento no banco de dados: " +
                    ex.getMessage(),
                "Erro no Banco de Dados",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void cancelarAgendamento() {
        int linhaSelecionada = tabelaAgendamentos.getSelectedRow();
        if (linhaSelecionada < 0) {
            JOptionPane.showMessageDialog(
                this,
                "Selecione um agendamento para cancelar.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        int confirmacao = JOptionPane.showConfirmDialog(
            this,
            "Tem certeza que deseja cancelar este agendamento?",
            "Confirmar Cancelamento",
            JOptionPane.YES_NO_OPTION
        );
        if (confirmacao == JOptionPane.YES_OPTION) {
            Agendamento ag = listaAgendamentos.get(linhaSelecionada);
            try {
                mainFrame.getSistema().cancelarAgendamento(ag);
                JOptionPane.showMessageDialog(
                    this,
                    "Agendamento cancelado!",
                    "Cancelado",
                    JOptionPane.INFORMATION_MESSAGE
                );
                atualizar();
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Erro ao cancelar agendamento no banco de dados: " +
                        ex.getMessage(),
                    "Erro no Banco de Dados",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
