import java.util.HashMap;
import java.util.Map;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long totalK = (long) k1 + k2;
        int n = nums1.length;
        
        // Map to store frequency of each absolute difference
        Map<Integer, Long> diffCount = new HashMap<>();
        int maxDiff = 0;
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            diffCount.put(diff, diffCount.getOrDefault(diff, 0L) + 1);
            if (diff > maxDiff) {
                maxDiff = diff;
            }
        }
        
        // Greedily reduce from maxDiff down to 1
        for (int d = maxDiff; d > 0 && totalK > 0; d--) {
            long count = diffCount.getOrDefault(d, 0L);
            if (count == 0) continue;
            
            long take = Math.min(totalK, count);
            totalK -= take;
            
            // Update counts: decrease difference d to (d - 1)
            diffCount.put(d, count - take);
            diffCount.put(d - 1, diffCount.getOrDefault(d - 1, 0L) + take);
        }
        
        // Calculate the final minimum sum of squared differences
        long result = 0;
        for (Map.Entry<Integer, Long> entry : diffCount.entrySet()) {
            long d = entry.getKey();
            long count = entry.getValue();
            result += count * d * d;
        }
        
        return result;
    }
}