import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freeCount = new int[n];

        for (int i = 0; i < n; i++) {
            int fi = items[i][0];
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % fi == 0) {
                    cnt++;
                }
            }
            freeCount[i] = cnt;
        }

        final int NEG_INF = -1_000_000_000;
        // dp0[b]: max items at budget b before activating current item
        // dp1[b]: max items at budget b after activating current item
        int[] dp0 = new int[budget + 1];
        int[] dp1 = new int[budget + 1];
        Arrays.fill(dp0, NEG_INF);
        Arrays.fill(dp1, NEG_INF);
        dp0[0] = 0;

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int[] nextDp0 = new int[budget + 1];
            int[] nextDp1 = new int[budget + 1];
            Arrays.fill(nextDp0, NEG_INF);
            Arrays.fill(nextDp1, NEG_INF);

            // Option 1: Don't buy item i at all (carry forward best from previous item)
            for (int b = 0; b <= budget; b++) {
                int bestPrev = Math.max(dp0[b], dp1[b]);
                if (bestPrev > nextDp0[b]) {
                    nextDp0[b] = bestPrev;
                }
            }

            // Option 2: Activate item i (first copy gives 1 + freeCount[i] items)
            if (price <= budget) {
                for (int b = 0; b + price <= budget; b++) {
                    int bestPrev = Math.max(dp0[b], dp1[b]);
                    if (bestPrev != NEG_INF) {
                        int cand = bestPrev + 1 + freeCount[i];
                        if (cand > nextDp1[b + price]) {
                            nextDp1[b + price] = cand;
                        }
                    }
                }
            }

            // Option 3: Buy extra copies of item i (unbounded within current item, +1 item each)
            if (price <= budget) {
                for (int b = 0; b + price <= budget; b++) {
                    if (nextDp1[b] != NEG_INF) {
                        int cand = nextDp1[b] + 1;
                        if (cand > nextDp1[b + price]) {
                            nextDp1[b + price] = cand;
                        }
                    }
                }
            }

            dp0 = nextDp0;
            dp1 = nextDp1;
        }

        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            ans = Math.max(ans, Math.max(dp0[b], dp1[b]));
        }
        return ans;
    }
}