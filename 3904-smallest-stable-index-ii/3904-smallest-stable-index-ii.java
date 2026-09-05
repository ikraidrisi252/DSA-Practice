class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] suffixMin = new int[n];
        suffixMin[n-1] = nums[n-1];
        for(int i=n-2; i>=0; i--){
            suffixMin[i] = Math.min(nums[i], suffixMin[i+1]);
        }

        int prefMax = Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            prefMax = Math.max(prefMax, nums[i]);
            if(prefMax-suffixMin[i]<= k) return i;
        }
        return -1;

    }
}