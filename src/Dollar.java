/**
 * Classe que representa a moeda Dólar ($).
 * Herda da classe abstrata Coin e implementa a conversão para Reais
 * utilizando a cotação definida na constante COTACAO.
 */
public class Dollar extends Coin {

    /** Cotação do Dólar em relação ao Real. Ex.: 1 Dólar = 5,33 Reais 21-09-2025*/
    private static final double COTACAO = 5.33;

    /**
     * Construtor da classe Dollar.
     * @param amount amount da moeda em Dólares.
     */
    public Dollar(double amount) {
        super(amount);
    }

    /**
     * Converte o amount do Dólar para Reais utilizando a cotação.
     * @return amount da moeda em Reais.
     */
    @Override
    public double realConvert() {
        return amount * COTACAO;
    }

    /**
     * Retorna uma representação textual da moeda Dólar.
     * Exemplo: "$ 10.0"
     * @return String representando a moeda.
     */
    @Override
    public String toString() {
        return "$ " + amount;
    }
}
