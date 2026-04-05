package L5Q2;

public class Medico extends Funcionario {

    private String cargo;
    private boolean isPediatra;
    private boolean isCirurgiao;
    private Medico assistenteEstagiario;
    private Enfermeiro assistenteEnfermeiro;

    public Medico(String nome, String cargo, boolean isPediatra, boolean isCirurgiao) {
        super(nome, 1200, "INTEGRAL");
        this.cargo = cargo;
        this.isPediatra = isPediatra;
        this.isCirurgiao = isCirurgiao;
        this.assistenteEstagiario = null;
        this.assistenteEnfermeiro = null;
    }

    public boolean atribuirAssistenteEstagiario(Medico medico) {
        if ("ESTAGIARIO".equals(this.cargo)) {
            System.out.println("Erro: Medico estagiario nao pode ter assistente!");
            return false;
        }
        
        if (medico == null || !"ESTAGIARIO".equals(medico.cargo)) {
            System.out.println("Erro: O assistente deve ser um medico estagiario!");
            return false;
        }
        
        this.assistenteEstagiario = medico;
        System.out.println("Assistente estagiario atribuido com sucesso!");
        return true;
    }

    public boolean atribuirAssistenteEnfermeiro(Enfermeiro enfermeiro) {
        if ("ESTAGIARIO".equals(this.cargo)) {
            System.out.println("Erro: Medico estagiario nao pode ter assistente!");
            return false;
        }
        
        if (enfermeiro == null) {
            System.out.println("Erro: Enfermeiro invalido!");
            return false;
        }
        
        this.assistenteEnfermeiro = enfermeiro;
        System.out.println("Assistente enfermeiro atribuido com sucesso!");
        return true;
    }

    public Medico getAssistenteEstagiario() {
        return assistenteEstagiario;
    }

    public Enfermeiro getAssistenteEnfermeiro() {
        return assistenteEnfermeiro;
    }

    public void contratarFuncionario(Funcionario funcionario) {
        if ("DIRETOR".equals(this.cargo)) {
            System.out.println("Funcionario contratado com sucesso!");
        } else {
            System.out.println("Erro: Apenas medicos com cargo de Diretor podem contratar funcionarios!");
        }
    }

    public void demitirFuncionario(Funcionario funcionario) {
        if ("DIRETOR".equals(this.cargo)) {
            System.out.println("Funcionario demitido com sucesso!");
        } else {
            System.out.println("Erro: Apenas medicos com cargo de Diretor podem demitir funcionarios!");
        }
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean isPediatra() {
        return isPediatra;
    }

    public void setPediatra(boolean isPediatra) {
        this.isPediatra = isPediatra;
    }

    public boolean isCirurgiao() {
        return isCirurgiao;
    }

    public void setCirurgiao(boolean isCirurgiao) {
        this.isCirurgiao = isCirurgiao;
    }

    public void setAssistenteEstagiario(Medico assistenteEstagiario) {
        this.assistenteEstagiario = assistenteEstagiario;
    }

    public void setAssistenteEnfermeiro(Enfermeiro assistenteEnfermeiro) {
        this.assistenteEnfermeiro = assistenteEnfermeiro;
    }

    @Override
    public double exibirSalario() {
        double salario = 1200;
        
        if ("ESTAGIARIO".equals(cargo)) {
            salario = 1200;
        } else if ("FORMADO".equals(cargo)) {
            salario = 5000;
        } else if ("DIRETOR".equals(cargo)) {
            salario = 10000;
        }

        if (isPediatra) {
            salario += 1000;
        }

        if (isCirurgiao) {
            salario += 1500;
        }

        return salario;
    }
}
