class Solution {
    static final int MOD = 1000000007;
    
    // Segment Tree arrays
    long[] sum;
    long[] lazy;
    long[] sumSq;

    // Push lazy updates down the tree
    private void pushDown(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void apply(int node, int l, int r, long val) {
        int len = r - l + 1;
        sumSq[node] = (sumSq[node] + 2 * val % MOD * sum[node] % MOD + val * val % MOD * len) % MOD;
        sum[node] = (sum[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    // Range update: add val to all elements in range [ql, qr]
    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        pushDown(node, l, r);
        int mid = (l + r) / 2;
        update(2 * node, l, mid, ql, qr, val);
        update(2 * node + 1, mid + 1, r, ql, qr, val);
        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sumSq[node] = (sumSq[node * 2] + sumSq[node * 2 + 1]) % MOD;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        lazy = new long[4 * n];
        sumSq = new long[4 * n];

        java.util.Map<Integer, Integer> lastOccurrence = new java.util.HashMap<>();
        long totalSumSq = 0;
        long currentSumSq = 0;

        for (int j = 0; j < n; j++) {
            int prev = lastOccurrence.getOrDefault(nums[j], -1);
            // Update the range [prev + 1, j] by adding 1 to distinct counts
            update(1, 0, n - 1, prev + 1, j, 1);
            lastOccurrence.put(nums[j], j);

            // sumSq[1] holds the sum of squares of all subarrays ending at current index j
            currentSumSq = sumSq[1];
            totalSumSq = (totalSumSq + currentSumSq) % MOD;
        }

        return (int) totalSumSq;
    }
}