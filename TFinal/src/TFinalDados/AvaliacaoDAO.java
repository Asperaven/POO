package TFinalDados;

import TFinalClasses.Avaliacao;
import TFinalClasses.Medico;
import TFinalClasses.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AvaliacaoDAO {
    public void cadastrarAvaliacao(Avaliacao avaliacao){
        String sql = "INSERT INTO avaliacoes (medico, paciente, descricao, avaliacao) VALUES (?, ?, ?, ?)";

        try(Connection conexao = DBConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql);
        ) {
            statement.setString(1, avaliacao.getMedico().getNome());
            statement.setString(2, avaliacao.getPaciente().getNome());
            statement.setString(3, avaliacao.getDescricao());
            statement.setInt(4, avaliacao.getEstrelas());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar avaliação: " + e.getMessage());
        }
    }

    public ArrayList<Avaliacao> buscarAvaliacoes(ArrayList<Paciente> pacientes, ArrayList<Medico> medicos){
        ArrayList<Avaliacao> listaAvaliacoes = new ArrayList<>();
        String sql = "SELECT * FROM avaliacoes";

        try(Connection conexao = DBConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql);
            ResultSet rs = statement.executeQuery()) {
            while(rs.next()){
                String nomeMedico = rs.getString("medico");
                String nomePaciente = rs.getString("paciente");

                Paciente pacienteEncontrado = null;
                for(Paciente p : pacientes){
                    if(p.getNome().equalsIgnoreCase(nomePaciente)){
                        pacienteEncontrado = p;
                        break;
                    }
                }

                Medico medicoEncontrado = null;
                for(Medico m : medicos){
                    if(m.getNome().equalsIgnoreCase(nomeMedico)){
                        medicoEncontrado = m;
                        break;
                    }
                }

                if (medicoEncontrado != null && pacienteEncontrado != null) {
                    Avaliacao avaliacao = new Avaliacao(medicoEncontrado, pacienteEncontrado, rs.getString("descricao"),
                            rs.getInt("avaliacao"));
                    listaAvaliacoes.add(avaliacao);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAvaliacoes;
    }
}
