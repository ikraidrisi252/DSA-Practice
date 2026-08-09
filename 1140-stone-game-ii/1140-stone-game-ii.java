class Solution {

    int[][] dp;
    int[] suffix;
    int n;

    public int stoneGameII(int[] piles) {

        n = piles.length;

        dp = new int[n][n + 1];
        suffix = new int[n + 1];


        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        return solve(0, 1, piles);
    }

    private int solve(int i, int M, int[] piles) {

        if (i >= n) {
            return 0;
        }

        if (2 * M >= n - i) {
            return suffix[i];
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int best = 0;

        for (int X = 1; X <= 2 * M; X++) {

            int newM = Math.max(M, X);

            
            int currentPlayer = suffix[i]
                    - solve(i + X, newM, piles);

            best = Math.max(best, currentPlayer);
        }

        dp[i][M] = best;

        return best;
    }
}
