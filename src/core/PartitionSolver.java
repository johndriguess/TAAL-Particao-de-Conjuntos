package core;

import models.PartitionResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Essa classe provê métodos para resolver o problema de particionamento 
 * de um conjunto em dois subconjuntos distintos que possuem a mesma soma.
 * Ela possui implementações usando os algoritmos de Backtracking 
 * e Branch & Bound.
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

        this.suffixSums = new int[numbers.length + 1];
        for (int i = numbers.length - 1; i >= 0; i--) {
            suffixSums[i] = suffixSums[i + 1] + numbers[i];
        }
    }

    /**
     * Inicia a resolução do problema de partição usando um algoritmo de Backtracking simples.
     *
     * @return Um objeto PartitionResult representando o sucesso (ou não) da partição, junto aos subconjuntos.
     */
    public PartitionResult solve() {
        if (targetSum == -1) {
            return new PartitionResult(false, null, null);
        }

        List<Integer> currentSubset1 = new ArrayList<>();
        List<Integer> currentSubset2 = new ArrayList<>();
        List<Integer> finalSubset1 = new ArrayList<>();
        List<Integer> finalSubset2 = new ArrayList<>();

        boolean found = backtrack(0, 0, currentSubset1, currentSubset2, finalSubset1, finalSubset2);

        if (found) {
            return new PartitionResult(true, finalSubset1, finalSubset2);
        }

        return new PartitionResult(false, null, null);
    }

    /**
     * Algoritmo de Backtracking recursivo usado na função solve().
     *
     * @param index O índice atual do qual se está tomando decisão para os elementos de `numbers`.
     * @param currentSum A soma acumulada dos elementos atualmente em `currentSubset1`.
     * @param currentSubset1 Uma lista temporal de inteiros que estarão no primeiro subconjunto.
     * @param currentSubset2 Uma lista temporal de inteiros que estarão no segundo subconjunto.
     * @param finalSubset1 Lista de inteiros final de retorno do primeiro subconjunto, a ser preenchida caso encontrada.
     * @param finalSubset2 Lista de inteiros final de retorno do segundo subconjunto, a ser preenchida caso encontrada.
     * @return true se foi encontrada uma partição válida, false caso contrário.
     */
    private boolean backtrack(int index, int currentSum, List<Integer> currentSubset1, List<Integer> currentSubset2, List<Integer> finalSubset1, List<Integer> finalSubset2) {
        if (currentSum == targetSum) {
            finalSubset1.addAll(currentSubset1);
            finalSubset2.addAll(currentSubset2);
            for (int i = index; i < numbers.length; i++) {
                finalSubset2.add(numbers[i]);
            }
            return true;
        }

        if (currentSum > targetSum || index >= numbers.length) {
            return false;
        }

        currentSubset1.add(numbers[index]);
        if (backtrack(index + 1, currentSum + numbers[index], currentSubset1, currentSubset2, finalSubset1, finalSubset2)) {
            return true;
        }
        currentSubset1.remove(currentSubset1.size() - 1);

        currentSubset2.add(numbers[index]);
        if (backtrack(index + 1, currentSum, currentSubset1, currentSubset2, finalSubset1, finalSubset2)) {
            return true;
        }
        currentSubset2.remove(currentSubset2.size() - 1);

        return false;
    }
    /**
     * Inicia a resolução do problema de partição usando o algoritmo de Branch and Bound, o qual utiliza heurísticas avançadas 
     * como verificação de somas das partições faltantes para realizar as podas da árvore de exploração.
     *
     * @return Um objeto PartitionResult representando o sucesso (ou não) da partição, junto aos subconjuntos resultantes.
     */
    public PartitionResult solveBranchAndBound() {
        if (targetSum == -1) return new PartitionResult(false, null, null);

        List<Integer> finalSubset1 = new ArrayList<>();
        List<Integer> finalSubset2 = new ArrayList<>();

        boolean found = branchAndBound(0, 0, new ArrayList<>(), new ArrayList<>(), finalSubset1, finalSubset2);
        return found ? new PartitionResult(true, finalSubset1, finalSubset2) : new PartitionResult(false, null, null);
    }

    /**
     * Algoritmo Branch and Bound recursivo usado na função solveBranchAndBound(), o qual implementa estratégias de poda para explorar somente a árvore necessária.
     *
     * @param index O índice atual para o elemento de inserção ou exclusão.
     * @param currentSum A soma acumulada pro primeiro subconjunto da árvore de decisões.
     * @param currentSubset1 Retém o estado temporal de elementos colocados no 1º grupo.
     * @param currentSubset2 Retém o estado temporal de elementos não colocados no 1º grupo.
     * @param finalSubset1 Resultado preenchido mediante sucesso.
     * @param finalSubset2 Resultado preenchido mediante sucesso.
     * @return true se foi encontrada partição com os devidos requisitos, senão false.
     */
    private boolean branchAndBound(int index, int currentSum, List<Integer> currentSubset1, List<Integer> currentSubset2, List<Integer> finalSubset1, List<Integer> finalSubset2) {
        if (currentSum == targetSum) {
            finalSubset1.addAll(currentSubset1);
            finalSubset2.addAll(currentSubset2);
            for (int i = index; i < numbers.length; i++) finalSubset2.add(numbers[i]);
            return true;
        }

        if (index >= numbers.length) return false;
        
        if (currentSum + suffixSums[index] < targetSum) {
            return false;
        }

        if (currentSum > targetSum) {
            return false;
        }

        currentSubset1.add(numbers[index]);
        if (branchAndBound(index + 1, currentSum + numbers[index], currentSubset1, currentSubset2, finalSubset1, finalSubset2)) return true;
        currentSubset1.remove(currentSubset1.size() - 1);

        currentSubset2.add(numbers[index]);
        if (branchAndBound(index + 1, currentSum, currentSubset1, currentSubset2, finalSubset1, finalSubset2)) return true;
        currentSubset2.remove(currentSubset2.size() - 1);

        return false;
    }

}
