public class ListaPolinomio {
    private No inicio;

    public ListaPolinomio() {
        inicializar();
    }

    public void inicializar() {
        inicio = null;
    }

    public boolean estaVazia() {
        return inicio == null;
    }

    public void inserirNoFinal(double coeficiente, int expoente) {
        // Termos com coeficiente zero nao alteram o polinomio.
        if (coeficiente == 0) {
            return;
        }

        No novoNo = new No(new Monomio(coeficiente, expoente));

        if (estaVazia()) {
            inicio = novoNo;
            return;
        }

        No atual = inicio;
        while (atual.getProximo() != null) {
            atual = atual.getProximo();
        }

        atual.setProximo(novoNo);
    }

    public void inserirOrdenado(double coeficiente, int expoente) {
        if (coeficiente == 0) {
            return;
        }

        if (estaVazia() || expoente > inicio.getMonomio().getExpoente()) {
            No novoNo = new No(new Monomio(coeficiente, expoente));
            novoNo.setProximo(inicio);
            inicio = novoNo;
            return;
        }

        // Busca a posicao correta para manter a ordem decrescente dos expoentes.
        No atual = inicio;
        No anterior = null;

        while (atual != null && atual.getMonomio().getExpoente() > expoente) {
            anterior = atual;
            atual = atual.getProximo();
        }

        if (atual != null && atual.getMonomio().getExpoente() == expoente) {
            double novoCoeficiente = atual.getMonomio().getCoeficiente() + coeficiente;

            if (novoCoeficiente == 0) {
                removerMonomio(expoente);
            } else {
                atual.getMonomio().setCoeficiente(novoCoeficiente);
            }
            return;
        }

        No novoNo = new No(new Monomio(coeficiente, expoente));

        if (anterior == null) {
            // Caso especial de insercao no inicio.
            novoNo.setProximo(inicio);
            inicio = novoNo;
        } else {
            // Insere o novo no entre o anterior e o atual.
            novoNo.setProximo(atual);
            anterior.setProximo(novoNo);
        }
    }

    public boolean removerMonomio(int expoente) {
        if (estaVazia()) {
            return false;
        }

        if (inicio.getMonomio().getExpoente() == expoente) {
            inicio = inicio.getProximo();
            return true;
        }

        // Procura o termo a ser removido guardando o no anterior.
        No anterior = inicio;
        No atual = inicio.getProximo();

        while (atual != null) {
            if (atual.getMonomio().getExpoente() == expoente) {
                anterior.setProximo(atual.getProximo());
                return true;
            }
            anterior = atual;
            atual = atual.getProximo();
        }

        return false;
    }

    public boolean existeExpoente(int expoente) {
        return buscarMonomio(expoente) != null;
    }

    public Monomio buscarMonomio(int expoente) {
        // Percorre a lista procurando um termo com o expoente informado.
        No atual = inicio;

        while (atual != null) {
            if (atual.getMonomio().getExpoente() == expoente) {
                return atual.getMonomio();
            }
            atual = atual.getProximo();
        }

        return null;
    }

    public double avaliar(double x) {
        // Calcula o valor numerico do polinomio para um valor de X.
        double resultado = 0;
        No atual = inicio;

        while (atual != null) {
            Monomio monomio = atual.getMonomio();
            resultado += monomio.getCoeficiente() * Math.pow(x, monomio.getExpoente());
            atual = atual.getProximo();
        }

        return resultado;
    }

    public ListaPolinomio copiar() {
        // Cria uma nova lista com os mesmos termos da lista atual.
        ListaPolinomio copia = new ListaPolinomio();
        No atual = inicio;

        while (atual != null) {
            Monomio monomio = atual.getMonomio();
            copia.inserirNoFinal(monomio.getCoeficiente(), monomio.getExpoente());
            atual = atual.getProximo();
        }

        return copia;
    }

    public void adicionarTermosEm(ListaPolinomio destino) {
        adicionarTermosEm(destino, 1);
    }

    public void adicionarTermosEm(ListaPolinomio destino, double multiplicador) {
        // Adiciona os termos desta lista em outra,
        // podendo multiplicar os coeficientes por um valor.
        No atual = inicio;

        // Percorre todos os nós da lista de polinômios, multiplicando o coeficiente de cada monômio
        // por um valor escalar e inserindo o resultado, de forma ordenada, em um novo polinômio destino.
        while (atual != null) {
            Monomio monomio = atual.getMonomio();
            destino.inserirOrdenado(monomio.getCoeficiente() * multiplicador, monomio.getExpoente());
            atual = atual.getProximo();
        }
    }

    public void multiplicarCom(ListaPolinomio outro, ListaPolinomio resultado) {
        // Multiplica cada termo deste polinomio por cada termo do outro.
        No atualEste = inicio;

        while (atualEste != null) {
            Monomio monomioEste = atualEste.getMonomio();
            No atualOutro = outro.inicio;

            while (atualOutro != null) {
                Monomio monomioOutro = atualOutro.getMonomio();
                double coeficiente = monomioEste.getCoeficiente() * monomioOutro.getCoeficiente();
                int expoente = monomioEste.getExpoente() + monomioOutro.getExpoente();
                // O inserirOrdenado ja organiza e junta termos com mesmo expoente.
                resultado.inserirOrdenado(coeficiente, expoente);
                atualOutro = atualOutro.getProximo();
            }

            atualEste = atualEste.getProximo();
        }
    }

    @Override
    public String toString() {
        if (estaVazia()) {
            return "0";
        }

        // Monta a representacao textual do polinomio para exibicao.
        StringBuilder polinomio = new StringBuilder();
        No atual = inicio;
        boolean primeiroTermo = true;

        while (atual != null) {
            Monomio monomio = atual.getMonomio();
            double coeficiente = monomio.getCoeficiente();
            int expoente = monomio.getExpoente();

            if (coeficiente != 0) {
                if (!primeiroTermo) {
                    polinomio.append(coeficiente >= 0 ? " + " : " - ");
                } else if (coeficiente < 0) {
                    polinomio.append("-");
                }

                double coeficienteAbsoluto = Math.abs(coeficiente);
                // Nao mostra o coeficiente 1 quando houver parte literal, como em X^2.
                boolean mostrarCoeficiente = coeficienteAbsoluto != 1 || expoente == 0;

                if (mostrarCoeficiente) {
                    if (coeficienteAbsoluto == (long) coeficienteAbsoluto) {
                        polinomio.append((long) coeficienteAbsoluto);
                    } else {
                        polinomio.append(coeficienteAbsoluto);
                    }
                }

                if (expoente > 0) {
                    polinomio.append("X");

                    if (expoente > 1) {
                        // So mostra o expoente quando ele for maior que 1.
                        polinomio.append("^").append(expoente);
                    }
                }

                primeiroTermo = false;
            }

            atual = atual.getProximo();
        }

        return primeiroTermo ? "0" : polinomio.toString();
    }

    public void imprimir() {
        System.out.println(toString());
    }
}
