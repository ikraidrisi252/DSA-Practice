class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find the closest x and y coordinates on the rectangle to the circle center
        int closestX = Math.max(x1, Math.min(xCenter, x2));
        int closestY = Math.max(y1, Math.min(yCenter, y2));
        
        // Calculate the distance from the circle center to this closest point
        int distanceX = xCenter - closestX;
        int distanceY = yCenter - closestY;
        
        int distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);
        
        // Return true if the distance is less than or equal to the radius squared
        return distanceSquared <= (radius * radius);
    }
}