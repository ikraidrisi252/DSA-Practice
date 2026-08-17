class Solution {

    int[][] dp;
    int[] prefix;

    public int stoneGameV(int[] stoneValue) {

        int n = stoneValue.length;

        dp = new int[n][n];

        prefix = new int[n + 1];

        // Prefix sum
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return solve(stoneValue, 0, n - 1);
    }

    private int solve(int[] nums, int left, int right) {

        // Only one stone
        if (left == right) {
            return 0;
        }

        // Already calculated
        if (dp[left][right] != 0) {
            return dp[left][right];
        }

        int answer = 0;

        // Try every split
        for (int split = left; split < right; split++) {

            int leftSum =
                prefix[split + 1] - prefix[left];

            int rightSum =
                prefix[right + 1] - prefix[split + 1];

            if (leftSum < rightSum) {

                answer = Math.max(
                    answer,
                    leftSum + solve(nums, left, split)
                );

            } else if (leftSum > rightSum) {

                answer = Math.max(
                    answer,
                    rightSum + solve(nums, split + 1, right)
                );

            } else {

                answer = Math.max(
                    answer,
                    Math.max(
                        leftSum + solve(nums, left, split),
                        rightSum + solve(nums, split + 1, right)
                    )
                );
            }
        }

        dp[left][right] = answer;

        return answer;
    }
}