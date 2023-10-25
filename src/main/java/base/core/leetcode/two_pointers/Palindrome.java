package base.core.leetcode.two_pointers;

public class Palindrome {
    public boolean isPalindrome(String str) {
        // Boundary Condition Check

        // 删除非字母数字字符并将字符串转换为小写
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        
        // 使用双指针检查回文性质
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }    
}
