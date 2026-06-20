package TFinalGUI;

import TFinalClasses.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ConsultaPanel extends JPanel {
    private MainFrame mainFrame;
    private JComboBox<String> comboPacientes;
    private JTextArea areaDescricao;
    private JTextField campoReceita;
    private JTextField campoExames;
    private JLabel labelValor;
    private ArrayList<Agendamento> agendamentosHoje;

    public ConsultaPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.agendamentosHoje = new ArrayList<>();
        setBackground(MainFrame.COR_FUNDO);
        setLayout(new BorderLayout());
        criarInterface();
    }

    private void criarInterface() {
        JPanel header = PesquisaMedicoPanel.criarHeaderComVoltar("🩺 Realizar Consulta", mainFrame);
        add(header, BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setBackground(MainFrame.COR_FUNDO);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JPanel card = MainFrame.criarPainelCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setMaximumSize(new Dimension(800, 600));

        // Seleção de paciente
        JPanel linhaPaciente = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        linhaPaciente.setBackground(MainFrame.COR_PAINEL);
        linhaPaciente.add(MainFrame.criarLabel("Paciente:", 14, true));
        comboPacientes = new JComboBox<>();
        comboPacientes.setBackground(MainFrame.COR_CARD);
        comboPacientes.setForeground(MainFrame.COR_TEXTO);
        comboPacientes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboPacientes.setPreferredSize(new Dimension(300, 38));
        comboPacientes.addActionListener(e -> atualizarValor());
        linhaPaciente.add(comboPacientes);
        linhaPaciente.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(linhaPaciente);
        card.add(Box.createVerticalStrut(12));

        // Descrição (sintomas, diagnóstico, tratamento)
        JLabel labelDescricao = MainFrame.criarLabel("Descrição (sintomas, diagnóstico, tratamento):", 14, false);
        labelDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(labelDescricao);
        card.add(Box.createVerticalStrut(4));

        areaDescricao = MainFrame.criarAreaTexto();
        areaDescricao.setRows(6);
        JScrollPane scrollDesc = new JScrollPane(areaDescricao);
        scrollDesc.setMaximumSize(new Dimension(720, 150));
        scrollDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollDesc.setBorder(BorderFactory.createLineBorder(MainFrame.COR_BORDA));
        card.add(scrollDesc);
        card.add(Box.createVerticalStrut(12));

        // Receita
        JLabel labelReceita = MainFrame.criarLabel("Receita médica:", 14, false);
        labelReceita.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(labelReceita);
        card.add(Box.createVerticalStrut(4));
        campoReceita = MainFrame.criarCampoTexto("Medicamentos prescritos...");
        campoReceita.setMaximumSize(new Dimension(720, 42));
        campoReceita.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(campoReceita);
        card.add(Box.createVerticalStrut(12));

        // Exames
        JLabel labelExames = MainFrame.criarLabel("Exames solicitados:", 14, false);
        labelExames.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(labelExames);
        card.add(Box.createVerticalStrut(4));
        campoExames = MainFrame.criarCampoTexto("Exames solicitados...");
        campoExames.setMaximumSize(new Dimension(720, 42));
        campoExames.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(campoExames);
        card.add(Box.createVerticalStrut(16));

        // Valor
        JPanel linhaValor = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        linhaValor.setBackground(MainFrame.COR_PAINEL);
        linhaValor.add(MainFrame.criarLabel("Valor:", 14, true));
        labelValor = MainFrame.criarLabel("R$ 0,00", 16, true);
        labelValor.setForeground(MainFrame.COR_SUCESSO);
        linhaValor.add(labelValor);
        linhaValor.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(linhaValor);
        card.add(Box.createVerticalStrut(20));

        // Botão registrar
        JButton botaoRegistrar = MainFrame.criarBotao("✅ Registrar Consulta", MainFrame.COR_SUCESSO);
        botaoRegistrar.setAlignmentX(Component.LEFT_ALIGNMENT);
        botaoRegistrar.addActionListener(e -> registrarConsulta());
        card.add(botaoRegistrar);

        centro.add(card);
        JScrollPane scrollCentro = new JScrollPane(centro);
        scrollCentro.setBackground(MainFrame.COR_FUNDO);
        scrollCentro.getViewport().setBackground(MainFrame.COR_FUNDO);
        scrollCentro.setBorder(null);
        add(scrollCentro, BorderLayout.CENTER);
    }

    public void atualizar() {
        Object usuario = mainFrame.getUsuarioLogado();
        comboPacientes.removeAllItems();
        agendamentosHoje.clear();

        if (usuario instanceof Medico) {
            Medico medico = (Medico) usuario;
            agendamentosHoje = mainFrame.getSistema().getAgendamentosHojeMedico(medico);

            if (agendamentosHoje.isEmpty()) {
                comboPacientes.addItem("Nenhum agendamento para hoje");
            } else {
                for (Agendamento ag : agendamentosHoje) {
                    comboPacientes.addItem(ag.getPaciente().getNome()
                            + " — " + ag.getPaciente().getPlanoSaude());
                }
            }
            atualizarValor();
        }
    }

    private void atualizarValor() {
        Object usuario = mainFrame.getUsuarioLogado();
        if (!(usuario instanceof Medico))
            return;
        Medico medico = (Medico) usuario;

        int idx = comboPacientes.getSelectedIndex();
        if (idx >= 0 && idx < agendamentosHoje.size()) {
            Paciente paciente = agendamentosHoje.get(idx).getPaciente();
            if (paciente.getPlanoSaude() != null && !paciente.getPlanoSaude().isEmpty()) {
                labelValor.setText("R$ 0,00 (coberto pelo plano)");
                labelValor.setForeground(MainFrame.COR_SUCESSO);
            } else {
                labelValor.setText("R$ " + String.format("%.2f", medico.getValorConsulta()));
                labelValor.setForeground(new Color(250, 204, 21));
            }
        }
    }

    private void registrarConsulta() {
        Object usuario = mainFrame.getUsuarioLogado();
        if (!(usuario instanceof Medico)) {
            JOptionPane.showMessageDialog(this,
                    "Apenas médicos podem registrar consultas.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idx = comboPacientes.getSelectedIndex();
        if (idx < 0 || idx >= agendamentosHoje.size()) {
            JOptionPane.showMessageDialog(this,
                    "Nenhum paciente selecionado ou sem agendamentos para hoje.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String descricao = areaDescricao.getText().trim();
        if (descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "A descrição da consulta é obrigatória.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Medico medico = (Medico) usuario;
        Paciente paciente = agendamentosHoje.get(idx).getPaciente();
        String receita = MainFrame.getTextoOuVazio(campoReceita, "Medicamentos prescritos...");
        String exames = MainFrame.getTextoOuVazio(campoExames, "Exames solicitados...");
        double valor = medico.getValorConsulta();

        try {
            mainFrame.getSistema().realizarConsulta(medico, paciente, descricao, receita, exames, valor);

            JOptionPane.showMessageDialog(this,
                    "Consulta registrada com sucesso!",
                    "Sucesso ✅", JOptionPane.INFORMATION_MESSAGE);

            // Limpar campos
            areaDescricao.setText("");
            campoReceita.setText("Medicamentos prescritos...");
            campoReceita.setForeground(MainFrame.COR_TEXTO_SECUNDARIO);
            campoExames.setText("Exames solicitados...");
            campoExames.setForeground(MainFrame.COR_TEXTO_SECUNDARIO);
            atualizar();
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao registrar consulta no banco de dados: " + ex.getMessage(),
                    "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }
}
