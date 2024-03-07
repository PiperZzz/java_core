package base.core.leetcode.hashing;

import java.util.*;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        // Boundary Condition Check
        if (nums == null || nums.length <= 1) {
            return new int[] {}; // 如果数组为空或者长度小于等于1，则返回一个空数组
        }

        // 用一个HashMap来存储目标差值和数组中所对应的数，差值是Key，原数是Value
        Map<Integer, Integer> mapComplementNum = new HashMap<>();

        for (int num : nums) { // 如果不需要返回索引，可以用foreach，甚至用Set来代替Map
            if (mapComplementNum.containsKey(num)) {
                // 如果num已经存在于map的key中，则说明这个num之前已经以complement的身份出现过
                return new int[]{mapComplementNum.get(num), num}; // 那么这个Entry的Value和Key就是答案，循环结束
            }

            // 计算当前数num和目标数target的差值
            int complement = target - num; 

            // 如果差值complement不存在于map的key中，则添加到map的Key，当前数num添加为对应的Value
            mapComplementNum.put(complement, num); // 这步的作用是遍历过的差值会立即以Key的身份出现在循环的第一步检查中 - Line 16
        }
        
        return new int[] {}; // 如果没有找到答案，则返回一个空数组
    }

    public static int[] twoSumIndex(int[] nums, int target) {
        if (nums == null || nums.length <= 1) {
            return new int[] {};
        }
    
        Map<Integer, Integer> mapNumIndex = new HashMap<>(); // HashMap用来存储数组中的数(Key)和对应的索引(Value)
    
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            if (mapNumIndex.containsKey(complement)) {
                return new int[]{mapNumIndex.get(complement), i}; // 返回差值和当前数的索引
            }
            
            mapNumIndex.put(nums[i], i); // 将当前数和索引添加到map中
        }
        
        return new int[] {};
    }

    public static int[] twoSumSet(int[] nums, int target) {
        if (nums == null || nums.length <= 1) {
            return new int[] {};
        }

        Set<Integer> setComplement = new HashSet<>();
        for (int num : nums) {
            int complement = target - num; 
            if (setComplement.contains(complement)) {
                return new int[]{complement, num};
            }
            setComplement.add(num);
        }
        
        return new int[] {};
    }
}