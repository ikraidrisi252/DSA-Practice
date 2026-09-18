class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        java.util.Arrays.fill(first, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) first[c] = i;
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        for (int c = 0; c < 26; c++) {
            if (first[c] == -1) continue;
            int l = first[c];
            int r = last[c];
            // expand
            boolean valid = true;
            for (int j = l; j <= r; j++) {
                int cur = s.charAt(j) - 'a';
                if (first[cur] < l) { // extends left, invalid
                    valid = false;
                    break;
                }
                r = Math.max(r, last[cur]);
            }
            if (valid) intervals.add(new int[]{l, r});
        }

        // sort by end
        intervals.sort((a,b) -> a[1] - b[1]);

        List<String> ans = new ArrayList<>();
        int prevEnd = -1;
        for (int[] inter : intervals) {
            int l = inter[0], r = inter[1];
            if (l > prevEnd) {
                ans.add(s.substring(l, r + 1));
                prevEnd = r;
            }
        }
        return ans;
    }
}
