class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        // Start with all bits set up to the highest bit needed for n - 1
        int k0 = Integer.highestOneBit(n) * 2 - 1;
        int k = k0;

        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                k &= nums[i];
            }
        }

        // If no elements were misplaced, return 0
        return (k == k0) ? 0 : k;
    }
}