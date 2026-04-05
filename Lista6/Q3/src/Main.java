import java.util.ArrayList;
import L6Q3.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Sistema de gerenciamento de departamentos e colaboradores");
        Departamento depTecnologia = new Departamento("Tecnologia", new ArrayList<ColobaradorAssalariado>());
        Departamento depFashion = new Departamento("Fashion", new ArrayList<ColobaradorAssalariado>());
        Departamento depAlimentos = new Departamento("Alimentos", new ArrayList<ColobaradorAssalariado>());
        System.out.println("Departamentos existentes: " + depTecnologia.getNome() + ", " + depFashion.getNome() + ", " + depAlimentos.getNome());
        ColobaradorAssalariado colaborador1 = new ColobaradorAssalariado("001", "João", 160);
        ColobaradorAssalariado colaborador2 = new ColobaradorAssalariado("002", "Maria", 160);
        ColoboradorHorista colaborador3 = new ColoboradorHorista("003", "Carlos", 160, 15);
        ColoboradorComissionado colaborador4 = new ColoboradorComissionado("004", "Ana", 160, 10);
        depTecnologia.adicionarColaborador(colaborador1);
        depTecnologia.adicionarColaborador(colaborador3);
        depFashion.adicionarColaborador(colaborador2);
        depAlimentos.adicionarColaborador(colaborador4);
        depTecnologia.gerarFolhaSalarial();
        depFashion.gerarFolhaSalarial();
        depAlimentos.gerarFolhaSalarial();

    }
}
