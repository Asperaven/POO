package TFinalDados;

import TFinalClasses.Medico;
import TFinalClasses.MedicoCardiologista;
import TFinalClasses.MedicoDermatologista;
import TFinalClasses.MedicoPediatra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicoDAO {

    public void cadastrarMedico(Medico medico) {
        String sql =
            "INSERT INTO medicos (nome, especialidade, valorConsulta, senha, tipo) VALUES (?, ?, ?, ?, ?)";

        try (
            Connection conexao = DBConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql)
        ) {
            statement.setString(1, medico.getNome());
            statement.setString(2, medico.getEspecialidade());
            statement.setDouble(3, medico.getValorConsulta());
            statement.setString(4, medico.getSenha());

            if (medico instanceof MedicoCardiologista) statement.setString(
                5,
                "Cardiologista"
            );
            else if (
                medico instanceof MedicoDermatologista
            ) statement.setString(5, "Dermatologista");
            else statement.setString(5, "Pediatra");

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(
                "Erro ao salvar médico: " + e.getMessage()
            );
        }
    }

    public ArrayList<Medico> buscarMedicos() {
        ArrayList<Medico> listaMedicos = new ArrayList<>();
        String sql = "SELECT * FROM medicos";

        try (
            Connection conexao = DBConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql);
            ResultSet resultset = statement.executeQuery()
        ) {
            while (resultset.next()) {
                String tipo = resultset.getString("tipo");
                Medico medico;
                if (tipo.equals("Cardiologista")) {
                    //nome, valor, senha
                    medico = new MedicoCardiologista(
                        resultset.getString("nome"),
                        resultset.getDouble("valorConsulta"),
                        resultset.getString("senha")
                    );
                } else if (tipo.equals("Dermatologista")) {
                    medico = new MedicoDermatologista(
                        resultset.getString("nome"),
                        resultset.getDouble("valorConsulta"),
                        resultset.getString("senha")
                    );
                } else {
                    medico = new MedicoPediatra(
                        resultset.getString("nome"),
                        resultset.getDouble("valorConsulta"),
                        resultset.getString("senha")
                    );
                }

                listaMedicos.add(medico);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaMedicos;
    }
}
