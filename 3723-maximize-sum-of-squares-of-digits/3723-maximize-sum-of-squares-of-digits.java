class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9L * num) {
            return "";
        }

        int nines = sum / 9;
        int rem = sum % 9;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nines; i++) {
            sb.append('9');
        }
        if (rem > 0 || sb.length() == 0) {
            if (rem > 0) {
                sb.append(rem);
            }
        }
        while (sb.length() < num) {
            sb.append('0');
        }

        if (sb.length() > num) {
            return "";
        }

        return sb.toString();
    }
}