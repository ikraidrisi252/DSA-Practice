class Solution(object):
    def runningSum(self, nums):
        sum = 0
        for i in range(1,len(nums)):
            nums[i] = nums[i] + nums[i-1]
            
        return nums
        