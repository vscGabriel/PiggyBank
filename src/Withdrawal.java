import java.util.List;
import java.util.Scanner;

/**
 * Classe que encapsula a funcionalidade de resgate de fundos.
 * Permite retirar apenas valores disponíveis (não bloqueados).
 */
public class Withdrawal {

    /**
     * Executa o resgate de fundos disponíveis.
     *
     * @param sc         Scanner para leitura de entrada
     * @param funds      Lista de fundos existentes
     * @param currentDay Dia atual do sistema
     */
    public static void execute(Scanner sc, List<Fund> funds, int currentDay) {
        System.out.println("=== RESGATE DE FUNDOS DISPONÍVEIS ===");
        for (Fund f : funds) {
            System.out.println(f.getCode() + " - Disponível: R$ " + f.getAvailableBalance(currentDay) +
                    " | Bloqueado: R$ " + f.getBlockedBalance(currentDay));
        }

        boolean resgatar = true;
        while (resgatar) {
            System.out.println("Deseja resgatar algum fundo?");
            System.out.println("1 - Total disponível");
            System.out.println("2 - Parcial");
            System.out.println("3 - Cancelar");
            int choice = sc.nextInt();

            if (choice == 3) {
                resgatar = false;
                continue;
            }

            System.out.print("Escolha o fundo: ");
            int fIndex = sc.nextInt() - 1;
            if (fIndex < 0 || fIndex >= funds.size()) {
                System.out.println("Fundo inválido!");
                continue;
            }
            Fund f = funds.get(fIndex);

            switch (choice) {
                case 1 -> {
                    double valor = f.getAvailableBalance(currentDay);
                    f.withdraw(valor, currentDay);
                    System.out.println("Resgatado R$ " + valor + " do fundo " + f.getCode());
                    resgatar = false;
                }
                case 2 -> {
                    boolean valorValido = false;
                    while (!valorValido) {
                        double disponivel = f.getAvailableBalance(currentDay);
                        System.out.print("Informe o valor a resgatar (disponível: R$ " + disponivel + "): ");
                        double valor = sc.nextDouble();
                        if (valor <= disponivel) {
                            f.withdraw(valor, currentDay);
                            System.out.println("Resgatado R$ " + valor + " do fundo " + f.getCode());
                            valorValido = true;
                            resgatar = false;
                        } else {
                            System.out.println("Valor maior do que o disponível. Tente novamente.");
                        }
                    }
                }
            }
        }
    }
}
