import java.util.List;
import java.util.Scanner;

/**
 * Classe que encapsula a funcionalidade de adicionar moedas ao cofrinho,
 * com opção de aplicar em fundos e simular rendimento.
 */
public class AddCoin {

    /**
     * Método para adicionar uma moeda e, opcionalmente, aplicar em um fundo.
     *
     * @param piggyBank  Cofrinho do usuário
     * @param funds      Lista de fundos disponíveis
     * @param sc         Scanner para leitura de entrada
     * @param currentDay Dia atual do sistema
     */
    public static void execute(PiggyBank piggyBank, List<Fund> funds, Scanner sc, int currentDay) {
        Coin coin = null;
        double valor = 0;

        // Loop até o usuário informar um tipo de moeda válido
        while (coin == null) {
            System.out.println("Escolha o tipo de moeda:");
            System.out.println("1 - Real");
            System.out.println("2 - Dólar");
            System.out.println("3 - Euro");
            int tipo = sc.nextInt();

            if (tipo < 1 || tipo > 3) {
                System.out.println("Tipo de moeda não aceita! Tente novamente.");
                continue;
            }

            System.out.print("Informe o valor: ");
            valor = sc.nextDouble();

            switch (tipo) {
                case 1 -> coin = new Real(valor);
                case 2 -> coin = new Dollar(valor);
                case 3 -> coin = new Euro(valor);
            }
        }

        // Escolher se quer aplicar em fundo
        System.out.println("Deseja aplicar em um fundo?");
        System.out.println("1 - Simular aplicação");
        System.out.println("2 - Guardar sem fundo");
        int choice = sc.nextInt();

        if (choice == 1 && !funds.isEmpty()) {
            boolean simular = true;
            while (simular) {
                System.out.println("\n=== FUNDS DISPONÍVEIS PARA SIMULAÇÃO ===");
                for (int i = 0; i < funds.size(); i++) {
                    Fund f = funds.get(i);
                    System.out.println((i + 1) + " - " + f +
                            " | Disponível: R$ " + f.getAvailableBalance(currentDay) +
                            " | Bloqueado: R$ " + f.getBlockedBalance(currentDay));
                }

                System.out.print("Escolha um fundo para simular: ");
                int fIndex = sc.nextInt() - 1;
                if (fIndex < 0 || fIndex >= funds.size()) {
                    System.out.println("Fundo inválido!");
                    continue;
                }

                Fund f = funds.get(fIndex);

                System.out.print("Informe número de dias para simulação: ");
                int days = sc.nextInt();

                double valorEmReais = coin.realConvert();
                double simulated = f.simulate(valorEmReais, days);
                System.out.println("Valor após " + days + " dias: R$ " + simulated);

                System.out.println("1 - Aplicar no fundo " + f.getCode());
                System.out.println("2 - Simular nova aplicação");
                System.out.println("3 - Guardar sem fundo");
                int applyChoice = sc.nextInt();

                if (applyChoice == 1) {
                    if (valorEmReais >= f.getMinValue()) {
                        coin.setAppliedToFund(true);
                        f.addToFund(valorEmReais, currentDay);
                        System.out.println("Valor aplicado no fundo " + f.getCode() + " (R$ " + valorEmReais + ")");
                        simular = false;
                    } else {
                        System.out.println("Valor abaixo do mínimo exigido pelo fundo!");
                    }
                } else if (applyChoice == 3) {
                    simular = false;
                }
            }
        }

        // Adiciona a moeda ao cofrinho (mesmo que parte tenha sido aplicada em fundo)
        piggyBank.addCoin(coin);
    }
}
