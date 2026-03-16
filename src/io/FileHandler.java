package io;

import models.PartitionResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {

    public static int[] readInput(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        if (!scanner.hasNextInt()) {
            scanner.close();
            return new int[0];
        }

        int n = scanner.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            if (scanner.hasNextInt()) {
                numbers[i] = scanner.nextInt();
            }
        }

        scanner.close();
        return numbers;
    }

    public static void printOutput(PartitionResult result) {
        if (!result.isPossible()) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            System.out.println(result.getSubset1().size());
            
            for (int i = 0; i < result.getSubset1().size(); i++) {
                System.out.print(result.getSubset1().get(i) + (i == result.getSubset1().size() - 1 ? "" : " "));
            }
            System.out.println();
            
            for (int i = 0; i < result.getSubset2().size(); i++) {
                System.out.print(result.getSubset2().get(i) + (i == result.getSubset2().size() - 1 ? "" : " "));
            }
            System.out.println();
        }
    }
}
