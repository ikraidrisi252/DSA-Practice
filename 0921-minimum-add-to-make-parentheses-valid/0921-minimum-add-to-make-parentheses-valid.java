class Solution {
    public int minAddToMakeValid(String s) {
            Stack<Character> st = new Stack<>();
            int n = s.length();
            int count = 0;
            for(int i = 0; i<n; i++){
                if(s.charAt(i) == '('){
                    st.push(s.charAt(i));
                }
                else if(s.charAt(i) == ')'){
                    if(st.size()>0 && st.peek() == '('){
                        st.pop();
                    }
                    else{
                        count++;
                    }
                }
                  
            }
            return st.size()+count;

        
    }
}