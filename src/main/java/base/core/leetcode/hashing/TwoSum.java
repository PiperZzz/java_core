package base.core.leetcode.hashing;

import java.util.*;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        // Boundary Condition Check
        if (nums == null || nums.length <= 1) {
            throw new IllegalArgumentException("Invalid input array");
        }

        Map<Integer, Integer> map = new HashMap<>();
    
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        
        throw new IllegalArgumentException("No two sum solution");
    }
}