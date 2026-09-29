class Solution {
    public int islandPerimeter(int[][] grid) {
        int p = 0;
        int row = grid.length;
        int col = grid[0].length;
        for(int i = 0; i<row; i++){
            for(int j = 0; j<col; j++){
                if(grid[i][j] == 1){
                    if(i == 0 || grid[i-1][j] == 0) p++;  //for top
                    if(i == row-1 || grid[i+1][j] == 0) p++;  //for bottom
                    if(j == 0 || grid[i][j-1] == 0) p++;    //for left
                    if(j == col-1 || grid[i][j+1] == 0) p++;   //for right
                }
            }
        }
        return p;
    }
}