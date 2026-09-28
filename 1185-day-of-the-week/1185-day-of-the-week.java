class Solution {
    public String dayOfTheWeek(int day, int month, int year) {
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int totalDays = 0;

        // Count days for previous years starting from 1971
        for (int i = 1971; i < year; i++) {
            totalDays += isLeapYear(i) ? 366 : 365;
        }

        // Adjust February for the given year if it's a leap year
        if (isLeapYear(year)) {
            daysInMonth[1] = 29;
        }

        // Count days for previous months in the current year
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonth[i];
        }

        // Add remaining days
        totalDays += day - 1;

        // Jan 1, 1971 was Friday (index 5: Sunday=0, Monday=1, ..., Friday=5)
        return days[(totalDays + 5) % 7];
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}