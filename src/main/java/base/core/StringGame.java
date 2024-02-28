package base.core;

import java.util.HashMap;
import java.util.Map;

public class StringGame {
    public static void stringChar() {
        String str = "abcdefga";

        HashMap<Character, Integer> mapCharFreq = new HashMap<>();

        for (char c : str.toCharArray()) {
            mapCharFreq.put(c, mapCharFreq.getOrDefault(c, 0) + 1);
        }

        for (Map.Entry<Character, Integer> e : mapCharFreq.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }
}
