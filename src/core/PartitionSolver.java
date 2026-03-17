package core;

import models.PartitionResult;

import java.util.ArrayList;
import java.util.List;

public class PartitionSolver {
    private final int[] numbers;
    private final int targetSum;
    private final int[] suffixSums;

    public PartitionSolver(int[] numbers) {
        this.numbers = numbers;
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        this.targetSum = (sum % 2 == 0) ? (sum / 2) : -1;

        // Pré-calcula a soma de cada posição até o final (Soma Sufixa)
        // Isso permite saber o potencial máximo de crescimento em tempo O(1)
        this.suffixSums = new int[numbers.length + 1];
        for (int i = numbers.length - 1; i >= 0; i--) {
            suffixSums[i] = suffixSums[i + 1] + numbers[i];
        }
    }

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
    public PartitionResult solveBranchAndBound() {
        if (targetSum == -1) return new PartitionResult(false, null, null);

        List<Integer> finalSubset1 = new ArrayList<>();
        List<Integer> finalSubset2 = new ArrayList<>();

        boolean found = branchAndBound(0, 0, new ArrayList<>(), new ArrayList<>(), finalSubset1, finalSubset2);
        return found ? new PartitionResult(true, finalSubset1, finalSubset2) : new PartitionResult(false, null, null);
    }

    private boolean branchAndBound(int index, int currentSum, List<Integer> currentSubset1, List<Integer> currentSubset2, List<Integer> finalSubset1, List<Integer> finalSubset2) {
        // Caso base: sucesso
        if (currentSum == targetSum) {
            finalSubset1.addAll(currentSubset1);
            finalSubset2.addAll(currentSubset2);
            for (int i = index; i < numbers.length; i++) finalSubset2.add(numbers[i]);
            return true;
        }

        // Falha base
        if (index >= numbers.length) return false;

        // --- PODA (THE BOUND) ---
        // Se a soma atual + todos os números que restam for menor que o alvo,
        // é impossível chegar no resultado por este caminho. Poda-se a árvore aqui.
        if (currentSum + suffixSums[index] < targetSum) {
            return false;
        }

        // Outra poda: se a soma atual já passou do alvo, interrompe.
        if (currentSum > targetSum) {
            return false;
        }

        // Branch 1: Incluir no subset1
        currentSubset1.add(numbers[index]);
        if (branchAndBound(index + 1, currentSum + numbers[index], currentSubset1, currentSubset2, finalSubset1, finalSubset2)) return true;
        currentSubset1.remove(currentSubset1.size() - 1);

        // Branch 2: Incluir no subset2
        currentSubset2.add(numbers[index]);
        if (branchAndBound(index + 1, currentSum, currentSubset1, currentSubset2, finalSubset1, finalSubset2)) return true;
        currentSubset2.remove(currentSubset2.size() - 1);

        return false;
    }

}
