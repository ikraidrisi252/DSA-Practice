class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        // if(matrix.length = 0){
        //     return result;
        // }

        int top = 0;
        int left = 0;
        int right =matrix[0].length-1;
        int bottom = matrix.length-1;

        while(top<=bottom && left<=right){
            // 1. Move right
            for(int col = left; col<=right; col++){
                result.add(matrix[top][col]);
            }
            top++;

            //2. move down

            for(int row = top; row<=bottom; row++){
                result.add(matrix[row][right]);
            }
            right--;

            //3. move left

            if(top<=bottom){
                for(int col = right; col>=left; col--){
                    result.add(matrix[bottom][col]);
                }
                bottom--;
            }
            

            // 4. Move up
            if(left<=right){
                for(int row = bottom; row>=top; row--){
                    result.add(matrix[row][left]);
                }
                left++;
            }
            
        }
        return result;
    }
}