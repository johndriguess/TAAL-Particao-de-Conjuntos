package app;
import core.PartitionSolver;
import io.FileHandler;
import models.PartitionResult;

import java.io.FileNotFoundException;

public class Main {
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

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de entrada nao encontrado: " + inputFilePath);
        } catch (Exception e) {
            System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }
}
