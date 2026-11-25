import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 100_003L;
    private static final long BASE2 = 100_019L;

    private long[] pow1, pow2;
    private long[] pref1, pref2;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        pow1 = new long[n + 1];
        pow2 = new long[n + 1];
        pref1 = new long[n + 1];
        pref2 = new long[n + 1];

        pow1[0] = pow2[0] = 1L;
        for (int i = 0; i < n; i++) {
            long val = nums[i] + 1L;
            pow1[i + 1] = (pow1[i] * BASE1) % MOD1;
            pow2[i + 1] = (pow2[i] * BASE2) % MOD2;
            pref1[i + 1] = (pref1[i] * BASE1 + val) % MOD1;
            pref2[i + 1] = (pref2[i] * BASE2 + val) % MOD2;
        }

        int low = 1, high = n, ans = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueOfLength(mid, n)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean hasUniqueOfLength(int len, int n) {
        Map<Long, Integer> freq = new HashMap<>();
        long p1 = pow1[len];
        long p2 = pow2[len];

        for (int i = 0; i + len <= n; i++) {
            int j = i + len;
            long h1 = (pref1[j] - (pref1[i] * p1) % MOD1 + MOD1) % MOD1;
            long h2 = (pref2[j] - (pref2[i] * p2) % MOD2 + MOD2) % MOD2;
            long key = (h1 << 32) ^ (h2 & 0xffffffffL);
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}