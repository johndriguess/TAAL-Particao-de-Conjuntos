package core;

import models.PartitionResult;

/**
 * Essa classe provê métodos para resolver o problema de particionamento 
 * de um conjunto em dois subconjuntos distintos que possuem a mesma soma.
 * Ela atua como um coordenador que repassa as chamadas para as classes de 
 * Backtracking e Branch & Bound.
 */
public class PartitionSolver {
    private final int[] numbers;
    private final int targetSum;
    private final int[] suffixSums;

    /**
     * Construtor da classe PartitionSolver.
     * Calcula também os arrays de somas de sufixos úteis para otimização em Branch and Bound.
     *
     * @param numbers Arranjo de inteiros que representam os elementos a serem particionados.
     */
    public PartitionSolver(int[] numbers) {
        this.numbers = numbers;
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        this.targetSum = (sum % 2 == 0) ? (sum / 2) : -1;

        // Pré-calcula a soma de cada posição até o final (Soma Sufixa)
        this.suffixSums = new int[numbers.length + 1];
        for (int i = numbers.length - 1; i >= 0; i--) {
            suffixSums[i] = suffixSums[i + 1] + numbers[i];
        }
    }

    /**
     * Inicia a resolução do problema de partição usando o algoritmo Backtracking 
     * acessando sua respectiva classe separada.
     *
     * @return Um objeto PartitionResult representando o sucesso (ou não) da partição.
     */
    public PartitionResult solve() {
        BacktrackingSolver bSolver = new BacktrackingSolver(this.numbers, this.targetSum);
        return bSolver.solve();
    }

    /**
     * Inicia a resolução do problema de partição usando o algoritmo Branch and Bound 
     * acessando sua respectiva classe separada.
     *
     * @return Um objeto PartitionResult.
     */
    public PartitionResult solveBranchAndBound() {
        BranchAndBoundSolver bbSolver = new BranchAndBoundSolver(this.numbers, this.targetSum, this.suffixSums);
        return bbSolver.solve();
    }

    /**
     * Inicia a resolução do problema de partição usando Programação Dinâmica
     * acessando sua respectiva classe separada.
     *
     * @return Um objeto PartitionResult.
     */
    public PartitionResult solveDynamicProgramming() {
        DynamicProgrammingSolver dpSolver = new DynamicProgrammingSolver(this.numbers, this.targetSum);
        return dpSolver.solve();
    }
}
