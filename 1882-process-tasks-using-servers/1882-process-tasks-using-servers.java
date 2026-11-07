import java.util.PriorityQueue;
import java.util.Arrays;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];
        
        // Min-heap for free servers: sorted by weight, then by index
        // Format: [weight, index]
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        
        // Min-heap for busy servers: sorted by release time, then weight, then index
        // Format: [releaseTime, weight, index]
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[2], b[2]);
        });
        
        // Initialize free servers
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }
        
        long currentTime = 0;
        
        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);
            
            // If no servers are free, fast-forward time to when the next server becomes free
            if (freeServers.isEmpty() && !busyServers.isEmpty()) {
                currentTime = Math.max(currentTime, busyServers.peek()[0]);
            }
            
            // Release all servers that have finished their tasks by currentTime
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                int[] freed = busyServers.poll();
                // freed[1] is weight, freed[2] is index
                freeServers.offer(new int[]{freed[1], freed[2]});
            }
            
            // Assign the current task to the best available free server
            int[] server = freeServers.poll();
            int weight = server[0];
            int index = server[1];
            
            ans[j] = index;
            busyServers.offer(new int[]{(int)currentTime + tasks[j], weight, index});
        }
        
        return ans;
    }
}