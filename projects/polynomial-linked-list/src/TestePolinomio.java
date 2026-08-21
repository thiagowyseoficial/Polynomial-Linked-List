public class TestePolinomio {
    public static void main(String[] args) {
        testarSoma();
        testarSubtracao();
        testarMultiplicacaoPorEscalar();
        testarMultiplicacaoDePolinomios();
        testarValorNumerico();
        testarRemocao();
        testarConsultaDeExpoente();
        testarUniaoDeExpoentesIguais();

        System.out.println();
        System.out.println("Todos os testes foram executados.");
    }

    private static void testarSoma() {
        ListaPolinomio a = criarPolinomio(new double[]{3, 2, 1}, new int[]{2, 1, 0});
        ListaPolinomio b = criarPolinomio(new double[]{5, -2, 4}, new int[]{2, 1, 0});
        ListaPolinomio resultado = OperacoesPolinomio.somar(a, b);

        verificar("Soma", "8X^2 + 5", resultado.toString());
    }

    private static void testarSubtracao() {
        ListaPolinomio a = criarPolinomio(new double[]{3, 2, 1}, new int[]{2, 1, 0});
        ListaPolinomio b = criarPolinomio(new double[]{5, -2, 4}, new int[]{2, 1, 0});
        ListaPolinomio resultado = OperacoesPolinomio.subtrair(a, b);

        verificar("Subtracao", "-2X^2 + 4X - 3", resultado.toString());
    }

    private static void testarMultiplicacaoPorEscalar() {
        ListaPolinomio a = criarPolinomio(new double[]{3, 2, 1}, new int[]{2, 1, 0});
        ListaPolinomio resultado = OperacoesPolinomio.multiplicarPorEscalar(a, 2);

        verificar("Multiplicacao por escalar", "6X^2 + 4X + 2", resultado.toString());
    }

    private static void testarMultiplicacaoDePolinomios() {
        ListaPolinomio a = criarPolinomio(new double[]{1, 1}, new int[]{1, 0});
        ListaPolinomio b = criarPolinomio(new double[]{1, 1}, new int[]{1, 0});
        ListaPolinomio resultado = OperacoesPolinomio.multiplicarPolinomios(a, b);

        verificar("Multiplicacao entre polinomios", "X^2 + 2X + 1", resultado.toString());
    }

    private static void testarValorNumerico() {
        ListaPolinomio a = criarPolinomio(new double[]{3, 2, 1}, new int[]{2, 1, 0});
        double resultado = OperacoesPolinomio.valorNumerico(a, 2);

        verificar("Valor numerico", 17.0, resultado);
    }

    private static void testarRemocao() {
        ListaPolinomio a = criarPolinomio(new double[]{3, 2, 1}, new int[]{2, 1, 0});
        boolean removido = a.removerMonomio(1);

        verificar("Remocao", true, removido);
        verificar("Polinomio apos remocao", "3X^2 + 1", a.toString());
    }

    private static void testarConsultaDeExpoente() {
        ListaPolinomio a = criarPolinomio(new double[]{3, 2, 1}, new int[]{2, 1, 0});

        verificar("Consulta de expoente existente", true, a.existeExpoente(2));
        verificar("Consulta de expoente inexistente", false, a.existeExpoente(5));
    }

    private static void testarUniaoDeExpoentesIguais() {
        ListaPolinomio a = new ListaPolinomio();
        a.inserirOrdenado(2, 2);
        a.inserirOrdenado(3, 2);
        verificar("Uniao de termos com mesmo expoente", "5X^2", a.toString());

        a.inserirOrdenado(-5, 2);
        verificar("Cancelamento de termos", "0", a.toString());
    }

    private static ListaPolinomio criarPolinomio(double[] coeficientes, int[] expoentes) {
        ListaPolinomio polinomio = new ListaPolinomio();

        for (int i = 0; i < coeficientes.length; i++) {
            polinomio.inserirOrdenado(coeficientes[i], expoentes[i]);
        }

        return polinomio;
    }

    private static void verificar(String nomeTeste, String esperado, String obtido) {
        if (esperado.equals(obtido)) {
            System.out.println("[OK] " + nomeTeste + " -> " + obtido);
        } else {
            System.out.println("[ERRO] " + nomeTeste);
            System.out.println("Esperado: " + esperado);
            System.out.println("Obtido:   " + obtido);
        }
    }

    private static void verificar(String nomeTeste, boolean esperado, boolean obtido) {
        if (esperado == obtido) {
            System.out.println("[OK] " + nomeTeste + " -> " + obtido);
        } else {
            System.out.println("[ERRO] " + nomeTeste);
            System.out.println("Esperado: " + esperado);
            System.out.println("Obtido:   " + obtido);
        }
    }

    private static void verificar(String nomeTeste, double esperado, double obtido) {
        if (Math.abs(esperado - obtido) < 0.0001) {
            System.out.println("[OK] " + nomeTeste + " -> " + obtido);
        } else {
            System.out.println("[ERRO] " + nomeTeste);
            System.out.println("Esperado: " + esperado);
            System.out.println("Obtido:   " + obtido);
        }
    }
}
