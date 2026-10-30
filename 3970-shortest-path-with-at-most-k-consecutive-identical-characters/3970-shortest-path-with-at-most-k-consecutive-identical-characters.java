import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list: u -> list of [v, weight]
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[] { edge[1], edge[2] });
        }

        // Priority queue stores states: [current_weight, current_node, consecutive_count]
        // Min-heap ordered by current_weight
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        // Distance table to keep track of minimum weight to reach (node, consecutive_count)
        // Since consecutive_count can be up to k, we use a 2D array: dist[node][count]
        long[][] dist = new long[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        // Start at node 0 with a run length of 1 for labels.charAt(0)
        pq.offer(new long[] { 0, 0, 1 });
        dist[0][1] = 0;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long w = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

            if (u == n - 1) {
                return (int) w;
            }

            if (w > dist[u][count]) {
                continue;
            }

            for (int[] neighbor : adj[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];

                // Calculate the new consecutive count for node v
                int nextCount = 1;
                if (labels.charAt(u) == labels.charAt(v)) {
                    nextCount = count + 1;
                }

                // If the run length exceeds k, this path is invalid
                if (nextCount > k) {
                    continue;
                }

                if (w + weight < dist[v][nextCount]) {
                    dist[v][nextCount] = w + weight;
                    pq.offer(new long[] { w + weight, v, nextCount });
                }
            }
        }

        return -1;
    }
}