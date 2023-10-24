package base.core.leetcode.twopointer;

public class ReverseString {
    public String reverseString(String str) {
        // Boundary check

        char[] chars = str.toCharArray();
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            // 交换左右指针指向的字符
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            
            // 移动指针
            left++;
            right--;
        }
        
        // 将字符数组转换回字符串
        return new String(chars);
    }
    
}
