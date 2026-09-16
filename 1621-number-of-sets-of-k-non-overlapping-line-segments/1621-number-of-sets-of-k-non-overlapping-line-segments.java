class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007L;
        long N = n + k - 1;
        long R = 2 * k;

        // Calculate C(n + k - 1, 2 * k) % MOD
        long ans = 1;
        for (int i = 1; i <= R; i++) {
            ans = ans * (N - R + i) % MOD;
            ans = ans * power(i, MOD - 2, MOD) % MOD; // Modular Inverse
        }

        return (int) ans;
    }

    private long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp /= 2;
        }
        return res;
    }
}