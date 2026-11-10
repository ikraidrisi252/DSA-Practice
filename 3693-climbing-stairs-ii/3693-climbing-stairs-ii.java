class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        
        for (int j = 1; j <= n; j++) {
            int minCost = Integer.MAX_VALUE;
            for (int i = Math.max(0, j - 3); i < j; i++) {
                // Use costs[j - 1] instead of costs[j] because costs is 1-indexed
                int jumpCost = dp[i] + costs[j - 1] + (j - i) * (j - i);
                minCost = Math.min(minCost, jumpCost);
            }
            dp[j] = minCost;
        }
        
        return dp[n];
    }
}