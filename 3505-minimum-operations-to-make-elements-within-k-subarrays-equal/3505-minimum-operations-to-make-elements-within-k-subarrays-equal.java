import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];
        SlidingMedian sm = new SlidingMedian();
        for (int i = 0; i < n; i++) {
            sm.add(nums[i]);
            if (i >= x) sm.remove(nums[i - x]);
            if (i >= x - 1) cost[i - x + 1] = sm.getCost();
        }

        long INF = (long) 4e18;
        long[][] dp = new long[k + 1][n + 1];
        for (int t = 1; t <= k; t++) Arrays.fill(dp[t], INF);
        // dp[0][*] = 0

        for (int i = 1; i <= n; i++) {
            for (int t = 0; t <= k; t++) {
                dp[t][i] = dp[t][i - 1]; // skip nums[i-1]
                if (t > 0 && i >= x) {
                    long cand = dp[t - 1][i - x] + cost[i - x];
                    if (cand < dp[t][i]) dp[t][i] = cand;
                }
            }
        }
        return dp[k][n];
    }

    // Sliding window median with sums - O(log x)
    static class SlidingMedian {
        PriorityQueue<Integer> low = new PriorityQueue<>(Collections.reverseOrder()); // max heap
        PriorityQueue<Integer> high = new PriorityQueue<>(); // min heap
        Map<Integer, Integer> delayed = new HashMap<>();
        long lowSum = 0, highSum = 0;
        int lowSize = 0, highSize = 0;

        void add(int num) {
            if (low.isEmpty() || num <= low.peek()) {
                low.offer(num); lowSum += num; lowSize++;
            } else {
                high.offer(num); highSum += num; highSize++;
            }
            rebalance();
        }

        void remove(int num) {
            delayed.merge(num, 1, Integer::sum);
            if (!low.isEmpty() && num <= low.peek()) {
                lowSum -= num; lowSize--;
            } else {
                highSum -= num; highSize--;
            }
            prune(low); prune(high);
            rebalance();
        }

        void rebalance() {
            // low size = high size or +1
            if (lowSize > highSize + 1) {
                int v = low.poll(); lowSum -= v; lowSize--;
                high.offer(v); highSum += v; highSize++;
                prune(low);
            } else if (lowSize < highSize) {
                int v = high.poll(); highSum -= v; highSize--;
                low.offer(v); lowSum += v; lowSize++;
                prune(high);
            }
        }

        void prune(PriorityQueue<Integer> pq) {
            while (!pq.isEmpty()) {
                int v = pq.peek();
                Integer cnt = delayed.get(v);
                if (cnt == null || cnt == 0) break;
                pq.poll();
                if (cnt == 1) delayed.remove(v);
                else delayed.put(v, cnt - 1);
            }
        }

        long getCost() {
            prune(low); prune(high);
            long median = low.peek();
            long left = median * lowSize - lowSum;
            long right = highSum - median * highSize;
            return left + right;
        }
    }
}