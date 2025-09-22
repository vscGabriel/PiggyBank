import java.util.Scanner;

/**
 * Classe que encapsula a funcionalidade de remover moedas do cofrinho.
 * Não permite remover moedas que foram aplicadas em fundos.
 */
public class RemoveCoin {

    /**
     * Remove moeda do cofrinho pelo índice.
     * Se a moeda estiver aplicada em um fundo, exibe mensagem informando
     * que só pode ser resgatada via fundo.
     *
     * @param sc         Scanner para leitura de entrada
     * @param piggyBank  Cofrinho do usuário
     */
    public static void execute(Scanner sc, PiggyBank piggyBank) {
        piggyBank.listCoins();
        System.out.print("Informe índice da moeda para retirar: ");
        int index = sc.nextInt();

        if (index < 0 || index >= piggyBank.getCoins().size()) {
            System.out.println("Índice inválido!");
            return;
        }

        Coin coin = piggyBank.getCoins().get(index);

        if (coin.isAppliedToFund()) {
            System.out.println("Essa moeda está aplicada em um fundo e não pode ser retirada diretamente!");
            System.out.println("Para resgatá-la, utilize a opção de resgate do fundo correspondente.");
        } else {
            piggyBank.removeCoin(index);
        }
    }
}
