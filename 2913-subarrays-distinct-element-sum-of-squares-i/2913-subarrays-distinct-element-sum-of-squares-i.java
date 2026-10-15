import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int totalSum = 0;
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            Set<Integer> distinctSet = new HashSet<>();
            for (int j = i; j < n; j++) {
                distinctSet.add(nums.get(j));
                int distinctCount = distinctSet.size();
                totalSum += distinctCount * distinctCount;
            }
        }
        
        return totalSum;
    }
}