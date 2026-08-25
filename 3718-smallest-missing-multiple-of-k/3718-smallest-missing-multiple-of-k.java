import java.util.*;

class Solution {
    public int missingMultiple(int[] nums, int k) {
        // O(n) lookup
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }
        

        int multiple = k;
        while (true) {
            if (!set.contains(multiple)) {
                return multiple;
            }
            multiple += k;
            if (multiple < 0) { // overflowed
                break;
            }
        }
        return multiple;
    }

}