package base.core.leetcode.sliding_window;

public class MaxProfit {
    public static int maxProfit(int[] prices) {
        // Boundary / Corner Case
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        // Init - minPrice set to the max value
        int minPrice = Integer.MAX_VALUE; // 
        // init - maxProfit set to 0
        int maxProfit = 0;

        for (int price : prices) { // 这种遍历方式维持了每一个价格出现的时间顺序，这点非常重要。因为只有这样才能保证最低股价在最大利润之前出现
            minPrice = Math.min(minPrice, price); // Record the all time low price
            maxProfit = Math.max(maxProfit, price - minPrice); // Max Profit 和 Current Price - Min Price 需要分开记录，更新最低股价并不会立即影响最大利润
        }

        return maxProfit;
    }
}
