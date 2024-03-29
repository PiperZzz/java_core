package base.core.leetcode.string;

import java.util.Arrays;

public class Anagram {
    public static boolean areAnagrams(String str1, String str2) {
        // Boundary/Corner Condition Check
        if (str1 == null || str2 == null) {
            return false;
        }

        // If the lengths of the strings are not same, they can't be anagrams
        if (str1.length() != str2.length()) {
            return false;
        }

        // Remove non-alphabet characters from the strings and convert them to lowercase or Make reasonable assumptions
        str1 = str1.replaceAll("[^a-zA-Z]", "").toLowerCase();
        str2 = str2.replaceAll("[^a-zA-Z]", "").toLowerCase();
                
        // Convert the strings to character arrays and sort them
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);
        
        // Compare the sorted character arrays
        return Arrays.equals(charArray1, charArray2);
    }
}
