class Solution {
    public int scoreOfParentheses(String s) {
        int score = 0;
        int depth = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
                // If we hit a core "()", its value is 2^depth
                if (s.charAt(i - 1) == '(') {
                    score += 1 << depth; // equivalent to 2^depth
                }
            }
        }

        return score;
    }
}