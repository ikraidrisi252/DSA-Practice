import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        int[] requestCount = new int[k];
        
        for (int i = 0; i < arrival.length; i++) {
            int arrivalTime = arrival[i];
            int endTime = arrivalTime + load[i];
            
            // Free up servers whose tasks have finished by this arrival time
            while (!busy.isEmpty() && busy.peek()[0] <= arrivalTime) {
                available.add(busy.poll()[1]);
            }
            
            // If no servers are available, drop the request
            if (available.isEmpty()) {
                continue;
            }
            
            // Find the next available server starting from index (i % k)
            Integer serverId = available.ceiling(i % k);
            if (serverId == null) {
                serverId = available.first(); // Wrap around to the beginning
            }
            
            // Assign the request to this server
            available.remove(serverId);
            requestCount[serverId]++;
            busy.offer(new int[] { endTime, serverId });
        }
        
        // Find the maximum number of requests handled by any server
        int maxRequests = 0;
        for (int count : requestCount) {
            maxRequests = Math.max(maxRequests, count);
        }
        
        // Collect all servers that handled the maximum number of requests
        List<Integer> busiest = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                busiest.add(i);
            }
        }
        
        return busiest;
    }
}