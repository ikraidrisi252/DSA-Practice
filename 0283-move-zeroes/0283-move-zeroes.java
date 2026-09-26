class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0;
        int j = 0;
        int n = nums.length;
        
        while(j<n){
            if(nums[j]==0) j++;
            else{
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i++;
                    j++;
            }
        }
    }
}