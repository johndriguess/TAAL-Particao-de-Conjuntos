package io;

import models.PartitionResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe utilitária responsável por manipular a leitura de arquivos de entrada 
 * e a escrita/exibição dos resultados das operações de partição.
 */
public class FileHandler {

    /**
     * Lê os dados de entrada de um arquivo de texto. O primeiro número indica a 
     * quantidade de elementos, e os seguintes são os elementos do conjunto.
     *
     * @param filePath Caminho do arquivo a ser lido.
     * @return Um vetor de inteiros contendo os elementos do conjunto.
     * @throws FileNotFoundException Caso o arquivo especificado não seja encontrado.
     */
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

    /**
     * Imprime o resultado da partição no console (System.out).
     *
     * @param result O objeto PartitionResult contendo o resultado e os subconjuntos da partição.
     */
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

    /**
     * Escreve o resultado da partição em um objeto PrintWriter, geralmente para salvar em um arquivo.
     *
     * @param result O objeto PartitionResult contendo o resultado e os subconjuntos da partição.
     * @param writer O PrintWriter aberto para escrita do resultado.
     */
    public static void writeOutput(PartitionResult result, java.io.PrintWriter writer) {
        if (!result.isPossible()) {
            writer.println("NO");
        } else {
            writer.println("YES");
            writer.println(result.getSubset1().size());

            for (int i = 0; i < result.getSubset1().size(); i++) {
                writer.print(result.getSubset1().get(i) + (i == result.getSubset1().size() - 1 ? "" : " "));
            }
            writer.println();

            for (int i = 0; i < result.getSubset2().size(); i++) {
                writer.print(result.getSubset2().get(i) + (i == result.getSubset2().size() - 1 ? "" : " "));
            }
            writer.println();
        }
    }
}
