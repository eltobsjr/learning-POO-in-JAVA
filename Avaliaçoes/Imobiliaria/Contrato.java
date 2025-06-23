// Arquivo: Contrato.java
import java.time.LocalDate;

public class Contrato {
    private static int proximoId = 1;
    private int id;
    private Imovel imovel;
    private Cliente cliente;
    private Corretor corretor;
    private double valorFinal;
    private TipoContrato tipo;
    private LocalDate data;

    public Contrato(Imovel imovel, Cliente cliente, Corretor corretor, double valorFinal, TipoContrato tipo) {
        this.id = proximoId++;
        this.imovel = imovel;
        this.cliente = cliente;
        this.corretor = corretor;
        this.valorFinal = valorFinal;
        this.tipo = tipo;
        this.data = LocalDate.now();
        imovel.setDisponivel(false);
    }

    public void imprimir() {
        System.out.println("Contrato ID: " + id);
        System.out.println("Tipo: " + tipo);
        System.out.println("Data: " + data);
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Corretor: " + corretor.getNome());
        System.out.println("Imóvel: ");
        imovel.exibirDetalhes();
        System.out.println("Valor Final: R$" + valorFinal);
    }
}
