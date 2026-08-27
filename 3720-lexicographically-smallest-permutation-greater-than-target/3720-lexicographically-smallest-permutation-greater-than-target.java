class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        int[] cnt = new int[26];

        // Characters available in s
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        // Remove characters used by target
        for (char c : target.toCharArray()) {
            cnt[c - 'a']--;
        }

        // Number of characters whose count is negative
        int negative = 0;

        for (int i = 0; i < 26; i++) {
            if (cnt[i] < 0) {
                negative++;
            }
        }

        // Try to make target greater from the right side
        for (int i = n - 1; i >= 0; i--) {

            int current = target.charAt(i) - 'a';

            // Put target[i] back into available characters
            if (cnt[current] == -1) {
                negative--;
            }

            cnt[current]++;

            // Prefix target[0...i-1] must be possible
            if (negative == 0) {

                // Find the smallest character greater than target[i]
                for (int c = current + 1; c < 26; c++) {

                    if (cnt[c] > 0) {

                        StringBuilder ans = new StringBuilder();

                        // Keep prefix same as target
                        ans.append(target, 0, i);

                        // Make this position slightly larger
                        ans.append((char) ('a' + c));

                        cnt[c]--;

                        // Add remaining characters in sorted order
                        for (int j = 0; j < 26; j++) {
                            while (cnt[j] > 0) {
                                ans.append((char) ('a' + j));
                                cnt[j]--;
                            }
                        }

                        return ans.toString();
                    }
                }
            }
        }

        return "";
    }
}