
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] freq = new int[n + 1];

        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] divisible = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                divisible[i] += freq[j];
            }
        }

        Arrays.sort(items, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        int ans = 0;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            int freeCount = divisible[factor] - 1;

            if (price >= 2L * minPrice) {
                break;
            }

            if (budget < price) {
                break;
            }

            int take = Math.min(freeCount, budget / price);

            ans += 2 * take;
            budget -= take * price;
        }

        ans += budget / minPrice;

        return ans;
    }
}
