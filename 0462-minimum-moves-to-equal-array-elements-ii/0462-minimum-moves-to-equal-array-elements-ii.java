import java.util.Arrays;

class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int median = nums[n / 2]; // works for both odd/even
        
        long ans = 0; // use long to avoid overflow, return int
        for (int x : nums) {
            ans += Math.abs((long)x - median);
        }
        return (int) ans;
    }
}