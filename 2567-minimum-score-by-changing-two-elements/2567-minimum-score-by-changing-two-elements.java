import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int changeTwoSmallest = nums[n - 1] - nums[2];
        int changeBothEnds = nums[n - 2] - nums[1];
        int changeTwoLargest = nums[n - 3] - nums[0];

        return Math.min(changeTwoSmallest, Math.min(changeBothEnds, changeTwoLargest));
    }
}