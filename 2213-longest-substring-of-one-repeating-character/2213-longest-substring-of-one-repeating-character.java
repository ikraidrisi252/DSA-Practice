class Solution {

    class Node {

        char leftChar;
        char rightChar;

        int length;
        int prefix;
        int suffix;
        int best;

        Node(char leftChar, char rightChar,
             int length, int prefix, int suffix, int best) {

            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.length = length;
            this.prefix = prefix;
            this.suffix = suffix;
            this.best = best;
        }
    }

    Node[] tree;
    char[] arr;

    public int[] longestRepeating(String s,
                                  String queryCharacters,
                                  int[] queryIndices) {

        int n = s.length();

        arr = s.toCharArray();

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {

            int index = queryIndices[i];

            char ch = queryCharacters.charAt(i);

            arr[index] = ch;

            update(1, 0, n - 1, index);

            ans[i] = tree[1].best;
        }

        return ans;
    }

    private void build(int node, int left, int right) {

        if (left == right) {

            tree[node] =
                new Node(arr[left], arr[left],
                         1, 1, 1, 1);

            return;
        }

        int mid = (left + right) / 2;

        build(node * 2, left, mid);

        build(node * 2 + 1, mid + 1, right);

        tree[node] =
            merge(tree[node * 2],
                  tree[node * 2 + 1]);
    }

    private void update(int node,
                        int left,
                        int right,
                        int index) {

        if (left == right) {

            tree[node] =
                new Node(arr[index], arr[index],
                         1, 1, 1, 1);

            return;
        }

        int mid = (left + right) / 2;

        if (index <= mid) {

            update(node * 2,
                   left,
                   mid,
                   index);

        } else {

            update(node * 2 + 1,
                   mid + 1,
                   right,
                   index);
        }

        tree[node] =
            merge(tree[node * 2],
                  tree[node * 2 + 1]);
    }

    private Node merge(Node a, Node b) {

        int length = a.length + b.length;

        int prefix = a.prefix;

        int suffix = b.suffix;

        int best = Math.max(a.best, b.best);

        // Boundary characters are same
        if (a.rightChar == b.leftChar) {

            best = Math.max(
                best,
                a.suffix + b.prefix
            );

            // Entire left segment is same
            if (a.prefix == a.length) {

                prefix = a.length + b.prefix;
            }

            // Entire right segment is same
            if (b.suffix == b.length) {

                suffix = b.length + a.suffix;
            }
        }

        return new Node(
            a.leftChar,
            b.rightChar,
            length,
            prefix,
            suffix,
            best
        );
    }
}