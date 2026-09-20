class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            // Position in reversed alphabet: 'a' -> 26, 'b' -> 25, ..., 'z' -> 1
            int reversedAlphabetPos = 26 - (s.charAt(i) - 'a');
            
            // Position in string is 1-indexed (i + 1)
            int stringPos = i + 1;

            totalSum += reversedAlphabetPos * stringPos;
        }

        return totalSum;
    }
}