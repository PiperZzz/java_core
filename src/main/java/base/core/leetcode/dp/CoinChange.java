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

    // 如果面值组合是1和任意质数，那么就可以用贪心算法
    // 硬币面值包含必须1，且其余面值都是质数，两个条件缺一不可
    // 不然就会出现coins={2, 5} amount=6，却无法找零的情况
    // 或者coins={1, 3，4} amount=6，却无法得到最优解的情况
    public static Integer minCoinsGreedy(Integer[] coins, Integer amount) {
        // Boundary Condition Check
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }

        // 从大到小排序
        Arrays.sort(coins, (a, b) -> b - a );

        // 初始化硬币数量
        Integer coinCount = 0;
        
        // 初始化剩余金额
        Integer remainingAmount = amount;

        // 从大到小遍历硬币面值
        for (int coin : coins) {
            // 如果剩余金额大于等于当前硬币面值
            if (remainingAmount >= coin) {
                // 剩余金额除以当前硬币面值，得到的商就是所需当前硬币的数量
                coinCount += remainingAmount / coin;
                // 余数就是剩余金额
                remainingAmount %= coin;
                // 如果剩余金额为0，说明已经找零完成，返回硬币数量
                if (remainingAmount == 0) {
                    return coinCount;
                }
            }
        }
        
        // 由于硬币面值包含必须1，所以必然能找零，所以这个return永远不会被执行
        // 在coin的for循环内执行返回，是为节省时间
        return 0;
    }
    //DP算法的时间复杂度是O(n*amount)，空间复杂度是O(amount)，n是硬币面值的数量
    //贪心算法的时间复杂度是O(n)，空间复杂度是O(1)，n是硬币面值的数量，如果要算是排序的时间复杂度是O(nlogn)
    
}
