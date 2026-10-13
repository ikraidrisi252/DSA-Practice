import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        
        // Square each element to work directly with positive squared values
        long[] squares = new long[n];
        for (int i = 0; i < n; i++) {
            long val = nums[i];
            squares[i] = val * val;
        }
        
        // Sort the squared values in ascending order
        Arrays.sort(squares);
        
        long totalScore = 0;
        int half = n / 2;
        
        // Subtract the first half (smaller values)
        for (int i = 0; i < half; i++) {
            totalScore -= squares[i];
        }
        
        // Add the second half (larger values)
        for (int i = half; i < n; i++) {
            totalScore += squares[i];
        }
        
        return totalScore;
    }
}