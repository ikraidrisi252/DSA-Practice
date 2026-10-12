import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[32];
        
        // Count the occurrences of each bit position across all numbers
        for (int num : nums) {
            for (int i = 0; i < 31; i++) {
                if ((num & (1 << i)) != 0) {
                    bitCount[i]++;
                }
            }
        }
        
        long MOD = 1_000_000_007;
        long totalSum = 0;
        
        // Greedily build the top k numbers
        for (int step = 0; step < k; step++) {
            long currentNum = 0;
            for (int i = 0; i < 31; i++) {
                if (bitCount[i] > 0) {
                    currentNum |= (1L << i);
                    bitCount[i]--;
                }
            }
            totalSum = (totalSum + (currentNum * currentNum) % MOD) % MOD;
        }
        
        return (int) totalSum;
    }
}