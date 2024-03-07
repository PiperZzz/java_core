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

        for (int num : nums) {
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
}