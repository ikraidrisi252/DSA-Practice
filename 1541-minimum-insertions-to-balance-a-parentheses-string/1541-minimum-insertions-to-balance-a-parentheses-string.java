import java.util.Stack;

class Solution {
    public int minInsertions(String s) {
        Stack<Character> st = new Stack<>();
        int n = s.length();
        int i = 0;
        int count = 0;

        while (i < n) {
            if (s.charAt(i) == '(') {
                st.push(s.charAt(i));
                i++;
            } else { 
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    if (st.size() > 0) {
                        st.pop();
                    } else {
                        count++; 
                    }
                    i += 2;
                } else {
                    count++; 

                    if (st.size() > 0) {
                        st.pop(); 
                    } else {
                        count++;
                    }
                    i++;
                }
            }
        }
        if (st.size() > 0) {
            count += st.size() * 2;
        }

        return count;
    }
}