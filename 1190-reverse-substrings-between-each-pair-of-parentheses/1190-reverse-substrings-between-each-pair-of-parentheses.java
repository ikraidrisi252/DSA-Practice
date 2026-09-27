class Solution {
    public String reverseParentheses(String s) {
        Deque<StringBuilder> stack = new ArrayDeque<>();
        stack.push(new StringBuilder());
        for(char c : s.toCharArray()){
            if(c == '('){
                stack.push(new StringBuilder());
            }
            else if(c == ')'){
                StringBuilder cur = stack.pop().reverse();
                stack.peek().append(cur);
            }
            else{
                stack.peek().append(c);
            }
        }
        return stack.pop().toString();
    }
}