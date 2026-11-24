
class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            long product = 1;
            long gcd = 0;
            long lcm = 1;

            for (int j = i; j < n; j++) {
                long x = nums[j];

                product *= x;

                gcd = gcd(gcd, x);

                lcm = (lcm / gcd(lcm, x)) * x;

                if (product == gcd * lcm) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
