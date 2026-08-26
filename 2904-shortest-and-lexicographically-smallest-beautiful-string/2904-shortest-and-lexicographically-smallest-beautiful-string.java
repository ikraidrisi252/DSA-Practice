class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();

        int left = 0;
        int ones = 0;

        String answer = "";

        for (int right = 0; right < n; right++) {

            if (s.charAt(right) == '1') {
                ones++;
            }

            // We have exactly k ones
            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            if (ones == k) {

                // Remove leading zeros
                while (s.charAt(left) == '0') {
                    left++;
                }

                String current = s.substring(left, right + 1);

                if (answer.equals("")
                        || current.length() < answer.length()
                        || (current.length() == answer.length()
                            && current.compareTo(answer) < 0)) {
                    answer = current;
                }
            }
        }

        return answer;
    }
}