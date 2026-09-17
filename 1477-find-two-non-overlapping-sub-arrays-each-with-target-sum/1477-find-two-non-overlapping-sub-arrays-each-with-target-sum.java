class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = n + 1;
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, INF);

        // dp[i] = min length of valid subarray in [0..i]
        int left = 0, sum = 0;
        int best = INF;
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            while (sum > target && left <= right) {
                sum -= arr[left++];
            }
            if (sum == target) {
                best = Math.min(best, right - left + 1);
            }
            dp[right] = best;
        }

        int ans = INF;
        left = 0;
        sum = 0;
        // second window + combine with dp
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            while (sum > target && left <= right) {
                sum -= arr[left++];
            }
            if (sum == target) {
                int curLen = right - left + 1;
                if (left > 0 && dp[left - 1]!= INF) {
                    ans = Math.min(ans, dp[left - 1] + curLen);
                }
            }
        }
        return ans == INF? -1 : ans;
    }
}
