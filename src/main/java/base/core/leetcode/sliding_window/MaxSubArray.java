package base.core.leetcode.sliding_window;

public class MaxSubArray {
    public static int maxSubArray(int[] nums) {
        // Boundary/Corner Check
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        
        // 初始化两个变量：maxSum用于跟踪到目前为止找到的最大和，currentSum用于跟踪当前子数组的和。
        int maxSum = nums[0];
        int currentSum = nums[0];
        
        // 从数组的第二个元素（索引1）开始遍历。
        for (int i = 1; i < nums.length; i++) {
            // 对于每个元素，计算新的currentSum，取当前元素本身或当前元素与前一个currentSum之和的较大值。
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            
            // 更新maxSum，取当前maxSum和新的currentSum之间的较大值。
            maxSum = Math.max(maxSum, currentSum);
        }
        
        // 返回maxSum，其中包含了输入数组中最大连续子数组的和。
        return maxSum;
    }
}
