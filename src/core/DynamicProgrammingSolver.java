package core;

import models.PartitionResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por executar a solução utilizando os princípios da 
 * Programação Dinâmica para o problema da Partição de Conjuntos.
 */
public class DynamicProgrammingSolver {
    private final int[] numbers;
    private final int targetSum;

    public DynamicProgrammingSolver(int[] numbers, int targetSum) {
        this.numbers = numbers;
        this.targetSum = targetSum;
    }

    /**
     * Inicia a resolução do problema de partição usando Programação Dinâmica.
     *
     * @return Um objeto PartitionResult representando o sucesso (ou não) da partição.
     */
    public PartitionResult solve() {
        if (targetSum == -1) {
            return new PartitionResult(false, null, null);
        }

        int n = numbers.length;
        // dp[i][j] será true se for possível formar a soma j com um subconjunto dos primeiros i elementos
        boolean[][] dp = new boolean[n + 1][targetSum + 1];

        // Se a soma alvo é 0, a reposta é sempre verdadeira (conjunto vazio)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Se o conjunto está vazio e a soma alvo é > 0, é falsa
        for (int j = 1; j <= targetSum; j++) {
            dp[0][j] = false;
        }

        // Preenche a tabela de PD
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= targetSum; j++) {
                // Se o elemento atual for maior que a soma atual, não podemos incluí-lo
                if (numbers[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // Verifica se a soma j pode ser obtida ignorando o elemento ou incluindo-o
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - numbers[i - 1]];
                }
            }
        }

        // Se não for possível formar a soma, retorna falso
        if (!dp[n][targetSum]) {
            return new PartitionResult(false, null, null);
        }

        // Reconstrução dos subconjuntos
        List<Integer> subset1 = new ArrayList<>();
        List<Integer> subset2 = new ArrayList<>();

        int currentSum = targetSum;
        boolean[] used = new boolean[n];

        for (int i = n; i > 0 && currentSum > 0; i--) {
            // Se a soma atual não pôde ser formada sem o i-ésimo elemento, 
            // então ele com certeza deve estar na solução.
            if (!dp[i - 1][currentSum]) {
                subset1.add(numbers[i - 1]);
                currentSum -= numbers[i - 1];
                used[i - 1] = true;
            }
        }

        // Os restantes vão para o subset2
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                subset2.add(numbers[i]);
            }
        }

        return new PartitionResult(true, subset1, subset2);
    }
}
