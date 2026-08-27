import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        generate("", 0, 0, n, result);

        return result;
    }

    void generate(String s, int open, int close, int n,
                  List<String> result) {

        // If we used all parentheses
        if (s.length() == 2 * n) {
            result.add(s);
            return;
        }

        // Add '(' if we still have some left
        if (open < n) {
            generate(s + "(", open + 1, close, n, result);
        }

        // Add ')' only if it won't make parentheses invalid
        if (close < open) {
            generate(s + ")", open, close + 1, n, result);
        }
    }
}