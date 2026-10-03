class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int totalNumbers = n * n;
        int xorAll = 0;

        // XOR all elements in the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                xorAll ^= grid[i][j];
            }
        }

        // XOR all numbers from 1 to n^2
        for (int i = 1; i <= totalNumbers; i++) {
            xorAll ^= i;
        }

        // Get rightmost set bit
        int mask = xorAll & (-xorAll);

        int firstGroup = 0;
        int secondGroup = 0;

        // Divide grid numbers into 2 groups based on mask
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((grid[i][j] & mask) != 0) {
                    firstGroup ^= grid[i][j];
                } else {
                    secondGroup ^= grid[i][j];
                }
            }
        }

        // Divide 1 to n^2 into 2 groups based on mask
        for (int i = 1; i <= totalNumbers; i++) {
            if ((i & mask) != 0) {
                firstGroup ^= i;
            } else {
                secondGroup ^= i;
            }
        }

        // Check which one is the repeated number
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == firstGroup) {
                    return new int[]{firstGroup, secondGroup};
                }
            }
        }

        return new int[]{secondGroup, firstGroup};
    }
}