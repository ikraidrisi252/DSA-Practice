import java.util.*;

class Solution {
    private int[] newParent;
    private int[] subtreeSize;
    private List<Integer>[] origTree;
    private List<Integer>[] finalTree;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        origTree = new ArrayList[n];
        finalTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origTree[i] = new ArrayList<>();
            finalTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            origTree[parent[i]].add(i);
        }

        newParent = new int[n];
        newParent[0] = -1;
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        findNewParents(0, parent, s, lastSeen);

        for (int i = 1; i < n; i++) {
            finalTree[newParent[i]].add(i);
        }

        subtreeSize = new int[n];
        computeSizes(0);
        return subtreeSize;
    }

    private void findNewParents(int u, int[] parent, String s, int[] lastSeen) {
        int ch = s.charAt(u) - 'a';
        int prev = lastSeen[ch];

        if (u != 0) {
            newParent[u] = (prev != -1) ? prev : parent[u];
        }

        lastSeen[ch] = u;
        for (int v : origTree[u]) {
            findNewParents(v, parent, s, lastSeen);
        }
        lastSeen[ch] = prev;
    }

    private int computeSizes(int u) {
        int size = 1;
        for (int v : finalTree[u]) {
            size += computeSizes(v);
        }
        subtreeSize[u] = size;
        return size;
    }
}