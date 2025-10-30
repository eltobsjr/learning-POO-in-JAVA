package br.edu.ifpi.Factory;

/**
 * Enum que define os tipos de imóveis disponíveis no sistema.
 * Utilizado pelo Factory Method Pattern para determinar qual factory instanciar.
 */
public enum TipoImovel {
    CASA("Casa"),
    APARTAMENTO("Apartamento");
    
    private final String descricao;
    
    TipoImovel(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
}
