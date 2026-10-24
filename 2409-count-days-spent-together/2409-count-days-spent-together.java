class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int a1 = calculateDayOfYear(arriveAlice);
        int l1 = calculateDayOfYear(leaveAlice);
        int a2 = calculateDayOfYear(arriveBob);
        int l2 = calculateDayOfYear(leaveBob);
        
        int latestArrival = Math.max(a1, a2);
        int earliestLeave = Math.min(l1, l2);
        
        // If the latest arrival is after the earliest leave, there is no overlap
        if (latestArrival > earliestLeave) {
            return 0;
        }
        
        return earliestLeave - latestArrival + 1;
    }
    
    private int calculateDayOfYear(String date) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int dayOfYear = day;
        for (int i = 0; i < month - 1; i++) {
            dayOfYear += daysInMonths[i];
        }
        
        return dayOfYear;
    }
}