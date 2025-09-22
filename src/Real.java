/**
 * Classe que representa a moeda Real.
 */
public class Real extends Coin {
    public Real(double valor) {
        super(valor);
    }

    @Override
    public double realConvert() {
        return amount; // já está em reais
    }

    @Override
    public String toString() {
        return "R$ " + amount;
    }
}
