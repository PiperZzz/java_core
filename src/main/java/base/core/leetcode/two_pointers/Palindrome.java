package base.core.leetcode.two_pointers;

public class Palindrome {
    public boolean isPalindrome(String str) {
        // Boundary Condition Check - Revisit

        // Remove all non-alphanumeric characters and convert the string to lowercase or Make reasonable assumptions
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        
        // Initialization - pointers
        int left = 0; // left pointer starts from the head of the string
        int right = str.length() - 1; // right pointer starts from the tail of the string
        
        while (left < right) { // When the left pointer is smaller than the right pointer (not meet), execute the loop body
            if (str.charAt(left) != str.charAt(right)) {
                return false; // When the current characters of the left and right pointers are not equal, return false
            }
            left++; // Move the left pointer
            right--; // Move the right pointer
        }
        
        return true; // When the left pointer is equal to the right pointer (meet), return true
    }
}
