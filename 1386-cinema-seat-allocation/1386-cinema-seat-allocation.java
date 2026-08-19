class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Set<Integer>> map = new HashMap<>();

        // Store reserved seats row-wise
        for (int[] seat : reservedSeats) {

            int row = seat[0];
            int col = seat[1];

            map.computeIfAbsent(row, k -> new HashSet<>()).add(col);
        }

        // Every empty row can have 2 families
        int answer = 2 * n;

        for (int row : map.keySet()) {

            Set<Integer> reserved = map.get(row);

            // Seats 2,3,4,5
            boolean left =
                    !reserved.contains(2) &&
                    !reserved.contains(3) &&
                    !reserved.contains(4) &&
                    !reserved.contains(5);

            // Seats 4,5,6,7
            boolean middle =
                    !reserved.contains(4) &&
                    !reserved.contains(5) &&
                    !reserved.contains(6) &&
                    !reserved.contains(7);

            // Seats 6,7,8,9
            boolean right =
                    !reserved.contains(6) &&
                    !reserved.contains(7) &&
                    !reserved.contains(8) &&
                    !reserved.contains(9);

            // Remove the assumed 2 families
            answer -= 2;

            if (left && right) {
                // Two non-overlapping families
                answer += 2;
            }
            else if (left || middle || right) {
                // Only one family can fit
                answer += 1;
            }
        }

        return answer;
    }
}