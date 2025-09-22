import java.util.List;

public class ListCoins {

    public static void execute(PiggyBank piggyBank, List<Fund> funds, int currentDay) {
        System.out.println("\n=== MOEDAS NO COFRINHO ===");
        piggyBank.listCoins(); // mostra moedas fora de fundos

        System.out.println("\n=== FUNDOS ===");
        for (Fund f : funds) {
            double disponivel = f.getAvailableBalance(currentDay);
            double bloqueado = f.getBlockedBalance(currentDay);
            double totalComJuros = f.getBalanceWithInterest(currentDay);

            System.out.println(f.getCode() + " - Tipo: " + f.getType() +
                    " | Saldo total (com rendimento): R$ " + String.format("%.2f", totalComJuros) +
                    " | Disponível: R$ " + String.format("%.2f", disponivel) +
                    " | Bloqueado: R$ " + String.format("%.2f", bloqueado));
        }
    }
}
