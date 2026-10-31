class Solution {
    private static final int MOD = 1_000_000_007;
    
    public int uniquePaths(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == 1 || grid[m-1][n-1] == 1) return 0;
        
        long[][] dp = new long[m][n];
        dp[0][0] = 1;
        
        // we need to process cells in increasing i+j because dest has larger sum
        // but dest of (i,j) can be (i+1,j+1) or further, so simple row-major with sum loop
        for (int sum = 0; sum <= m + n - 2; sum++) {
            for (int i = 0; i < m; i++) {
                int j = sum - i;
                if (j < 0 || j >= n) continue;
                if (dp[i][j] == 0) continue;
                if (i == m-1 && j == n-1) continue;
                
                // try RIGHT
                int[] rd = getDest(grid, i, j, 0);
                if (rd != null) {
                    dp[rd[0]][rd[1]] = (dp[rd[0]][rd[1]] + dp[i][j]) % MOD;
                }
                // try DOWN
                int[] dd = getDest(grid, i, j, 1);
                if (dd != null) {
                    dp[dd[0]][dd[1]] = (dp[dd[0]][dd[1]] + dp[i][j]) % MOD;
                }
            }
        }
        return (int) dp[m-1][n-1];
    }
    
    // dir 0 = RIGHT, 1 = DOWN
    // returns destination cell after all reflections, or null if goes out of bounds
    private int[] getDest(int[][] g, int i, int j, int dir) {
        int m = g.length, n = g[0].length;
        int r = i, c = j;
        
        while (true) {
            int nr = r + (dir == 1 ? 1 : 0);
            int nc = c + (dir == 0 ? 1 : 0);
            
            if (nr < 0 || nr >= m || nc < 0 || nc >= n) return null;
            
            if (g[nr][nc] == 0) {
                return new int[]{nr, nc};
            }
            // mirror cell -> reflect before entering
            // if we tried to move RIGHT into mirror, next dir = DOWN, and position stays at mirror cell for next attempt
            // if we tried DOWN into mirror, next dir = RIGHT
            r = nr;
            c = nc;
            dir = 1 - dir; // flip RIGHT<->DOWN
            // loop continues, will try to move from (r,c) with new dir to (r+1,c) or (r,c+1)
            // To avoid infinite loop (can't happen as r+c increases by 1 each iteration), bound iterations
            if (r + c > m + n) return null;
        }
    }
}
