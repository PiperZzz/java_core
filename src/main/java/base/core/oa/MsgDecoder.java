package base.core.oa;

import java.io.*;
import java.util.TreeMap;

public class MsgDecoder {
    public static void msgDecode() {
        // transfer the input number-word format elment as key-value pair into a TreeMap which is sorted by key in ascending order
        TreeMap<Integer, String> wordMap = readInputFromFile("coding_qual_input.txt"); // The input file is in the same directory as this file and renamed it accordingly

		// Boundary check
        if (wordMap.isEmpty()) {
            System.out.println("No input found.");
            return;
        }

		int currentNumber = 1; // The number linked to the last word in each line of the pyramid, starting from 1 
        int increment = 2; // The increment to find the next currentNumber, starting from 2

		while (currentNumber <= wordMap.size()) { // Boundary check
            System.out.print(wordMap.get(currentNumber) + " ");
            currentNumber += increment;
            increment += 1; // increment increases by 1 each time according to the pyramid rule
        }
    }

    private static TreeMap<Integer, String> readInputFromFile(String filename) {
        TreeMap<Integer, String> wordMap = new TreeMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    int number = Integer.parseInt(parts[0]);
                    String word = parts[1];
                    wordMap.put(number, word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordMap;
    }
}
