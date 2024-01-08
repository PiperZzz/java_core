package base.core.leetcode.string;

public class LongestCommonPrefix {
    /**
     * Problem: Find the longest common prefix among a string array.
     * 
     * Approach: Use the horizontal scanning method. 
     * Start with the first string as the reference and compare each character with the corresponding character in other strings.
     * Stop comparing and return the prefix when a mismatched character is found or when the end of a string is reached.
     */
    public static String[] strs1 = {"flower", "flow", "flight"};
    public static String[] strs2 = {"dog", "car", "racecar"};
    public static String[] strs3 = {};

    public String longestCommonPrefix(String[] strs) {
        // Simplifying Assumptions: all strings are in lowercase

        // Corner Case: if the input is empty or has no strings, return an empty string
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Initial: take the first string as the initial prefix
        String prefix = strs[0];
        
        // Algorithm: Horizontal Scanning
        // i - String Array index from 0 to strs.length - 1
        // Time Complexity: O(n) where n is the total number of characters in the string array
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                // Time Complexity: O(m) where m is the length of the prefix
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return ""; //Exit Condition: If the prefix becomes empty, return an empty string.
                }
            }
        }

        // End Condition: If the prefix is not empty, return the longest common prefix
        return prefix;
    }
}
