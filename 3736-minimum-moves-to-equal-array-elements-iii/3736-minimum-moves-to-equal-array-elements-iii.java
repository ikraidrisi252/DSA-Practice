class Solution {
    public int minMoves(int[] nums) {
        int max = nums[0];
        for(int num: nums){
            if(num>max){
                max = num;
            }
        }
        int total_moves = 0;
        for(int num :nums){
            total_moves += (max-num);
        }
        return total_moves;
    }
}