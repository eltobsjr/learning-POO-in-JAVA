package br.edu.ifpi.Factory;

import br.edu.ifpi.Model.Endereco;
import br.edu.ifpi.Model.Imovel;

/**
 * Factory Method Pattern - Abstract Creator
 * 
 * Classe abstrata que define o contrato para criação de imóveis.
 * Cada tipo de imóvel terá sua própria implementação concreta desta factory.
 * 
 * Benefícios:
 * - Encapsula a lógica de criação de objetos
 * - Facilita a adição de novos tipos de imóveis
 * - Centraliza validações específicas de cada tipo
 * - Segue o princípio Open/Closed (aberto para extensão, fechado para modificação)
 */
public abstract class ImovelFactory {
    
    /**
     * Factory Method abstrato - deve ser implementado pelas subclasses.
     * Cada factory concreta define como criar seu tipo específico de imóvel.
     * 
     * @param endereco Endereço do imóvel
     * @param valor Valor do imóvel
     * @param parametrosEspecificos Parâmetros específicos de cada tipo de imóvel
     *                              (ex: número de quartos para Casa, andar e condomínio para Apartamento)
     * @return Instância do imóvel criado
     * @throws IllegalArgumentException se os parâmetros forem inválidos
     */
    public abstract Imovel criarImovel(Endereco endereco, double valor, Object... parametrosEspecificos);
    
    /**
     * Template Method - define o processo completo de criação de um imóvel.
     * 
     * Este método orquestra:
     * 1. Criação do imóvel (delegado ao factory method)
     * 2. Validações comuns
     * 3. Validações específicas (hook method)
     * 
     * @param endereco Endereço do imóvel
     * @param valor Valor do imóvel
     * @param parametrosEspecificos Parâmetros específicos do tipo de imóvel
     * @return Imóvel criado e validado
     * @throws IllegalArgumentException se houver erro na validação
     */
    public Imovel criarImovelCompleto(Endereco endereco, double valor, Object... parametrosEspecificos) {
        // 1. Validações pré-criação
        validarParametrosBasicos(endereco, valor);
        
        // 2. Cria o imóvel usando o factory method específico
        Imovel imovel = criarImovel(endereco, valor, parametrosEspecificos);
        
        // 3. Validações pós-criação (hook method - pode ser sobrescrito)
        validarImovelCriado(imovel);
        
        return imovel;
    }
    
    /**
     * Validações comuns a todos os tipos de imóveis.
     * 
     * @param endereco Endereço a ser validado
     * @param valor Valor a ser validado
     * @throws IllegalArgumentException se os parâmetros forem inválidos
     */
    protected void validarParametrosBasicos(Endereco endereco, double valor) {
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo");
        }
        
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do imóvel deve ser maior que zero");
        }
    }
    
    /**
     * Hook Method - pode ser sobrescrito pelas subclasses para validações específicas.
     * 
     * @param imovel Imóvel a ser validado
     * @throws IllegalArgumentException se a validação falhar
     */
    protected void validarImovelCriado(Imovel imovel) {
        if (imovel == null) {
            throw new IllegalArgumentException("Erro ao criar imóvel: resultado nulo");
        }
    }
    
    /**
     * Static Factory Method - retorna a factory apropriada baseada no tipo.
     * 
     * Este método centraliza a decisão de qual factory concreta instanciar,
     * seguindo o padrão Simple Factory em conjunto com Factory Method.
     * 
     * @param tipo Tipo de imóvel desejado
     * @return Factory apropriada para o tipo especificado
     * @throws IllegalArgumentException se o tipo for nulo ou não suportado
     */
    public static ImovelFactory getFactory(TipoImovel tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de imóvel não pode ser nulo");
        }
        
        return switch (tipo) {
            case CASA -> new CasaFactory();
            case APARTAMENTO -> new ApartamentoFactory();
            // Novos tipos podem ser adicionados aqui sem modificar código existente
        };
    }
}
