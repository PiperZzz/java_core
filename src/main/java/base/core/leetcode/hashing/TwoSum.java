package base.core.leetcode.hashing;

import java.util.*;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        // Boundary Condition Check
        if (nums == null || nums.length <= 1) {
            throw new IllegalArgumentException("Invalid input array");
        }

        // 用一个HashMap来存储数组中的元素，num是Key，num的index是Value
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                // 如果差值complement存在于map的key中，则说明这个complement之前已经以某一个num的身份出现过
                return new int[]{map.get(complement), i}; // 返回答案数组

            }

            // 如果差值complement不存在于map的key中，则将当前num添加到map中
            map.put(nums[i], i);
        }
        
        throw new IllegalArgumentException("No two sum solution");
    }
}