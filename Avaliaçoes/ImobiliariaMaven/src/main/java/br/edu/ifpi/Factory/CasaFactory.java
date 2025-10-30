package br.edu.ifpi.Factory;

import br.edu.ifpi.Model.Casa;
import br.edu.ifpi.Model.Endereco;
import br.edu.ifpi.Model.Imovel;

/**
 * Factory Method Pattern - Concrete Creator para Casa
 * 
 * Responsável por criar instâncias de Casa com validações específicas.
 * 
 * Parâmetros esperados:
 * - parametrosEspecificos[0]: Integer - número de quartos
 */
public class CasaFactory extends ImovelFactory {
    
    /**
     * Implementação do Factory Method para criar uma Casa.
     * 
     * @param endereco Endereço da casa
     * @param valor Valor da casa
     * @param parametrosEspecificos [0] = número de quartos (Integer)
     * @return Nova instância de Casa
     * @throws IllegalArgumentException se os parâmetros forem inválidos
     */
    @Override
    public Imovel criarImovel(Endereco endereco, double valor, Object... parametrosEspecificos) {
        // Valida quantidade de parâmetros
        if (parametrosEspecificos.length < 1) {
            throw new IllegalArgumentException(
                "Casa requer 1 parâmetro: número de quartos (Integer)"
            );
        }
        
        // Extrai e valida número de quartos
        int numeroQuartos;
        try {
            numeroQuartos = (Integer) parametrosEspecificos[0];
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(
                "Parâmetro inválido: número de quartos deve ser um Integer", e
            );
        }
        
        // Valida regras de negócio específicas de Casa
        validarNumeroQuartos(numeroQuartos);
        
        // Cria e retorna a casa
        return new Casa(endereco, valor, numeroQuartos);
    }
    
    /**
     * Validação específica: número de quartos deve ser positivo.
     * 
     * @param numeroQuartos Número de quartos a validar
     * @throws IllegalArgumentException se o número for inválido
     */
    private void validarNumeroQuartos(int numeroQuartos) {
        if (numeroQuartos <= 0) {
            throw new IllegalArgumentException(
                "Casa deve ter pelo menos 1 quarto. Valor informado: " + numeroQuartos
            );
        }
        
        if (numeroQuartos > 50) {
            throw new IllegalArgumentException(
                "Número de quartos muito alto (máximo 50). Valor informado: " + numeroQuartos
            );
        }
    }
    
    /**
     * Validação adicional pós-criação (sobrescreve hook method).
     * 
     * @param imovel Imóvel (Casa) a ser validado
     * @throws IllegalArgumentException se a validação falhar
     */
    @Override
    protected void validarImovelCriado(Imovel imovel) {
        super.validarImovelCriado(imovel);
        
        if (!(imovel instanceof Casa)) {
            throw new IllegalStateException("Factory criou tipo incorreto de imóvel");
        }
        
        Casa casa = (Casa) imovel;
        
        // Validação adicional: garante que o número de quartos foi setado corretamente
        if (casa.getNumeroQuartos() <= 0) {
            throw new IllegalStateException(
                "Casa criada com número inválido de quartos: " + casa.getNumeroQuartos()
            );
        }
    }
}
