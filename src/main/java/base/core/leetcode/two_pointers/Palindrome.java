package base.core.leetcode.two_pointers;

public class Palindrome {
    public boolean isPalindrome(String str) {
        // Boundary Condition Check - Revisit

        // Remove all non-alphanumeric characters and convert the string to lowercase or Make reasonable assumptions
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        
        // Initialization - pointers
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) { // When the left pointer is smaller than the right pointer or the two pointers meet, stop the loop
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
}
