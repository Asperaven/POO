package TFinalDados;
import TFinalClasses.Consulta;
import TFinalClasses.Consulta;
import TFinalClasses.Medico;
import TFinalClasses.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ConsultaDAO {
    public void cadastrarConsulta(Consulta consulta){
        String sql = "INSERT INTO consultas (medico, paciente, data, descricao, receita, exames, valor) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(Connection conexao = DBConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql);
        ){
            statement.setString(1, consulta.getMedico().getNome());
            statement.setString(2, consulta.getPaciente().getNome());
            statement.setString(3, consulta.getData());
            statement.setString(4, consulta.getDescricao());
            statement.setString(5, consulta.getReceita());
            statement.setString(6, consulta.getExames());
            statement.setDouble(7, consulta.getValorPago());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar consulta: " + e.getMessage());
        }
    }

    public ArrayList<Consulta> buscarConsultas(ArrayList<Paciente> pacientes, ArrayList<Medico> medicos){
        ArrayList<Consulta> listaConsultas = new ArrayList<>();
        String sql  = "SELECT * FROM consultas";

        try(Connection conexao = DBConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql);
            ResultSet rs = statement.executeQuery()) {
            while(rs.next()){
                //medico, paciente, data, descricao, receita, exames, valor

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

                Consulta consulta = new Consulta(medicoEncontrado, pacienteEncontrado, rs.getString("data"),
                        rs.getString("descricao"), rs.getString("receita"), rs.getString("exames"),
                        rs.getDouble("valor"));
                listaConsultas.add(consulta);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }
}
