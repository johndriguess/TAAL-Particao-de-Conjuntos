package core;

import models.PartitionResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe responsável por executar a solução utilizando uma Estratégia Gulosa
 * para o problema da Partição de Conjuntos (Aproximação Diferença Mínima).
 * A estratégia comum é o Algoritmo de Diferenciação Gananciosa (Greedy Number Partitioning).
 */
public class GreedySolver {
    private final int[] numbers;
    private final int targetSum;

    public GreedySolver(int[] numbers, int targetSum) {
        this.numbers = numbers;
        this.targetSum = targetSum;
    }

    /**
     * Inicia a resolução do problema de partição usando uma Estratégia Gulosa.
     * Nota: O algoritmo guloso nem sempre encontra uma partição exata mesmo que ela exista.
     *
     * @return Um objeto PartitionResult representando a tentativa de partição.
     */
    public PartitionResult solve() {
        if (targetSum == -1) {
            return new PartitionResult(false, null, null);
        }

        // Ordena os números em ordem decrescente para a melhor aproximação gulosa
        int[] sortedNumbers = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(sortedNumbers);
        
        List<Integer> subset1 = new ArrayList<>();
        List<Integer> subset2 = new ArrayList<>();
        long sum1 = 0;
        long sum2 = 0;

        // Distribui cada número para o subconjunto que atualmente tem a menor soma
        for (int i = sortedNumbers.length - 1; i >= 0; i--) {
            int num = sortedNumbers[i];
            if (sum1 <= sum2) {
                subset1.add(num);
                sum1 += num;
            } else {
                subset2.add(num);
                sum2 += num;
            }
        }

        // Verifica se a partição encontrada é exata (somas iguais)
        boolean possible = (sum1 == sum2);

        return new PartitionResult(possible, subset1, subset2);
    }
}
