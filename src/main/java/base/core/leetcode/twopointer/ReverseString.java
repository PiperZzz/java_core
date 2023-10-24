package base.core.leetcode.twopointer;

public class ReverseString {
    public String reverseString(String str) {
        // Boundary check

        char[] chars = str.toCharArray();
        // 双指针初始化，left从头部开始遍历，right从尾部开始遍历
        int left = 0;
        int right = str.length() - 1;
        
        // 当左右指针没有相遇时，执行循环体
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
