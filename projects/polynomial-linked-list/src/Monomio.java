public class Monomio {
    private double coeficiente;
    private int expoente;

    public Monomio(double coeficiente, int expoente) {
        this.coeficiente = coeficiente;
        this.expoente = expoente;
    }

    public double getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(double coeficiente) {
        this.coeficiente = coeficiente;
    }

    public int getExpoente() {
        return expoente;
    }
}
