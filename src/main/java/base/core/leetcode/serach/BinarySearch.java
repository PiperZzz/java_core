package base.core.leetcode.serach;

public class BinarySearch {
    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid; // 找到目标值
            } else if (nums[mid] < target) {
                left = mid + 1; // 缩小搜索范围到右半部分
            } else {
                right = mid - 1; // 缩小搜索范围到左半部分
            }
        }
        
        return -1; // 目标值不存在
    }    
}
