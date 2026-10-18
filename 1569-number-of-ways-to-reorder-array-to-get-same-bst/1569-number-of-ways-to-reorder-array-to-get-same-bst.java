import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        // Precompute combinations using Pascal's Triangle
        comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        return (int) (ways(list) - 1 + MOD) % MOD;
    }

    private long ways(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }

        long leftWays = ways(left);
        long rightWays = ways(right);

        int leftSize = left.size();
        int rightSize = right.size();

        // Combinations: ways to merge left and right subtrees
        long ans = comb[leftSize + rightSize][leftSize];
        ans = (ans * leftWays) % MOD;
        ans = (ans * rightWays) % MOD;

        return ans;
    }
}