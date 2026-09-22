class Solution {
    static class Node {
        int prod;
        int[] cnt; // cnt[r] = count of prefixes in this node's range with product % k == r

        Node(int k) {
            this.prod = 1;
            this.cnt = new int[k];
        }
    }

    private Node[] tree;
    private int n;
    private int k;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int idx = queries[q][0];
            int val = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // 1. Update element at index
            update(1, 0, n - 1, idx, val);

            // 2. Query range [start, n - 1]
            Node res = query(1, 0, n - 1, start, n - 1);

            // 3. Store result for x
            ans[q] = (res != null) ? res.cnt[x] : 0;
        }

        return ans;
    }

    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        Node res = new Node(k);
        res.prod = (left.prod * right.prod) % k;

        // Copy counts from left child
        for (int r = 0; r < k; r++) {
            res.cnt[r] = left.cnt[r];
        }

        // Add counts from right child offset by left node's total product
        for (int r = 0; r < k; r++) {
            if (right.cnt[r] > 0) {
                int newRem = (left.prod * r) % k;
                res.cnt[newRem] += right.cnt[r];
            }
        }

        return res;
    }

    private void build(int node, int start, int end, int[] nums) {
        tree[node] = new Node(k);
        if (start == end) {
            int rem = nums[start] % k;
            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        build(2 * node, start, mid, nums);
        build(2 * node + 1, mid + 1, end, nums);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            int rem = val % k;
            tree[node] = new Node(k);
            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }

        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private Node query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return null;
        }

        if (l <= start && end <= r) {
            return tree[node];
        }

        int mid = start + (end - start) / 2;
        Node leftRes = query(2 * node, start, mid, l, r);
        Node rightRes = query(2 * node + 1, mid + 1, end, l, r);

        return merge(leftRes, rightRes);
    }
}