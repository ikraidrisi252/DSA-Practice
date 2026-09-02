class Solution {
    public boolean uniformArray(int[] nums1) {
        int n= nums1.length;
        if(n <= 2) return true;

        int oddCnt = 0;
        for(int x : nums1){
            if((x & 1) != 0) oddCnt++;
        }
        return true;
    }
}