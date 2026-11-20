import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 0;

        for (int x : nums) {
            int incVal = x + 1;
            int incLen = dp.getOrDefault(x, 0) + 1;

            int sameVal = x;
            int sameLen = dp.getOrDefault(x - 1, 0) + 1;

            int updatedInc = Math.max(dp.getOrDefault(incVal, 0), incLen);
            int updatedSame = Math.max(dp.getOrDefault(sameVal, 0), sameLen);

            dp.put(incVal, updatedInc);
            dp.put(sameVal, updatedSame);

            maxLen = Math.max(maxLen, Math.max(updatedInc, updatedSame));
        }

        return maxLen;
    }
}