public class OperacoesPolinomio {
    private OperacoesPolinomio() {
    }

    public static ListaPolinomio somar(ListaPolinomio p1, ListaPolinomio p2) {
        // Cria um novo polinomio com a soma dos termos de p1 e p2.
        ListaPolinomio resultado = new ListaPolinomio();
        p1.adicionarTermosEm(resultado);
        p2.adicionarTermosEm(resultado);

        return resultado;
    }

    public static ListaPolinomio subtrair(ListaPolinomio p1, ListaPolinomio p2) {
        // Adiciona os termos de p1 e subtrai os de p2 usando multiplicador -1.
        ListaPolinomio resultado = new ListaPolinomio();
        p1.adicionarTermosEm(resultado);
        p2.adicionarTermosEm(resultado, -1);

        return resultado;
    }

    public static ListaPolinomio multiplicarPorEscalar(ListaPolinomio polinomio, double escalar) {
        // Multiplica cada coeficiente do polinomio por um valor escalar.
        ListaPolinomio resultado = new ListaPolinomio();
        polinomio.adicionarTermosEm(resultado, escalar);
        return resultado;
    }

    public static ListaPolinomio multiplicarPolinomios(ListaPolinomio p1, ListaPolinomio p2) {
        // Multiplica cada termo de p1 por cada termo de p2.
        ListaPolinomio resultado = new ListaPolinomio();
        p1.multiplicarCom(p2, resultado);
        return resultado;
    }

    public static double valorNumerico(ListaPolinomio polinomio, double x) {
        // Calcula o valor numerico do polinomio para o X informado.
        return polinomio.avaliar(x);
    }
}
