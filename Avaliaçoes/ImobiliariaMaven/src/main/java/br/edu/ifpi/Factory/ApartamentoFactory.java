package br.edu.ifpi.Factory;

import br.edu.ifpi.Model.Apartamento;
import br.edu.ifpi.Model.Endereco;
import br.edu.ifpi.Model.Imovel;

/**
 * Factory Method Pattern - Concrete Creator para Apartamento
 * 
 * Responsável por criar instâncias de Apartamento com validações específicas.
 * 
 * Parâmetros esperados:
 * - parametrosEspecificos[0]: Integer - andar do apartamento
 * - parametrosEspecificos[1]: Double - valor do condomínio
 */
public class ApartamentoFactory extends ImovelFactory {
    
    /**
     * Implementação do Factory Method para criar um Apartamento.
     * 
     * @param endereco Endereço do apartamento
     * @param valor Valor do apartamento
     * @param parametrosEspecificos [0] = andar (Integer), [1] = valor do condomínio (Double)
     * @return Nova instância de Apartamento
     * @throws IllegalArgumentException se os parâmetros forem inválidos
     */
    @Override
    public Imovel criarImovel(Endereco endereco, double valor, Object... parametrosEspecificos) {
        // Valida quantidade de parâmetros
        if (parametrosEspecificos.length < 2) {
            throw new IllegalArgumentException(
                "Apartamento requer 2 parâmetros: andar (Integer) e valor do condomínio (Double)"
            );
        }
        
        // Extrai e valida andar
        int andar;
        try {
            andar = (Integer) parametrosEspecificos[0];
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(
                "Parâmetro inválido: andar deve ser um Integer", e
            );
        }
        
        // Extrai e valida valor do condomínio
        double valorCondominio;
        try {
            valorCondominio = (Double) parametrosEspecificos[1];
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(
                "Parâmetro inválido: valor do condomínio deve ser um Double", e
            );
        }
        
        // Valida regras de negócio específicas de Apartamento
        validarAndar(andar);
        validarValorCondominio(valorCondominio);
        
        // Cria e retorna o apartamento
        return new Apartamento(endereco, valor, andar, valorCondominio);
    }
    
    /**
     * Validação específica: andar deve estar em um range válido.
     * 
     * @param andar Andar a validar
     * @throws IllegalArgumentException se o andar for inválido
     */
    private void validarAndar(int andar) {
        if (andar < 0) {
            throw new IllegalArgumentException(
                "Andar não pode ser negativo. Valor informado: " + andar
            );
        }
        
        if (andar > 200) {
            throw new IllegalArgumentException(
                "Andar muito alto (máximo 200). Valor informado: " + andar
            );
        }
    }
    
    /**
     * Validação específica: valor do condomínio não pode ser negativo.
     * 
     * @param valorCondominio Valor do condomínio a validar
     * @throws IllegalArgumentException se o valor for inválido
     */
    private void validarValorCondominio(double valorCondominio) {
        if (valorCondominio < 0) {
            throw new IllegalArgumentException(
                "Valor do condomínio não pode ser negativo. Valor informado: R$ " + valorCondominio
            );
        }
        
        // Opcional: validar se condomínio é muito alto em relação ao valor do imóvel
        // Isso poderia ser adicionado se necessário
    }
    
    /**
     * Validação adicional pós-criação (sobrescreve hook method).
     * 
     * @param imovel Imóvel (Apartamento) a ser validado
     * @throws IllegalArgumentException se a validação falhar
     */
    @Override
    protected void validarImovelCriado(Imovel imovel) {
        super.validarImovelCriado(imovel);
        
        if (!(imovel instanceof Apartamento)) {
            throw new IllegalStateException("Factory criou tipo incorreto de imóvel");
        }
        
        Apartamento apartamento = (Apartamento) imovel;
        
        // Validação adicional: garante que os valores foram setados corretamente
        if (apartamento.getAndar() < 0) {
            throw new IllegalStateException(
                "Apartamento criado com andar inválido: " + apartamento.getAndar()
            );
        }
        
        if (apartamento.getValorCondominio() < 0) {
            throw new IllegalStateException(
                "Apartamento criado com valor de condomínio inválido: R$ " + apartamento.getValorCondominio()
            );
        }
    }
}
