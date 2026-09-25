class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int max_profit = 0;
        int buy = prices[0];
        for(int i = 1; i<n; i++){
            if(prices[i]<buy){
                buy = prices[i];
            }
            else{
                if(max_profit < prices[i]-buy){
                    max_profit = prices[i]-buy;
                }
            }
        }
        return max_profit;
    }
}