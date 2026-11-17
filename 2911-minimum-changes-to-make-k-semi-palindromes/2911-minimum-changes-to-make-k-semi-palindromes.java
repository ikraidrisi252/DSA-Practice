class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];

        // Step 1: Precompute cost to make s[i...j] a semi-palindrome (len >= 2)
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                cost[i][j] = len;

                for (int d = 1; d * d <= len; d++) {
                    if (len % d == 0) {
                        if (d < len) {
                            cost[i][j] = Math.min(cost[i][j], getChanges(s, i, j, d));
                        }
                        int d2 = len / d;
                        if (d2 < len) {
                            cost[i][j] = Math.min(cost[i][j], getChanges(s, i, j, d2));
                        }
                    }
                }
            }
        }

        // Step 2: DP to partition into k semi-palindromes
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            java.util.Arrays.fill(dp[i], 1000000);
        }
        dp[0][0] = 0;

        for (int j = 1; j <= k; j++) {
            for (int i = 2 * j; i <= n; i++) {
                // p <= i - 2 ensures substring s[p...i-1] has length at least 2
                for (int p = 2 * (j - 1); p <= i - 2; p++) {
                    dp[i][j] = Math.min(dp[i][j], dp[p][j - 1] + cost[p][i - 1]);
                }
            }
        }

        return dp[n][k];
    }

    private int getChanges(String s, int start, int end, int d) {
        int changes = 0;
        for (int offset = 0; offset < d; offset++) {
            int l = start + offset;
            int r = end - ((end - start - offset) % d);
            while (l < r) {
                if (s.charAt(l) != s.charAt(r)) {
                    changes++;
                }
                l += d;
                r -= d;
            }
        }
        return changes;
    }
}