class Solution {
    public int maxPalindromes(String s, int k) {

        int n = s.length();

        boolean[][] palindrome = new boolean[n][n];

        // Find all palindromic substrings
        for (int len = 1; len <= n; len++) {

            for (int i = 0; i + len <= n; i++) {

                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j) &&
                    (len <= 2 || palindrome[i + 1][j - 1])) {

                    palindrome[i][j] = true;
                }
            }
        }

        // dp[i] = maximum palindromes from i to end
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {

            // Don't choose a palindrome starting at i
            dp[i] = dp[i + 1];

            // Try every ending position
            for (int j = i + k - 1; j < n; j++) {

                if (palindrome[i][j]) {

                    dp[i] = Math.max(
                        dp[i],
                        1 + dp[j + 1]
                    );
                }
            }
        }

        return dp[0];
    }
}