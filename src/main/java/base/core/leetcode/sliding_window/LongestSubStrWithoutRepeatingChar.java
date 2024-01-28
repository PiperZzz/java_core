package base.core.leetcode.sliding_window;

import java.util.HashMap;

public class LongestSubStrWithoutRepeatingChar {
    public static int lengthOfLongestSubstring(String str) {
        // Boundary/Corner Case
        if (str == null || str.isEmpty()) {
            return 0;
        }

        // Assume: all lower or upper case - revisit

        // Init - frequency map to store the count of each character
        HashMap<Character, Integer> charCounts = new HashMap<>();
        
        // Init - Pointers/Sliding Window left and right boundaries
        int left = 0; // left pointer starts from the head, it moves when repeating character is found
        int right = 0; // right pointer starts from the head, it moves each loop no matter what

        // Init - max length of the substring without repeating characters
        int maxLength = 0;

        while (right < str.length()) { // When the window is not out of bound, otherwise stop the loop
            // Expand the window
            char rightChar = str.charAt(right); // Get the current right boundary character
            charCounts.put(rightChar, charCounts.getOrDefault(rightChar, 0) + 1); // Update the frequency map - increase the count of the current character frequency by 1
            right++; // Move right pointer towards right boundary

            // Shrink the window
            if (charCounts.get(rightChar) > 1) { // When find a repeating character of which the count is greater than 1 
                // Get the current left boundary character which should also be the right boundary character
                charCounts.put(rightChar, 1); // Update the frequency map - reset the count of the repeacting charactet to 1
                left++; // Move left pointer towards right boundary
            }

            // Always pick up the current max length or the current window length, whichever is greater
            maxLength = Math.max(maxLength, right - left);
        }

        return maxLength;
    }
}
