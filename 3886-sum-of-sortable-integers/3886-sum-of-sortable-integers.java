class Solution {

    public int sortableIntegers(int[] nums) {

        int n = nums.length;

        int[] prefMax = new int[n + 1];
        int[] suffMin = new int[n + 1];
        int[] prefDrop = new int[n];

        // 1. Calculate prefix maximum
        for (int i = 0; i < n; i++) {
            prefMax[i + 1] =
                Math.max(prefMax[i], nums[i]);
        }

        // 2. Calculate suffix minimum
        suffMin[n] = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            suffMin[i] =
                Math.min(nums[i], suffMin[i + 1]);
        }

        // 3. Count descending adjacent pairs
        for (int i = 1; i < n; i++) {
            prefDrop[i] = prefDrop[i - 1];

            if (nums[i - 1] > nums[i]) {
                prefDrop[i]++;
            }
        }

        int answer = 0;

        // 4. Check every divisor of n
        for (int k = 1; k <= n / k; k++) {

            if (n % k == 0) {

                if (isSortable(nums, prefMax,
                               suffMin, prefDrop, k)) {
                    answer += k;
                }

                int other = n / k;

                if (other != k &&
                    isSortable(nums, prefMax,
                               suffMin, prefDrop, other)) {
                    answer += other;
                }
            }
        }

        return answer;
    }

    private boolean isSortable(
            int[] nums,
            int[] prefMax,
            int[] suffMin,
            int[] prefDrop,
            int k) {

        int n = nums.length;

        for (int start = 0; start < n; start += k) {

            int end = start + k - 1;

            // Count descending pairs inside this block
            int drops =
                prefDrop[end] - prefDrop[start];

            // Check the circular boundary
            if (nums[end] > nums[start]) {
                drops++;
            }

            // Block must be circularly sortable
            if (drops > 1) {
                return false;
            }

            // Previous elements must not exceed
            // the elements in the remaining suffix
            if (prefMax[start] > suffMin[start]) {
                return false;
            }
        }

        return true;
    }
}