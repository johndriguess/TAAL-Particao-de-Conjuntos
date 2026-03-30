package core;

import models.PartitionResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por executar a busca por tentativa e erro (Backtracking)
 * clássica e sem podas avançadas.
 */
public class BacktrackingSolver {
    private final int[] numbers;
    private final int targetSum;

    public BacktrackingSolver(int[] numbers, int targetSum) {
        this.numbers = numbers;
        this.targetSum = targetSum;
    }

    /**
     * Inicia a resolução do problema de partição.
     *
     * @return Um objeto PartitionResult representando o sucesso (ou não) da partição.
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
}
