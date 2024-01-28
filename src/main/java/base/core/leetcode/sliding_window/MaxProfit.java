package base.core.leetcode.sliding_window;

public class MaxProfit {
    public static int maxProfit(int[] prices) {
        // Boundary/Corner Case
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        // 初始化最低股价为正无穷大
        int minPrice = Integer.MAX_VALUE;
        // 初始化最大利润为0
        int maxProfit = 0;
        
        /* 这种遍历方式维持了每一个价格出现的时间顺序，这点非常重要
        因为只有这样才能保证最低股价在最大利润之前出现 */
        for (int price : prices) {
            // 更新最低股价
            minPrice = Math.min(minPrice, price);
            // 更新最大利润 
            maxProfit = Math.max(maxProfit, price - minPrice);
            // 更新最低股价并不会立即影响最大利润
        }
            
        return maxProfit;
    }
}
