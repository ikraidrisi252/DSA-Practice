class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        // Find the position of 0
        int zero = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        // Check whether the numbers increase
        // when moving in the given direction
        boolean right = true;
        boolean left = true;

        for (int i = 1; i < n; i++) {
            int prevRight = (zero + i - 1) % n;
            int currRight = (zero + i) % n;

            if (nums[prevRight] > nums[currRight]) {
                right = false;
            }

            int prevLeft = (zero - i + 1 + n) % n;
            int currLeft = (zero - i + n) % n;

            if (nums[prevLeft] > nums[currLeft]) {
                left = false;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (right) {
            ans = Math.min(ans, zero);
            ans = Math.min(ans, n - zero + 2);
        }

        if (left) {
            ans = Math.min(ans, zero + 2);
            ans = Math.min(ans, n - zero);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
