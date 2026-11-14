import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int minSum = Integer.MAX_VALUE;
        int n = nums.size();
        
        // Iterate over all possible lengths from l to r
        for (int len = l; len <= r; len++) {
            // Check all subarrays of the current length
            for (int i = 0; i <= n - len; i++) {
                int currentSum = 0;
                for (int j = i; j < i + len; j++) {
                    currentSum += nums.get(j);
                }
                
                // We only care about positive sums
                if (currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                }
            }
        }
        
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}