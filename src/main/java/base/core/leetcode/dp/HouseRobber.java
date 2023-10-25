package base.core.leetcode.dp;

public class HouseRobber {
    public static int rob(int[] nums) {
        // Boundary Condition Check: if the input array is null or has less than 1 element
        if (nums == null || nums.length < 1) {
            return 0;
        }
        
        int n = nums.length;
        if (n == 1) {
            return nums[0];  // If there's only one house, return its value as the maximum we can rob.
        }
        
        int[] dp = new int[n];  // Create an array to store the maximum amount we can rob at each house.
        dp[0] = nums[0];  // Initialize the first element of the array with the value of the first house.
        dp[1] = Math.max(nums[0], nums[1]);  // Initialize the second element with the maximum of the first two houses.
        
        for (int i = 2; i < n; i++) {
            // For each subsequent house, calculate the maximum amount we can rob considering whether to rob the current house or skip it.
            // We take the maximum of two options:
            // 1. The maximum amount we could rob from the previous house (dp[i-1]).
            // 2. The maximum amount we could rob from two houses before plus the current house's value (dp[i-2] + nums[i]).
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        
        return dp[n - 1];  // Return the maximum amount we can rob, which is stored in the last element of the dp array.
    }  
}
