import java.util.Stack;

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        // Push -1 onto the stack as an initial base boundary
        st.push(-1);
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // Push index of opening parenthesis
                st.push(i);
            } else {
                // Pop the top index for a matching '('
                st.pop();
                
                if (st.isEmpty()) {
                    // If stack is empty, push current index as new base boundary
                    st.push(i);
                } else {
                    // Calculate valid substring length: current index - index at top of stack
                    maxLen = Math.max(maxLen, i - st.peek());
                }
            }
        }

        return maxLen;
    }
}