package base.core.leetcode;

import java.util.Arrays;

public class Anagram {
    public static boolean areAnagrams(String str1, String str2) {
        // Remove non-alphabet characters from the strings and convert them to lowercase
        str1 = str1.replaceAll("[^a-zA-Z]", "").toLowerCase();
        str2 = str2.replaceAll("[^a-zA-Z]", "").toLowerCase();
        
        // Check if the lengths of the strings are the same; if not, they can't be anagrams
        if (str1.length() != str2.length()) {
            return false;
        }
        
        // Convert the strings to character arrays and sort them
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);
        
        // Compare the sorted character arrays
        return Arrays.equals(charArray1, charArray2);
    }
}
