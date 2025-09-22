/**
 * Classe que representa um investimento em um fundo de aplicação.
 * Permite calcular o rendimento com base em uma taxa percentual.
 */
public class Investment {

    /** Valor inicial investido */
    private double initialAmount;

    /** Taxa de rendimento em percentual (ex.: 1.5 para 1,5%) */
    private double interestRate;

    /** Período de aplicação em meses */
    private int months;

    /**
     * Construtor do investimento.
     * @param initialAmount Valor inicial investido.
     * @param interestRate Taxa de rendimento em percentual.
     * @param months Período de aplicação em meses.
     */
    public Investment(double initialAmount, double interestRate, int months) {
        this.initialAmount = initialAmount;
        this.interestRate = interestRate;
        this.months = months;
    }

    /**
     * Calcula o valor final do investimento considerando juros compostos.
     * Fórmula: VF = VP * (1 + i)^n
     * @return Valor final do investimento
     */
    public double calculateFinalAmount() {
        return initialAmount * Math.pow(1 + (interestRate / 100), months);
    }

    /**
     * Exibe o resumo do investimento.
     */
    public void showSummary() {
        System.out.println("Investimento inicial: R$ " + initialAmount);
        System.out.println("Taxa de rendimento: " + interestRate + "% ao mês");
        System.out.println("Período: " + months + " meses");
        System.out.println("Valor final estimado: R$ " + calculateFinalAmount());
    }
}
