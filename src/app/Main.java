package app;
import core.PartitionSolver;
import io.FileHandler;
import models.PartitionResult;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Classe principal do aplicativo. Responsável por inicializar e 
 * coordenar a leitura, cálculo e exportação dos resultados.
 */
public class Main {
    /**
     * Método de entrada do programa.
     * Tenta ler um arquivo especificado nos argumentos ou utiliza o "data/input.txt" por padrão.
     * Mede os tempos de execução das abordagens Backtracking e Branch & Bound e salva os resultados.
     *
     * @param args Argumentos da linha de comando cujo primeiro elemento pode ser o caminho do arquivo de entrada.
     */
    public static void main(String[] args) {
        String inputFilePath = "data/input.txt";

        if (args.length > 0) {
            inputFilePath = args[0];
        }

        try {
            int[] numbers = FileHandler.readInput(inputFilePath);
            PartitionSolver solver = new PartitionSolver(numbers);

            long startTimeBT = System.nanoTime();
            PartitionResult resultBT = solver.solve();
            long endTimeBT = System.nanoTime();
            double durationBT = (endTimeBT - startTimeBT) / 1_000_000.0; 

            System.out.println("--- Resultado: Backtracking ---");
            FileHandler.printOutput(resultBT);
            System.out.printf("Tempo de execução (BT): %.4f ms\n", durationBT);

            long startTimeBB = System.nanoTime();
            PartitionResult resultBB = solver.solveBranchAndBound();
            long endTimeBB = System.nanoTime();
            double durationBB = (endTimeBB - startTimeBB) / 1_000_000.0;

            System.out.println("\n--- Resultado: Branch and Bound ---");
            FileHandler.printOutput(resultBB);
            System.out.printf("Tempo de execução (BB): %.4f ms\n", durationBB);

            long startTimeDP = System.nanoTime();
            PartitionResult resultDP = solver.solveDynamicProgramming();
            long endTimeDP = System.nanoTime();
            double durationDP = (endTimeDP - startTimeDP) / 1_000_000.0;

            System.out.println("\n--- Resultado: Programação Dinâmica ---");
            FileHandler.printOutput(resultDP);
            System.out.printf("Tempo de execução (PD): %.4f ms\n", durationDP);

            try (PrintWriter writer = new PrintWriter(new FileWriter("data/output.txt"))) {
                writer.println("--- Resultado: Backtracking ---");
                FileHandler.writeOutput(resultBT, writer);
                writer.printf("Tempo de execução (BT): %.4f ms\n", durationBT);

                writer.println("\n--- Resultado: Branch and Bound ---");
                FileHandler.writeOutput(resultBB, writer);
                writer.printf("Tempo de execução (BB): %.4f ms\n", durationBB);

                writer.println("\n--- Resultado: Programação Dinâmica ---");
                FileHandler.writeOutput(resultDP, writer);
                writer.printf("Tempo de execução (PD): %.4f ms\n", durationDP);

                System.out.println("\nResultados salvos com sucesso em data/output.txt");
            } catch (Exception e) {
                System.err.println("Erro ao salvar arquivo de output: " + e.getMessage());
            }

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de entrada nao encontrado: " + inputFilePath);
        } catch (Exception e) {
            System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }
}
