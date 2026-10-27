class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];
        
        // Initialize the grid with obstacles ('#')
        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(grid[i], '#');
        }
        
        // Create the single valid path along the top row and right column
        for (int j = 0; j < n; j++) {
            grid[0][j] = '.';
        }
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }
        
        // Convert char[][] to String[]
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }
        
        return result;
    }
}