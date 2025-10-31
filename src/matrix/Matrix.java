package matrix;

import java.util.Arrays;

public class Matrix {

    public static int medianOfRowWisedSortedMatrix(int[][] mat){
        int nr = mat.length;
        int nc = mat[0].length;

        int min = mat[0][0];
        int max = mat[0][nc-1];
        for(int row = 1; row<nr; row++){
            min = Math.min(min,mat[row][0]);
            max = Math.max(max,mat[row][nc-1]);
        }

        int medPos = (nr*nc + 1)/2;

        while(min < max){
            int mid = (min+max)/2;
            int midPos = 0;

            for(int row = 0; row<nr; row++){
                int pos = Arrays.binarySearch(mat[row],mid) + 1;
                midPos += Math.abs(pos);
            }

            if(midPos < medPos){
                min = mid+1;
            }
            else{
                max = mid;
            }
        }

        return min;
    }

    public static void printSnakePatternMatrix(int[][] mat){

        for(int row=0; row<mat.length; row++){
            for(int col=0;col<mat[row].length; col++){
                if(row%2 == 0){
                    System.out.print(mat[row][col]+" ");
                }else{
                    System.out.print(mat[row][mat[row].length - col - 1]+" ");
                }
            }
        }
    }

    public static void matrixBoundaryTraversal(int[][] mat){
        if(mat.length == 1){
            System.out.println(Arrays.toString(mat[0]));
        }else if(mat[0].length == 1){
            for(int row=0; row<mat.length; row++){
                System.out.print(mat[row][0]+" ");
            }
        }else{
            //print first row
            for(int col=0; col<mat[0].length; col++){
                System.out.print(mat[0][col]+" ");
            }

            // print right column except first and last row corner elements
            for(int row = 1; row<mat.length-1; row++){
                System.out.print(mat[row][mat[row].length-1] + " ");
            }

            // print last row reverse
            for(int col=mat[mat.length-1].length-1; col >= 0; col--){
                System.out.print(mat[mat.length-1][col] + " ");
            }

            // print left column reverse except first and last row corner elements
            for(int row = mat.length-2; row >= 1; row--){
                System.out.print(mat[row][0] + " ");
            }
        }
    }

    public static void transposeMatrix(int[][] mat){
        // way 1
//        for(int row = 0; row < mat.length; row++){
//            for(int col = row+1; col<mat[0].length; col++){
//                int tmp = mat[row][col];
//                mat[row][col] = mat[col][row];
//                mat[col][row] = tmp;
//            }
//        }
        // way2
        for(int row = 0; row < mat.length; row++){
            for(int col = 0; col<row; col++){
                int tmp = mat[row][col];
                mat[row][col] = mat[col][row];
                mat[col][row] = tmp;
            }
        }
    }

    public static void rotateBy90(int[][] mat){
        transposeMatrix(mat);

        int n = mat.length;

        // reversing colomns way 1
//        for(int row = 0; row<n/2; row++){
//            for(int col = 0; col<mat[row].length; col++){
//                int tmp = mat[row][col];
//                mat[row][col] = mat[n-row-1][col];
//                mat[n-row-1][col] = tmp;
//            }
//        }

        // reversing colomns way 2
        for(int col = 0; col<mat[0].length; col++){
            int low = 0;
            int high = mat.length-1;
            while(low < high){
                int tmp = mat[low][col];
                mat[low][col] =mat[high][col];
                mat[high][col] = tmp;

                low++;
                high--;
            }
        }
    }


    public static void spiralTraversalOfMatrix(int[][] mat){
        int n = mat.length;
        int m = mat[0].length;

        int top = 0;
        int right = m-1;
        int bottom = n-1;
        int left = 0;

        while(top <= bottom && left <= right){

            for(int col = left; col <= right; col++){
                System.out.print(mat[top][col] + " ");
            }
            top++;

            for(int row = top; row<=bottom; row++){
                System.out.print(mat[row][right] + " ");
            }
            right--;

            if(top<=bottom){
                for(int col = right; col >= left; col--){
                    System.out.print(mat[bottom][col] + " ");
                }
                bottom--;
            }

            if(left<=right){
                for(int row = bottom; row >= top; row--){
                    System.out.print(mat[row][left] + " ");
                }
                left++;
            }

        }
    }

    public static void searchInRowWiseSortedNColumnWiseSortedMatrix(int[][] mat, int x){
        // we can use top right corner or bottom left corner to start with
        // they both don't have same sorting directions row wise and column wise. one will be increasing N Other decreasing

        int n = mat.length;
        int m = mat[0].length;

        if(x < mat[0][0]) {
            System.out.println("not found");
            return;
        }
        if(x > mat[n-1][m-1]){
            System.out.println("not found");
            return;
        }

        // i'm going with topRight
        int top = 0;
        int right = m-1;

        while(top < n && right >= 0){
            if(mat[top][right] == x){
                System.out.println(top+","+right);
                return;
            }
            else if(mat[top][right] > x) right--;
            else top++;
        }

        System.out.println("not found");

    }

    public static void main(String[] args) {

        // Jagged array of user spcified row sizes

        int[][] jaggedArray = new int[3][]; // 3 rows are there. but noOfColumns in each row is not fixed yet -> jagged array

        for(int i = 0; i<3; i++){
            jaggedArray[i] = new int[i+1];
            for(int j = 0; j<jaggedArray[i].length; j++){
                jaggedArray[i][j] = i;
            }
        }

        System.out.println(Arrays.deepToString(jaggedArray));

        int[][] mat1 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        printSnakePatternMatrix(mat1);
        System.out.println();
        matrixBoundaryTraversal(mat1);
        System.out.println();

        int[][] mat2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        System.out.println(Arrays.deepToString(mat2));
        transposeMatrix(mat2);
        System.out.println(Arrays.deepToString(mat2));

        int[][] mat3 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        rotateBy90(mat3);
        for(int[] row: mat3){
            System.out.println(Arrays.toString(row));
        }
        System.out.println();

        int[][] mat4 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        for(int[] row: mat4){
            System.out.println(Arrays.toString(row));
        }
        spiralTraversalOfMatrix(mat4);
        System.out.println();

        int[][] mat5 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        searchInRowWiseSortedNColumnWiseSortedMatrix(mat5,13);

        int[][] mat10 = {{5,10,20,30,40},{1,2,3,4,6},{11,13,15,17,19}};
        System.out.println(medianOfRowWisedSortedMatrix(mat10));

    }
}
