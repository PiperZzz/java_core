package base.core.leetcode.two_pointers;

public class ReverseString {
    public String reverseString(String str) {
        // Boundary/Corner Condition Check - Revisit

        // Initialization - Convert the string to a character array
        char[] chars = str.toCharArray();
        // Initialization - Pointers, left starts from the head of the string, right starts from the tail of the string
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) { // When the left pointer is smaller than the right pointer (not meet), execute the loop body
            // Swap the characters of the left and right pointers
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            
            left++; // Move the left pointer
            right--; // Move the right pointer
        }

        return new String(chars); // Convert the character array back to a string
    }
}
