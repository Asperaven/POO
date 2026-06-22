package TFinalDados;

import TFinalClasses.Paciente;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PacienteDAO {
    public void cadastrarPaciente(Paciente paciente){
        String sql = "INSERT INTO pacientes (nome, idade, planoSaude, senha) VALUES (?, ?, ?, ?)";

        try(Connection conexao = DBConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, paciente.getNome());
            statement.setInt(2, paciente.getIdade());
            statement.setString(3, paciente.getPlanoSaude());
            statement.setString(4, paciente.getSenha());
            statement.executeUpdate();

        } catch(SQLException e){
            throw new RuntimeException("Erro ao salvar paciente: " + e.getMessage());
        }
    }

    public ArrayList<Paciente> buscarPacientes(){
        ArrayList<Paciente> listaPacientes = new ArrayList<>();
        String sql  = "SELECT * FROM pacientes";

        try(Connection conexao = DBConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql);
            ResultSet rs = statement.executeQuery()) {
            while(rs.next()){
                //nome, idade, planoSaude, senha, creditos
                Paciente paciente = new Paciente(rs.getString("nome"), rs.getInt("idade"),
                        rs.getString("planoSaude"), rs.getString("senha"));
                paciente.setCreditos(rs.getDouble("creditos"));
                listaPacientes.add(paciente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPacientes;
    }

    public void atualizarSenha(String nome, String senha) {
        String sql = "UPDATE pacientes SET senha = ? WHERE nome = ?";
        try (Connection conexao = DBConnection.getConnection();
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, senha);
            statement.setString(2, nome);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar senha: " + e.getMessage());
        }
    }

    public void atualizarPlano(String nome, String plano) {
        String sql = "UPDATE pacientes SET planoSaude = ? WHERE nome = ?";
        try (Connection conexao = DBConnection.getConnection();
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, plano);
            statement.setString(2, nome);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar plano: " + e.getMessage());
        }
    }

    public void atualizarCreditos(String nome, double creditos) {
        String sql = "UPDATE pacientes SET creditos = ? WHERE nome = ?";
        try (Connection conexao = DBConnection.getConnection();
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setDouble(1, creditos);
            statement.setString(2, nome);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar créditos: " + e.getMessage());
        }
    }

    public void deletarPaciente(String nome) {
        String sql = "DELETE FROM pacientes WHERE nome = ?";
        try (Connection conexao = DBConnection.getConnection();
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar paciente: " + e.getMessage());
        }
    }
}
