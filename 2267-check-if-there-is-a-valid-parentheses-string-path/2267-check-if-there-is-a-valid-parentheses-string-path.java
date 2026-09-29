class Solution {
    private Boolean[][][] memo;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // A valid parentheses string must have an even length
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // The path must start with '(' and end with ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        // Fix: Set balance bound to (m + n) to avoid out-of-bounds on long paths
        memo = new Boolean[m][n][m + n];

        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int r, int c, int balance) {
        // Update balance for the current cell
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        // Balance cannot be negative or exceed remaining steps
        if (balance < 0 || balance > (m - r + n - c)) {
            return false;
        }

        // Reached the bottom-right cell
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        // Return cached result if already visited
        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }

        boolean canReach = false;

        // Move Down
        if (r + 1 < m) {
            canReach = canReach || dfs(grid, r + 1, c, balance);
        }

        // Move Right
        if (!canReach && c + 1 < n) {
            canReach = canReach || dfs(grid, r, c + 1, balance);
        }

        return memo[r][c][balance] = canReach;
    }
}