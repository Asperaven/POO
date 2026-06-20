package TFinalDados;

import TFinalClasses.Avaliacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
