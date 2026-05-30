package TFinalClasses;

public class Conta {
    private Paciente paciente;
    private double valorConsulta;
    private boolean situacaoPagamento; // true para pago, false para pendente

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    public boolean isSituacaoPagamento() {
        return situacaoPagamento;
    }

    public void setSituacaoPagamento(boolean situacaoPagamento) {
        this.situacaoPagamento = situacaoPagamento;
    }

    public Conta(Paciente paciente, double valorConsulta, boolean situacaoPagamento) {
        this.paciente = paciente;
        this.valorConsulta = valorConsulta;
        this.situacaoPagamento = situacaoPagamento;
    }

}
