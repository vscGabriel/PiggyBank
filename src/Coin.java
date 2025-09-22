/**
 * Classe abstrata que representa uma moeda.
 * Contém valor da moeda e se foi aplicada em algum fundo.
 */
public abstract class Coin {
    protected double amount; // valor da moeda na sua unidade
    protected boolean appliedToFund; // true se o valor foi aplicado em fundo

    public Coin(double amount) {
        this.amount = amount;
        this.appliedToFund = false; // por padrão, não aplicada
    }

    public double getAmount() {
        return amount;
    }

    public boolean isAppliedToFund() {
        return appliedToFund;
    }

    public void setAppliedToFund(boolean appliedToFund) {
        this.appliedToFund = appliedToFund;
    }

    /**
     * Converte o valor da moeda para Reais.
     * @return valor convertido em Reais
     */
    public abstract double realConvert();

    @Override
    public abstract String toString();
}
