package models;

import java.util.List;

/**
 * Representa o resultado de uma operação de partição de conjunto em dois subconjuntos com somas iguais.
 */
public class PartitionResult {
    private final boolean possible;
    private final List<Integer> subset1;
    private final List<Integer> subset2;

    /**
     * Construtor da classe PartitionResult.
     *
     * @param possible Indica se foi possível realizar a partição.
     * @param subset1  Lista contendo os elementos do primeiro subconjunto.
     * @param subset2  Lista contendo os elementos do segundo subconjunto.
     */
    public PartitionResult(boolean possible, List<Integer> subset1, List<Integer> subset2) {
        this.possible = possible;
        this.subset1 = subset1;
        this.subset2 = subset2;
    }

    /**
     * Verifica se a partição foi possível.
     *
     * @return true se foi possível encontrar a partição, false caso contrário.
     */
    public boolean isPossible() {
        return possible;
    }

    /**
     * Retorna o primeiro subconjunto resultante da partição.
     *
     * @return a lista de inteiros do primeiro subconjunto, ou null se a partição não for possível.
     */
    public List<Integer> getSubset1() {
        return subset1;
    }

    /**
     * Retorna o segundo subconjunto resultante da partição.
     *
     * @return a lista de inteiros do segundo subconjunto, ou null se a partição não for possível.
     */
    public List<Integer> getSubset2() {
        return subset2;
    }
}
