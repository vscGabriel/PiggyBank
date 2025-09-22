import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Cofrinho (PiggyBank) que armazena múltiplas moedas.
 * Permite adicionar, remover, listar moedas e calcular o total em Reais,
 * incluindo valores aplicados em fundos com rendimento acumulado.
 */
public class PiggyBank {

    /** Lista que armazena todas as moedas do cofrinho (fora de fundos) */
    private List<Coin> coins;

    /** Construtor da classe PiggyBank. Inicializa a lista de moedas. */
    public PiggyBank() {
        coins = new ArrayList<>();
    }

    /** Adiciona uma moeda ao cofrinho */
    public void addCoin(Coin m) {
        coins.add(m);
        System.out.println("Moeda adicionada: " + m);
    }

    /** Remove uma moeda do cofrinho pelo índice */
    public void removeCoin(int index) {
        if (index >= 0 && index < coins.size()) {
            Coin removed = coins.remove(index);
            System.out.println("Moeda removida: " + removed);
        } else {
            System.out.println("Índice inválido!");
        }
    }

    /** Lista todas as moedas presentes no cofrinho */
    public void listCoins() {
        if (coins.isEmpty()) {
            System.out.println("Cofrinho vazio!");
            return;
        }
        System.out.println("Moedas no cofrinho:");
        for (int i = 0; i < coins.size(); i++) {
            Coin c = coins.get(i);
            System.out.println(i + ": " + c + (c.isAppliedToFund() ? " (Aplicada em fundo)" : ""));
        }
    }

    /**
     * Calcula e exibe o total do cofrinho em Reais.
     * Soma as moedas fora de fundos e os valores aplicados nos fundos,
     * considerando o rendimento acumulado até o dia atual.
     *
     * @param funds      Lista de fundos existentes
     * @param currentDay Dia atual do sistema
     */
    public void calculateTotal(List<Fund> funds, int currentDay) {
        double total = 0;

        for (Coin c : coins) {
            if (!c.isAppliedToFund()) {
                total += c.realConvert();
            }
        }

        for (Fund f : funds) {
            total += f.getBalanceWithInterest(currentDay);
        }

        System.out.println("Total em Reais (moedas + fundos): R$ " + String.format("%.2f", total));
    }

    /** Retorna a lista de moedas (para operações como RemoveCoin) */
    public List<Coin> getCoins() {
        return coins;
    }
}
