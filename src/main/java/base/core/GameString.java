package base.core;

import java.util.HashMap;

public class GameString {
    public static void stringChar() {
        String str = "abcde\"fgaA ; \\";

        HashMap<Character, Integer> mapCharFreq = new HashMap<>();

        for (char c : str.toCharArray()) {
            mapCharFreq.put(c, mapCharFreq.getOrDefault(c, 0) + 1);
        }

        mapCharFreq.forEach((k, v) -> System.out.println(k + " " + v));
    }
}