class Solution {

    static class Interval {
        int start;
        int end;
        int weight;
        int index;

        Interval(int start, int end, int weight, int index) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.index = index;
        }
    }

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    private Interval[] arr;
    private State[][] memo;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        // Sort by starting position
        Arrays.sort(arr, (a, b) -> {
            if (a.start != b.start)
                return Integer.compare(a.start, b.start);

            return Integer.compare(a.end, b.end);
        });

        memo = new State[n][5];

        State answer = dfs(0, 4);

        int[] result = new int[answer.indices.size()];

        for (int i = 0; i < answer.indices.size(); i++) {
            result[i] = answer.indices.get(i);
        }

        return result;
    }

    private State dfs(int i, int remaining) {

        if (i == arr.length || remaining == 0) {
            return new State(0, new ArrayList<>());
        }

        if (memo[i][remaining] != null) {
            return memo[i][remaining];
        }

        // Option 1: Skip current interval
        State skip = dfs(i + 1, remaining);

        // Option 2: Take current interval
        int next = findNext(i + 1, arr[i].end);

        State nextState = dfs(next, remaining - 1);

        List<Integer> takeIndices =
            new ArrayList<>(nextState.indices);

        takeIndices.add(arr[i].index);

        // We need lexicographical order
        Collections.sort(takeIndices);

        State take = new State(
            arr[i].weight + nextState.score,
            takeIndices
        );

        // Choose better state
        if (take.score > skip.score) {
            memo[i][remaining] = take;
        }
        else if (take.score < skip.score) {
            memo[i][remaining] = skip;
        }
        else {
            // Same score → lexicographically smaller
            if (compare(take.indices, skip.indices) < 0) {
                memo[i][remaining] = take;
            }
            else {
                memo[i][remaining] = skip;
            }
        }

        return memo[i][remaining];
    }

    // Find first interval whose start > current end
    private int findNext(int left, int end) {

        int right = arr.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid].start > end) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }

        return left;
    }

    // Lexicographical comparison
    private int compare(List<Integer> a, List<Integer> b) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}