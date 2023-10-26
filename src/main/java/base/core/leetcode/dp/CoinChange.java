package base.core.leetcode.dp;

import java.util.Arrays;

public class CoinChange {
    public int minCoins(int[] coins, int amount) {
        // Boundary Condition Check
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }

        // 创建一个整数数组dp，用于存储每个金额所需的最小硬币数量
        // dp[i] - index i表示当目标金额是i的时候，而dp[i]的值是目前找到所需的最小硬币数量，dp[i]的值会在遍历每个面值的硬币后更新一次
        // dp[amount] 当index i达到目标amount的时候，且所有面值的硬币全部被遍历完，此时的dp[amount]值才是我们要求的结果
        // dp数组的长度为amount + 1，因为dp[0]表示金额为0时所需的最小硬币数量
        int[] dp = new int[amount + 1];
    
        // 将dp数组的所有元素初始化为整数的最大值，表示未更新过的状态，如果这个状态维持到最后，说明以此处index i为目标金额的时候，无法找零
        Arrays.fill(dp, Integer.MAX_VALUE);
    
        // 初始化dp数组的第一个元素为0，因为凑成金额0不需要任何硬币
        dp[0] = 0;
    
        // 开始遍历硬币数组coins中的每一种硬币面值，即外层循环，DP算法的硬币面值不需要排序
        // 在遍历Coin的面值这第一层循环中，一开始会出现某些位置的MAX_VALUE无法被已知的最小组合硬币数量+1取代的情况
        for (int coin : coins) {
            // 在内层循环中，dp的index从当前硬币面值coin开始，遍历dp数组，直到index到达目标金额amount
            for (int i = coin; i <= amount; i++) {
                // 当前硬币面值为coin，随着dp的index i不断增加，dp[i - coin]的值是当少一个coin面值的硬币时，所需的最小硬币数量
                // 如果dp[i - coin]的值是无穷大，说明少一个coin面值的硬币时，无法找零，那么dp[i]的值也是无穷大
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    // 如果dp[i - coin]的值不是无穷大，说明少一个coin面值的硬币时，可以找零，那么dp[i]的值就是dp[i - coin]的值加1
                    // 在遍历第一个面值的硬币时，dp[i]的值一定会被初始化为dp[i - coin]的值加1
                    // 但是在后面其它面值遍历的过程中，dp[i]的值可能会被更小的dp[i - coin‘]+1、[i - coin‘’]+1取代
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
    
        // 最后，返回dp[amount]的值，如果它仍然是无穷大，则表示无法找零，返回-1，否则返回最小硬币数量
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
    //贪心算法的时间复杂度是O(n)+O(nlog n)=O(nlog n)，空间复杂度是O(1)，n是硬币面值的数量，O(nlog n)是排序的时间复杂度
}
