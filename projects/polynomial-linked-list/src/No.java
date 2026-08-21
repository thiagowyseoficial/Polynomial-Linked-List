public class No {
    private Monomio monomio;
    private No proximo;

    public No(Monomio monomio) {
        this.monomio = monomio;
    }

    public Monomio getMonomio() {
        return monomio;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
}
