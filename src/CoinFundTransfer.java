import java.util.List;
import java.util.Scanner;

public class CoinFundTransfer {

    public static void execute(List<Fund> funds, Scanner sc, int currentDay) {
        // Transferir fundos
        System.out.println("=== TRANSFERÊNCIA ENTRE FUNDOS ===");
        for (int i = 0; i < funds.size(); i++) {
            System.out.println((i + 1) + " - " + funds.get(i));
        }
        System.out.print("Escolha fundo de origem: ");
        int originIndex = sc.nextInt() - 1;
        System.out.print("Escolha fundo de destino: ");
        int destIndex = sc.nextInt() - 1;
        System.out.print("Informe valor a transferir: ");
        double valor = sc.nextDouble();

        Fund origin = funds.get(originIndex);
        Fund dest = funds.get(destIndex);

        if (valor <= origin.getAvailableBalance(currentDay)) {
            origin.withdraw(valor, currentDay);
            dest.addToFund(valor, currentDay);
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Saldo indisponível para transferência!");
        }
    }
}
