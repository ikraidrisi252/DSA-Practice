class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
        long high = (long) coins[0] * k;

        for (int coin : coins) {
            high = Math.min(high, (long) coin * k);
        }

        while (low < high) {
            long mid = low + (high - low) / 2;

            if (count(mid, coins) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long count(long x, int[] coins) {
        long result = 0;
        int n = coins.length;

        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            int bits = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;

                    lcm = lcm(lcm, coins[i]);

                    if (lcm > x) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) {
                continue;
            }

            long value = x / lcm;

            if (bits % 2 == 1) {
                result += value;
            } else {
                result -= value;
            }
        }

        return result;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}