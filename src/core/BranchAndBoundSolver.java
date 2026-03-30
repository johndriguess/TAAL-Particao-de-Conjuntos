package core;

import models.PartitionResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por aplicar estratégias de poda na árvore
 * utilizando pré-cálculo e limite superior (Branch and Bound).
 */
public class BranchAndBoundSolver {
    private final int[] numbers;
    private final int targetSum;
    private final int[] suffixSums;

    public BranchAndBoundSolver(int[] numbers, int targetSum, int[] suffixSums) {
        this.numbers = numbers;
        this.targetSum = targetSum;
        this.suffixSums = suffixSums;
    }

    /**
     * Inicia a resolução do problema de partição usando poda (pruning).
     *
     * @return Um objeto PartitionResult representando o sucesso (ou não) da partição.
     */
    public PartitionResult solve() {
        if (targetSum == -1) return new PartitionResult(false, null, null);

        List<Integer> finalSubset1 = new ArrayList<>();
        List<Integer> finalSubset2 = new ArrayList<>();

        boolean found = branchAndBound(0, 0, new ArrayList<>(), new ArrayList<>(), finalSubset1, finalSubset2);
        return found ? new PartitionResult(true, finalSubset1, finalSubset2) : new PartitionResult(false, null, null);
    }

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
