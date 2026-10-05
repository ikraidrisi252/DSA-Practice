class Solution {
    public boolean checkIfPangram(String sentence) {
        boolean[] alp = new boolean[26];
        int count = 0;
        for(char c : sentence.toCharArray()){
            int idx = c - 'a';
            if(!alp[idx]){
                alp[idx] = true;
                count++;
                if(count==26) return true;
            }
        }
        return count==26;
    }
}