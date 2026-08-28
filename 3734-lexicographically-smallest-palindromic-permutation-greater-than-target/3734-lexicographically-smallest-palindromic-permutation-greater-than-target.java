class Solution {

    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Find middle character and validate palindrome possibility
        int oddCount = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                oddCount++;
                middle = (char) ('a' + i);
            }
        }

        if (oddCount > 1) {
            return "";
        }

        // Count characters needed for the first half
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = freq[i] / 2;
        }

        int halfLen = n / 2;

        /*
         * First try to construct a palindrome that follows target
         * as closely as possible.
         *
         * We greedily match target's first half.
         */
        int[] remaining = halfCount.clone();
        char[] answerHalf = new char[halfLen];

        for (int i = 0; i < halfLen; i++) {
            int need = target.charAt(i) - 'a';

            if (remaining[need] > 0) {
                answerHalf[i] = (char) ('a' + need);
                remaining[need]--;
            } else {
                // Cannot continue matching target.
                // Put the smallest character greater than target[i].
                int greater = -1;

                for (int c = need + 1; c < 26; c++) {
                    if (remaining[c] > 0) {
                        greater = c;
                        break;
                    }
                }

                if (greater != -1) {
                    answerHalf[i] = (char) ('a' + greater);
                    remaining[greater]--;

                    fillSmallest(answerHalf, i + 1, remaining);

                    return buildPalindrome(answerHalf, middle, n);
                }

                /*
                 * No greater character is available here.
                 * We need to backtrack and increase an earlier position.
                 */
                for (int j = i - 1; j >= 0; j--) {
                    int current = answerHalf[j] - 'a';

                    // Restore current character
                    remaining[current]++;

                    // Find next greater available character
                    int greaterEarlier = -1;

                    for (int c = current + 1; c < 26; c++) {
                        if (remaining[c] > 0) {
                            greaterEarlier = c;
                            break;
                        }
                    }

                    if (greaterEarlier != -1) {
                        answerHalf[j] = (char) ('a' + greaterEarlier);
                        remaining[greaterEarlier]--;

                        fillSmallest(answerHalf, j + 1, remaining);

                        return buildPalindrome(answerHalf, middle, n);
                    }
                }

                return "";
            }
        }

        // First half exactly matches target's first half.
        String candidate = buildPalindrome(answerHalf, middle, n);

        // Strictly greater
        if (candidate.compareTo(target) > 0) {
            return candidate;
        }

        /*
         * Candidate is <= target.
         * Find next lexicographical permutation of the first half.
         */
        for (int i = halfLen - 1; i >= 0; i--) {
            int current = answerHalf[i] - 'a';

            remaining[current]++;

            int greater = -1;

            for (int c = current + 1; c < 26; c++) {
                if (remaining[c] > 0) {
                    greater = c;
                    break;
                }
            }

            if (greater != -1) {
                answerHalf[i] = (char) ('a' + greater);
                remaining[greater]--;

                fillSmallest(answerHalf, i + 1, remaining);

                return buildPalindrome(answerHalf, middle, n);
            }
        }

        return "";
    }

    private void fillSmallest(char[] half, int index, int[] remaining) {
        int pos = index;

        for (int c = 0; c < 26; c++) {
            while (remaining[c] > 0) {
                half[pos++] = (char) ('a' + c);
                remaining[c]--;
            }
        }
    }

    private String buildPalindrome(char[] half, char middle, int n) {
        StringBuilder sb = new StringBuilder(n);

        for (char c : half) {
            sb.append(c);
        }

        if ((n & 1) == 1) {
            sb.append(middle);
        }

        for (int i = half.length - 1; i >= 0; i--) {
            sb.append(half[i]);
        }

        return sb.toString();
    }
}