public class Corretor extends Pessoa {
    private String creci;

    public Corretor(String nome, String cpf, String creci) {
        super(nome, cpf);
        this.creci = creci;
    }

    public String getCreci() {
        return creci;
    }
}
