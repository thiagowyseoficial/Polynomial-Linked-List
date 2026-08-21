import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        ListaPolinomio polinomioA = new ListaPolinomio();
        ListaPolinomio polinomioB = new ListaPolinomio();

        boolean continuar = true;

        while (continuar) {
            exibirMenu();
            int opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    polinomioA = lerPolinomio("A");
                    break;
                case 2:
                    polinomioB = lerPolinomio("B");
                    break;
                case 3:
                    exibirPolinomios(polinomioA, polinomioB);
                    break;
                case 4:
                    exibirResultado("Soma", OperacoesPolinomio.somar(polinomioA, polinomioB));
                    break;
                case 5:
                    exibirResultado("Subtracao (A - B)", OperacoesPolinomio.subtrair(polinomioA, polinomioB));
                    break;
                case 6:
                    multiplicarPorEscalar(polinomioA, polinomioB);
                    break;
                case 7:
                    exibirResultado("Multiplicacao (A * B)", OperacoesPolinomio.multiplicarPolinomios(polinomioA, polinomioB));
                    break;
                case 8:
                    avaliarPolinomio(polinomioA, polinomioB);
                    break;
                case 9:
                    removerMonomio(polinomioA, polinomioB);
                    break;
                case 10:
                    consultarExpoente(polinomioA, polinomioB);
                    break;
                case 0:
                    continuar = false;
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }

            System.out.println();
        }
    }

    private static void exibirMenu() {
        System.out.println("========================================");
        System.out.println("     MANIPULACAO DE POLINOMIOS");
        System.out.println("========================================");
        System.out.println("1. Informar polinomio A");
        System.out.println("2. Informar polinomio B");
        System.out.println("3. Exibir polinomios");
        System.out.println("4. Somar A + B");
        System.out.println("5. Subtrair A - B");
        System.out.println("6. Multiplicar polinomio por escalar");
        System.out.println("7. Multiplicar A * B");
        System.out.println("8. Calcular valor numerico");
        System.out.println("9. Remover monomio por expoente");
        System.out.println("10. Consultar expoente");
        System.out.println("0. Sair");
        System.out.println("========================================");
    }

    private static ListaPolinomio lerPolinomio(String nome) {
        ListaPolinomio polinomio = new ListaPolinomio();
        int quantidade = lerInteiroNaoNegativo("Quantos monomios ha no polinomio " + nome + "? ");

        for (int i = 1; i <= quantidade; i++) {
            System.out.println("Monomio " + i + ":");
            double coeficiente = lerDouble("Coeficiente: ");
            int expoente = lerInteiroNaoNegativo("Expoente: ");
            polinomio.inserirOrdenado(coeficiente, expoente);
        }

        System.out.println("Polinomio " + nome + " armazenado: " + polinomio);
        return polinomio;
    }

    private static void exibirPolinomios(ListaPolinomio polinomioA, ListaPolinomio polinomioB) {
        System.out.println("Polinomio A: " + polinomioA);
        System.out.println("Polinomio B: " + polinomioB);
    }

    private static void multiplicarPorEscalar(ListaPolinomio polinomioA, ListaPolinomio polinomioB) {
        ListaPolinomio polinomioEscolhido = escolherPolinomio(polinomioA, polinomioB);
        double escalar = lerDouble("Informe o valor escalar: ");
        ListaPolinomio resultado = OperacoesPolinomio.multiplicarPorEscalar(polinomioEscolhido, escalar);
        exibirResultado("Multiplicacao por escalar", resultado);
    }

    private static void avaliarPolinomio(ListaPolinomio polinomioA, ListaPolinomio polinomioB) {
        ListaPolinomio polinomioEscolhido = escolherPolinomio(polinomioA, polinomioB);
        double x = lerDouble("Informe o valor de X: ");
        double resultado = OperacoesPolinomio.valorNumerico(polinomioEscolhido, x);
        System.out.println("Valor numerico: " + resultado);
    }

    private static void removerMonomio(ListaPolinomio polinomioA, ListaPolinomio polinomioB) {
        ListaPolinomio polinomioEscolhido = escolherPolinomio(polinomioA, polinomioB);
        int expoente = lerInteiro("Informe o expoente do monomio a remover: ");
        boolean removido = polinomioEscolhido.removerMonomio(expoente);

        if (removido) {
            System.out.println("Monomio removido com sucesso.");
            System.out.println("Polinomio atualizado: " + polinomioEscolhido);
        } else {
            System.out.println("Nao existe monomio com esse expoente.");
        }
    }

    private static void consultarExpoente(ListaPolinomio polinomioA, ListaPolinomio polinomioB) {
        ListaPolinomio polinomioEscolhido = escolherPolinomio(polinomioA, polinomioB);
        int expoente = lerInteiro("Informe o expoente a consultar: ");
        Monomio monomio = polinomioEscolhido.buscarMonomio(expoente);

        if (monomio != null) {
            System.out.println("Termo encontrado: coeficiente = " + monomio.getCoeficiente() + ", expoente = " + monomio.getExpoente());
        } else {
            System.out.println("O termo nao existe no polinomio.");
        }
    }

    private static ListaPolinomio escolherPolinomio(ListaPolinomio polinomioA, ListaPolinomio polinomioB) {
        while (true) {
            String escolha = lerTexto("Escolha o polinomio (A ou B): ").trim().toUpperCase();

            if (escolha.equals("A")) {
                return polinomioA;
            }

            if (escolha.equals("B")) {
                return polinomioB;
            }

            System.out.println("Escolha invalida. Digite A ou B.");
        }
    }

    private static void exibirResultado(String titulo, ListaPolinomio resultado) {
        System.out.println(titulo + ": " + resultado);
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = SCANNER.nextInt();
                SCANNER.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida. Digite um numero inteiro.");
                SCANNER.nextLine();
            }
        }
    }

    private static int lerInteiroNaoNegativo(String mensagem) {
        while (true) {
            int valor = lerInteiro(mensagem);

            if (valor >= 0) {
                return valor;
            }

            System.out.println("Entrada invalida. Digite um numero inteiro maior ou igual a zero.");
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                double valor = SCANNER.nextDouble();
                SCANNER.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida. Digite um numero real.");
                SCANNER.nextLine();
            }
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return SCANNER.nextLine();
    }
}
