class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        char[][] grid = new char[m][n];

        int sx = 0, sy = 0;
        int [][] litterId = new int[m][n];
        for (int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                litterId[i][j] = -1;
            }
        }
        int L = 0;
        for(int i = 0; i<m; i++){
            for(int j=0; j<n; j++){
                grid[i][j] = classroom[i].charAt(j);
                if(grid[i][j] == 'S'){
                    sx = i;
                    sy = j;
                }
                if(grid[i][j] == 'L'){
                    litterId[i][j] = L++;
                }
            }
        }
        if(L==0) return 0;
        int allMask = (1<<L)-1;

        int[][][] best = new int[m][n][1 << L]; 
        for(int i = 0; i<m; i++){
            for(int j=0; j<n; j++){
                java.util.Arrays.fill(best[i][j], -1);
            }
        }
        Deque<int[]> q = new ArrayDeque<>();
        int startMask = 0;
        q.offer(new int[]{sx, sy, energy, startMask, 0});
        best[sx][sy][startMask] = energy;

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], e = cur[2], mask = cur[3], steps = cur[4];

            if(mask == allMask) return steps;

            if(e==0){
                if(grid[x][y]!='R') continue;
                e = energy;
            }
            for(int[] d: dirs){
                int nx = x + d[0];
                int ny = y + d[1];
                if(nx<0 || ny<0 || nx>=m || ny>=n) continue;
                if(grid[nx][ny] == 'X') continue;
                int ne = e-1;
                int nmask = mask;
                if(litterId[nx][ny] != -1){
                    nmask = mask | (1 << litterId[nx][ny]);
                }
                int storeE = ne;
                if(grid[nx][ny] == 'R') storeE = energy;

                if (best[nx][ny][nmask] >= storeE) continue;

                if(storeE > best[nx][ny][nmask]){
                    best[nx][ny][nmask] = storeE;
                    q.offer(new int[]{nx, ny, storeE, nmask, steps+1});
                }
            }
        }
        return -1;
    }
}