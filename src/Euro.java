/**
 * Classe que representa a moeda Euro (€).
 * Herda da classe abstrata Coin e implementa a conversão para Reais
 * utilizando a cotação definida na constante COTACAO.
 */
public class Euro extends Coin {

    /** Cotação do Euro em relação ao Real. Ex.: 1 Euro = 6,25 Reais do dia 21-09-2025*/
    private static final double COTACAO = 6.25;

    /**
     * Construtor da classe Euro.
     * @param amount Valor da moeda em Euros.
     */
    public Euro(double amount) {
        super(amount);
    }

    /**
     * Converte o valor do Euro para Reais utilizando a cotação.
     * @return Valor da moeda em Reais.
     */
    @Override
    public double realConvert() {
        return amount * COTACAO;
    }

    /**
     * Retorna uma representação textual da moeda Euro.
     * Exemplo: "€ 10.0"
     * @return String representando a moeda.
     */
    @Override
    public String toString() {
        return "€ " + amount;
    }
}