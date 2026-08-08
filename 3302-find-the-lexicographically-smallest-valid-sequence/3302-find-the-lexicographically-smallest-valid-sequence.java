class Solution {

    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        int[] suffix = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {

            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }

            suffix[i] = m - 1 - j;
        }

        int[] ans = new int[m];

        int p = 0;
        int q = 0;
        boolean changed = false;

        for (int k = 0; k < m; k++) {

            while (p < n) {

                // Character matches
                if (word1.charAt(p) == word2.charAt(q)) {
                    ans[k] = p;
                    p++;
                    q++;
                    break;
                }

                if (!changed) {

                    int remaining = m - q - 1;

                    if (suffix[p + 1] >= remaining) {

                        ans[k] = p;
                        p++;
                        q++;
                        changed = true;
                        break;
                    }
                }

                p++;
            }

            if (q != k + 1) {
                return new int[0];
            }
        }

        return ans;
    }
}

