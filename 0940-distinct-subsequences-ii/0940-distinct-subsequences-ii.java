class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1000000007;
        long[] dp = new long[26];
        long total = 0;

        for(char ch : s.toCharArray()){
            int index = ch - 'a';
            long newSub = (total + 1)%MOD;
            total = (total + newSub - dp[index] + MOD) % MOD;
            dp[index] = newSub;
        } 
        return (int) total;
    }
}