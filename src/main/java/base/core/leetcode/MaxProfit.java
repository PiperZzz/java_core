package base.core.leetcode;

public class MaxProfit {
    public static int maxProfit(int[] prices) {
            int minPrice = Integer.MAX_VALUE; // 初始化最低股价为正无穷大
            int maxProfit = 0; // 初始化最大利润为0
            
            for (int i = 0; i < prices.length; i++) {
                if (prices[i] < minPrice) {
                    minPrice = prices[i]; // 更新最低股价
                } else if (prices[i] - minPrice > maxProfit) {
                    maxProfit = prices[i] - minPrice; // 更新最大利润
                }
            }
            
            return maxProfit;
        }
}
