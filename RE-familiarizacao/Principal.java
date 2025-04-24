

/**
 * Classe principal que demonstra o uso da classe ContaBancaria
 */
public class Principal {
    public static void main(String[] args) {
        System.out.println("Bem-vindo ao sistema bancário!");
        ContaBancaria contaBancaria = new ContaBancaria("12345-6", "João Silva");
        
        // Realizando operações bancárias
        System.out.println("Saldo inicial: " + contaBancaria.getSaldo());
        
        // Depositando dinheiro
        long valorDeposito = 1000;
        contaBancaria.depositar(valorDeposito);
        System.out.println("Saldo após depósito de " + valorDeposito + ": " + contaBancaria.getSaldo());
        
        // Sacando dinheiro
        long valorSaque = 300;
        long valorSacado = contaBancaria.sacar(valorSaque);
        System.out.println("Valor sacado: " + valorSacado);
        System.out.println("Saldo após saque: " + contaBancaria.getSaldo());
        
        // Tentando sacar mais do que o saldo
        long valorSaqueInvalido = 2000;
        long resultadoSaqueInvalido = contaBancaria.sacar(valorSaqueInvalido);
        System.out.println("Tentativa de saque de " + valorSaqueInvalido + ": " + resultadoSaqueInvalido);
        System.out.println("Saldo após tentativa de saque inválido: " + contaBancaria.getSaldo());
        
        // Exibindo informações da conta
        System.out.println("\nInformações da conta:");
        System.out.println("Titular: " + contaBancaria.getTitular());
        System.out.println("Número da conta: " + contaBancaria.getNumero());
        System.out.println("Saldo atual: " + contaBancaria.getSaldo());
    }
}