class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int w1 = 0, w2 = 0;
        int i1 = 0, i2 = 0;
        while(w1<word1.length && w2<word2.length){
            if(word1[w1].charAt(i1) !=word2[w2].charAt(i2)){
                return false;
            }
            i1++;
            i2++;
            if(i1 == word1[w1].length()){
                w1++;
                i1 = 0;
            }
            if(i2 == word2[w2].length()){
                w2++;
                i2 = 0;
            }
        }
        return w1 == word1.length && w2 == word2.length;
    }
}