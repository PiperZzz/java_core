package base.core.leetcode.sliding_window;

import java.util.HashMap;

public class LongestSubStrWithoutRepeatingChar {
    public static int lengthOfLongestSubstring(String str) {
        // Corner Case Check
        if (str == null || str.isEmpty()) {
            return 0;
        }

        // Assume: The string contains all lower or upper case charaters

        // Init - Create a frequency map to store the count of each character
        HashMap<Character, Integer> charCounts = new HashMap<>();
        
        // Init - Pointers/Sliding Window left and right slides 
        int left = 0; // left pointer starts from the head of the string
        int right = 0; // right pointer starts from the head of the string

        // Init - max length of the substring without repeating characters
        int maxLength = 0;

        while (right < str.length()) { // When the window is not out of bound, execute the loop body
            // Expand the window
            char rightChar = str.charAt(right); // Get the current character on the right boundary of the window
            charCounts.put(rightChar, charCounts.getOrDefault(rightChar, 0) + 1); // Update the frequency map - if the character is not in the map, set the count to 1, otherwise increment the count
            right++; // Move the right pointer towards the right boundary of the window

            // Shrink the window
            while (charCounts.get(rightChar) > 1) { // When the current character on the right boundary is not unique anymore in the window, execute the loop body to shrink the window
                char leftChar = str.charAt(left); // Get the current character on the left boundary of the window which should also be the character on the right boundary of the window
                charCounts.put(leftChar, charCounts.get(leftChar) - 1); // Update the frequency map - reset the count of the character frquency to 1
                left++; // Move the left pointer towards the right boundary of the window
            }

            // Update the max length of the substring without repeating characters, always pick up the greater one of the current max length and the length of the current window
            maxLength = Math.max(maxLength, right - left);
        }

        return maxLength;
    }
}
