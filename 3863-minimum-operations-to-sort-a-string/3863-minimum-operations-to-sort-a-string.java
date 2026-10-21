import java.util.Arrays;

class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        // Check if already sorted
        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;

        if (n == 2) return -1;
        
        char[] arr = s.toCharArray();
        char minChar = arr[0];
        char maxChar = arr[0];
        for (char c : arr) {
            minChar = (char) Math.min(minChar, c);
            maxChar = (char) Math.max(maxChar, c);
        }
        
        // Count frequencies
        int minCount = 0, maxCount = 0;
        for (char c : arr) {
            if (c == minChar) minCount++;
            if (c == maxChar) maxCount++;
        }

        if ((arr[0] == minChar && minCount < n) || (arr[n - 1] == maxChar && maxCount < n)) {
            return 1;
        }
        
        if (arr[0] == maxChar && maxCount == 1 && arr[n - 1] == minChar && minCount == 1) {
            return 3;
        }
        
        
        return 2;
    }
}