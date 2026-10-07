import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;

        // Step 1: Calculate the minimum number of '(' and ')' to remove
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--; // Matched with a previous '('
                } else {
                    right++; // Unmatched ')'
                }
            }
        }

        List<String> result = new ArrayList<>();
        backtrack(s, 0, left, right, result);
        return result;
    }

    private void backtrack(String s, int start, int left, int right, List<String> result) {
        // Base case: no more invalid brackets left to remove
        if (left == 0 && right == 0) {
            if (isValid(s)) {
                result.add(s);
            }
            return;
        }

        for (int i = start; i < s.length(); i++) {
            // Avoid duplicate strings at the same recursion depth
            if (i > start && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }

            // Remove unmatched '('
            if (left > 0 && s.charAt(i) == '(') {
                backtrack(s.substring(0, i) + s.substring(i + 1), i, left - 1, right, result);
            }

            // Remove unmatched ')'
            if (right > 0 && s.charAt(i) == ')') {
                backtrack(s.substring(0, i) + s.substring(i + 1), i, left, right - 1, result);
            }
        }
    }

    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) return false;
            }
        }
        return count == 0;
    }
}