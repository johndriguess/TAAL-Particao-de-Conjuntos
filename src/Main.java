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
            PartitionResult result = solver.solve();
            FileHandler.printOutput(result);

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de entrada nao encontrado: " + inputFilePath);
        } catch (Exception e) {
            System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }
}
