package base.core.leetcode.sliding_window;

public class LongestSubStrWihoutRepeatingChar {
    public static int lengthOfLongestSubstring(String str) {
        // Boundary Condition Check
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int[] charCounts = new int[256];
        int left = 0;
        int right = 0;
        int maxLength = 0;

        while (right < str.length()) {
            char rightChar = str.charAt(right);
            ++charCounts[rightChar];

            while (charCounts[rightChar] > 1) {
                char leftChar = str.charAt(left);
                --charCounts[leftChar];
                ++left;
            }

            maxLength = Math.max(maxLength, right - left + 1);
            ++right;
        }

        return maxLength;
    }
}
