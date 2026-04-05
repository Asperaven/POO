package L4Q2;

public class Quarto {
    private double quantidadeDeDias;
    private boolean vago;

    public Quarto(double quantidadeDeDias, boolean vago) {
        this.quantidadeDeDias = quantidadeDeDias;
        this.vago = vago;
    }

    public double getQuantidadeDeDias() {
        return quantidadeDeDias;
    }

    public void setQuantidadeDeDias(double quantidadeDeDias) {
        this.quantidadeDeDias = quantidadeDeDias;
    }

    public boolean isVago() {
        return vago;
    }

    public void setVago(boolean vago) {
        this.vago = vago;
    }

    public void ocuparQuarto(double dias) {
        if (vago) {
            this.quantidadeDeDias = dias;
            this.vago = false;
        } else {
            System.out.println("Erro: O quarto já está ocupado!");
        }
    }

    public void descuparQuarto() {
        if (!vago) {
            this.quantidadeDeDias = 0;
            this.vago = true;
        } else {
            System.out.println("Erro: O quarto já está vago!");
        }
    }

}
