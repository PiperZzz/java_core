package base.core.leetcode.dp;

import java.util.Arrays;

public class CoinChange {
    public int minCoins(int[] coins, int amount) {
        // Boundary Condition Check
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }

        // 创建一个整数数组 dp，用于存储每个金额所需的最小硬币数量
        // dp[i] 表示金额 i 所需的最小硬币数量 - 这个值在计算过程中会不断动态更新
        // dp[amount] 就是我们要求的结果
        // dp 数组的长度为 amount + 1，因为 dp[0] 表示金额为 0 时所需的最小硬币数量
        int[] dp = new int[amount + 1];
    
        // 将 dp 数组的所有元素初始化为整数的最大值，表示无穷大
        Arrays.fill(dp, Integer.MAX_VALUE);
    
        // 初始化 dp 数组的第一个元素为0，因为凑成金额0不需要任何硬币
        dp[0] = 0;
    
        // 开始遍历硬币数组 coins 中的每一种硬币面值
        // 在遍历Coin的面值这第一层循环中，一开始会出现某些位置的MAX_VALUE无法被已知的最小组合硬币数量+1取代的情况
        for (int coin : coins) {
            // 在内层循环中，从当前硬币面值 coin 开始，遍历 dp 数组，直到目标金额 amount
            for (int i = coin; i <= amount; i++) {
                // 检查如果使用当前硬币 coin 后，剩余金额 i - coin 不是无穷大
                // 说明可以用之前的硬币组合凑成剩余金额 i - coin，那么 dp[i - coin] 就是凑成剩余金额 i - coin 的最小硬币数量
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    // 更新 dp[i]，将其设置为当前 dp[i] 和 dp[i - coin] + 1（使用当前硬币的最小硬币数量）中的较小值
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
    
        // 最后，返回 dp[amount] 的值，如果它仍然是无穷大，则表示无法找零，返回-1，否则返回最小硬币数量
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
    
}
