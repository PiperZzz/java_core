package base.core.leetcode.dp;

import java.util.Arrays;

public class CoinChange {
    /* https://leetcode.com/problems/coin-change/
    You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
    Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
    You may assume that you have an infinite number of each kind of coin.
    Example 1:
    Input: coins = [1,2,5], amount = 11
    Output: 3
    Explanation: 11 = 5 + 5 + 1

    Example 2:
    Input: coins = [2], amount = 3
    Output: -1

    Example 3:
    Input: coins = [1], amount = 0
    Output: 0
    
    Constraints:

    1 <= coins.length <= 12
    1 <= coins[i] <= 231 - 1
    0 <= amount <= 104 */

    public int minCoinsByDP(int[] coins, int amountTarget) {
        // Boundary / Corner Case - Revisit
        if (coins == null || coins.length == 0 || amountTarget < 0) {
            return -1;
        }

        // Assumptions - Revisit

        /* Init - int array, index is targe amount, value is current min coins, which is dynamically updated after each loop
        When index reaches amountTarget, and all coin values have been traversed, the value of dp[amountTarget] is the result we want
        The length of the array is amount + 1, because dp[0] represents the minimum number of coins required when the amount is 0
        当index达到amountTarget的时候，且所有面值的硬币全部被遍历完，此时的dp[amountTarget]值才是我们要求的结果
        数组的长度为amount + 1，因为dp[0]表示金额为0时所需的最小硬币数量
         */
        int[] dp = new int[amountTarget + 1];
    
        // All elements of the array are initialized to the maximum value of integers, indicating that the state has not been updated. 
        // If this state is maintained until the end, it means that it is impossible to make change at this index amountTarget
        // Init - 数组的所有元素初始化为整数的最大值，表示未更新过的状态，如果这个状态维持到最后，说明以此处index为amountTarget的时候，无法找零
        Arrays.fill(dp, Integer.MAX_VALUE);
    
        // The first element of the array is 0, because no coins are needed to make up the amount 0
        // Init - 数组的第一个元素为0，因为凑成金额0不需要任何硬币
        dp[0] = 0;
    
        // 在遍历Coin的面值这第一层循环中，一开始会出现某些位置的MAX_VALUE无法被已知的最小组合硬币数量+1取代的情况
        // When traversing the face value of Coin in the first loop, there will be some positions where MAX_VALUE cannot be replaced by the known minimum combination of coins + 1
        for (int coin : coins) {// 外层循环：遍历面值（DP算法的硬币面值遍历不需要排序）// Outter Loop: Traverse the face value (DP algorithm's coin face value traversal does not require sorting)
            for (int indexDp = coin; indexDp <= amountTarget; indexDp++) {// 内层循环：数组的index从当前硬币面值Coin开始遍历到目标金额amountTarget // Inner Loop: The index of the array starts from the current coin face value Coin and traverses to the target amount amountTarget
                // 当前硬币面值为coin，随着dp的index不断增加，dp[indexDp - coin]的值是当少一个coin面值的硬币时，所需的最小硬币数量             
                // The current coin face value is coin, as the index of dp continues to increase, the value of dp[indexDp - coin] is the minimum number of coins required when one less coin face value is required
                if (dp[indexDp - coin] != Integer.MAX_VALUE) {// 如果dp[indexDp - coin]的值是无穷大，说明少一个coin面值的硬币时，无法找零，那么dp[indexDp]的值也是无穷大 // If the value of dp[indexDp - coin] is infinite, it means that when one less coin face value is required, change cannot be made, so the value of dp[indexDp] is also infinite
                    // 如果dp[indexDp - coin]的值不是无穷大，说明少一个coin面值的硬币时，可以找零，那么dp[indexDp]的值就是dp[indexDp - coin]的值加1
                    // If the value of dp[indexDp - coin] is not infinite, it means that when one less coin face value is required, change can be made, so the value of dp[indexDp] is the value of dp[indexDp - coin] plus 1
                    // 在遍历第一个面值的硬币时，dp[indexDp]的值一定会被初始化为dp[indexDp - coin]的值加1
                    // When traversing the first face value of the coin, the value of dp[indexDp] will definitely be initialized to the value of dp[indexDp - coin] plus 1
                    // 但是在后面其它面值遍历的过程中，dp[indexDp]的值可能会被更小的dp[indexDp - coin`]+1、[indexDp - coin``]+1...取代
                    // But in the process of traversing other face values later, the value of dp[indexDp] may be replaced by smaller dp[indexDp - coin`]+1, [indexDp - coin``]+1...
                    dp[indexDp] = Math.min(dp[indexDp], dp[indexDp - coin] + 1);
                }
            }
        }
    
        // 最后，返回dp[amountTarget]的值，如果它仍然是无穷大，则表示无法找零，返回-1，否则返回最小硬币数量
        // Finally, return the value of dp[amountTarget], if it is still infinite, it means that it is impossible to make change, return -1, otherwise return the minimum number of coins
        return dp[amountTarget] == Integer.MAX_VALUE ? -1 : dp[amountTarget];
    }

    /* 如果面值组合是1和任意质数(Prime Number)，那么就可以用贪心算法
    硬币面值包含必须1，且其余面值都是质数，两个条件缺一不可
    不然就会出现coins={2, 5} amount=6，却无法找零的情况
    或者coins={1, 3，4} amount=6，却无法得到最优解的情况 */
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
