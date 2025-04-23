public class ContaBancaria {
    private String numero;
    private String titular;
    private long saldo;

    public ContaBancaria(String numero, String titular) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0;
    }

    public long sacar(long valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            return valor;
        }
        return 0;
    }

    public long depositar(long valor) {
        if (valor > 0) {
            this.saldo += valor;
            return this.saldo;
        }
        return this.saldo;
    }

    // Getters and setters
    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public long getSaldo() {
        return saldo;
    }
}
