/**
 * Classe que representa um fundo de investimento com período de bloqueio.
 * Permite calcular rendimento e controlar saldo bloqueado e disponível.
 */
public class Fund {

    private String code;        // Código do fundo (F001, F002, F003)
    private String type;        // Tipo do fundo (D+0, D+5, D+30)
    private double minValue;    // Valor mínimo para investir
    private double interestRate; // Rendimento percentual ao mês
    private double balance;      // Saldo acumulado no fundo
    private int daysToUnlock;    // Dias necessários para liberar saque
    private int lastDepositDay;  // Dia do último depósito

    public Fund(String code, String type, double minValue, double interestRate, int daysToUnlock) {
        this.code = code;
        this.type = type;
        this.minValue = minValue;
        this.interestRate = interestRate;
        this.balance = 0;
        this.daysToUnlock = daysToUnlock;
        this.lastDepositDay = 0;
    }

    /**
     * Adiciona valor ao fundo.
     * Atualiza saldo acumulado com rendimento antes de adicionar novo valor.
     * Só permite adicionar valores maiores ou iguais ao valor mínimo de entrada.
     *
     * @param amount Valor em Reais a adicionar
     * @param currentDay Dia atual do sistema
     */
    public void addToFund(double amount, int currentDay) {
        if (amount < minValue) {
            System.out.println("Não é possível cadastrar o valor. Valor mínimo exigido pelo fundo "
                    + code + ": R$ " + minValue);
            return;
        }
        balance = getBalanceWithInterest(currentDay);
        balance += amount;
        lastDepositDay = currentDay;
        System.out.println("Valor de R$ " + amount + " adicionado ao fundo " + code + " com sucesso!");
    }


    /**
     * Simula rendimento de um valor em um número de dias.
     * <p>
     * Fórmula utilizada:
     *   Valor_final = Valor_inicial * (1 + (taxa_mensal / 100))^(dias / 30)
     * </p>
     * Onde:
     * - Valor_inicial: amount (em Reais)
     * - taxa_mensal: interestRate (% ao mês)
     * - dias: número de dias que se deseja simular
     *
     * @param amount Valor em Reais
     * @param days Número de dias
     * @return Valor simulado após rendimento
     */
    public double simulate(double amount, int days) {
        return amount * Math.pow(1 + (interestRate / 100), days / 30.0);
    }

    /**
     * Retorna saldo total do fundo com rendimento até currentDay.
     * @param currentDay Dia atual
     * @return saldo atualizado com juros
     */
    public double getBalanceWithInterest(int currentDay) {
        int diasPassados = currentDay - lastDepositDay;
        if(diasPassados <= 0) return balance;
        return balance * Math.pow(1 + (interestRate / 100), diasPassados / 30.0);
    }

    /**
     * Retorna saldo disponível para resgate considerando bloqueio.
     */
    public double getAvailableBalance(int currentDay) {
        return (currentDay - lastDepositDay >= daysToUnlock) ? getBalanceWithInterest(currentDay) : 0;
    }

    /**
     * Retorna saldo bloqueado (não disponível) para resgate.
     */
    public double getBlockedBalance(int currentDay) {
        return (currentDay - lastDepositDay < daysToUnlock) ? getBalanceWithInterest(currentDay) : 0;
    }

    /**
     * Resgata valor do fundo (somente disponível).
     */
    public void withdraw(double amount, int currentDay) {
        double disponivel = getAvailableBalance(currentDay);
        if(amount <= disponivel) {
            balance = getBalanceWithInterest(currentDay) - amount;
            lastDepositDay = currentDay; // reset para o restante
        } else {
            System.out.println("Saldo bloqueado ou insuficiente no fundo!");
        }
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public double getMinValue() {
        return minValue;
    }

    @Override
    public String toString() {
        return code + " - " + type + " | Min: R$ " + minValue;
    }
}
