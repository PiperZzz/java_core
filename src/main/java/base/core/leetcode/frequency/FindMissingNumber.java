package base.core.leetcode.frequency;

import java.util.*;

public class FindMissingNumber {
    // 前提：数组中的数字是从0到n，且只有一个数字缺失
    // 如果数字不会出现重复，那么可以直接用求和公式，然后减去数组中所有元素的和，得到的就是缺失的数字
    public int findMissingNumber(int[] nums) {
        // Boundary Condition Check
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }

    // 如果题目中没有说明数字不会重复出现，需要先过滤掉重复的数字
    public int findMissingNumber2(int[] nums) {
        Set<Integer> setSortedNums = new TreeSet<>();
        
        for (int num : nums) {
            setSortedNums.add(num);
        }

        for (int i = 0; i <= setSortedNums.size() + 1; i++) {
            if (!setSortedNums.contains(i)) {
                return i;
            }
        }
        
        return -1;
    }

    // 还有一种XOR解法，但逻辑不太好理解
    public int findMissingNumber3(int[] nums) {
        int xorSum = 0;
        for (int i = 0; i < nums.length; i++) {
            xorSum ^= i;
            xorSum ^= nums[i];
        }
        xorSum ^= nums.length;
        return xorSum;
    }
}
