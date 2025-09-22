import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal do sistema de Cofrinho de moedas com aplicação em fundos.
 * Permite adicionar moedas, aplicar em fundos, listar saldo, transferir entre fundos,
 * avançar dias e realizar resgate de fundos.
 */
public class Main {

    /**
     * Ponto de entrada do sistema.
     * Inicializa o Scanner e chama o processo principal.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        process(sc);

        sc.close();
    }

    /**
     * Método que contém o loop principal do sistema.
     * Exibe o menu, lê a opção do usuário e executa a funcionalidade correspondente.
     *
     * @param sc Scanner para leitura de entradas do usuário
     */
    private static void process(Scanner sc) {
        PiggyBank piggyBank = new PiggyBank();

        // Cria lista de fundos disponíveis
        List<Fund> funds = new ArrayList<>();
        funds.add(new Fund("F001", "D+0", 1.0, 100.0, 0));
        funds.add(new Fund("F002", "D+5", 100.0, 150.0, 5));
        funds.add(new Fund("F003", "D+30", 200.0, 200.0, 30));

        int currentDay = 0; // Dia inicial
        int opcao;

        // Loop principal do menu
        do {
            System.out.println("\n=== COFRINHO ===");
            System.out.println("Dia atual: " + currentDay);
            System.out.println("1 - Adicionar moeda");
            System.out.println("2 - Remover moeda");
            System.out.println("3 - Listar moedas e fundos");
            System.out.println("4 - Calcular total em Reais");
            System.out.println("5 - Transferir fundos");
            System.out.println("6 - Avançar dias");
            System.out.println("7 - Simular resgate");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            // Executa ação de acordo com a opção selecionada
            switch (opcao) {

                case 1 -> AddCoin.execute(piggyBank, funds, sc, currentDay); // Adicionar moeda

                case 2 -> RemoveCoin.execute(sc, piggyBank); // Remover moeda

                case 3 -> ListCoins.execute(piggyBank, funds, currentDay); // Listar moedas e fundos

                case 4 -> piggyBank.calculateTotal(funds, currentDay); // Calcular total em reais

                case 5 -> CoinFundTransfer.execute(funds, sc, currentDay); // Transferir entre fundos

                case 6 -> currentDay = getCurrentDay(sc, currentDay); // Avançar dias

                case 7 -> Withdrawal.execute(sc, funds, currentDay); // Resgatar fundos disponíveis

                case 0 -> System.out.println("Saindo..."); // Sair do sistema

                default -> System.out.println("Opção inválida!"); // Opção inválida
            }
        } while (opcao != 0);
    }

    /**
     * Avança o número de dias no sistema conforme entrada do usuário.
     *
     * @param sc         Scanner para leitura do número de dias
     * @param currentDay Dia atual do sistema
     * @return Novo dia atual após avançar os dias
     */
    private static int getCurrentDay(Scanner sc, int currentDay) {
        System.out.print("Informe quantos dias deseja avançar: ");
        int dias = sc.nextInt();

        currentDay += dias;
        System.out.println("Dias atualizados para " + currentDay);

        return currentDay;
    }
}
