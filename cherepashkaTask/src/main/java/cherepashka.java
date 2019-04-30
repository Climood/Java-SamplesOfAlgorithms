import java.util.Random;

public class cherepashka {
    static int [][]matrix = null;

    static void initializateMatrix(int n){
        Random rnd=new Random();
        matrix = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=rnd.nextInt(100);
            }
        }
    }
    //-----------------------------------------------------------------------
    static void printMatrix(){
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    //-----------------------------------------------------------------------
    static int max(int a,int b){
        return a>b?a:b;
    }
    //-----------------------------------------------------------------------
    public static void main(String ...args){
        //////////////////////////////////// while variant :)
        /*
        initializateMatrix(4);
        printMatrix();
        int result;
        int n = matrix.length;
        int m = -1;
        //summing first column
        while( n-- > 1) {             // not include 0 index
            matrix[n-1][0] += matrix[n][0];
        }
        //summing last line
        while( m++ < matrix.length-2){ // not include last index
            matrix[matrix.length-1][m+1] += matrix[matrix.length-1][m];
        }
        n=matrix.length-2;
        m=1;
        while(true){
            if(m==matrix.length){
                m=1;
                n--;
            }
            matrix[n][m] += max(matrix[n][m-1],matrix[n+1][m]);
            m++;
            if(n == 0 && m ==matrix.length){
                break;
            }
        }
        printMatrix();
        System.out.println("Result = " + matrix[0][matrix.length-1]);
        */
        ////////////////////////// for variant (:

        initializateMatrix(4);
        printMatrix();
        //summing first line
        for(int i=0; i<matrix.length-1; i++){// not include last index
            matrix[matrix.length-1][i+1]+=matrix[matrix.length-1][i];
        }

        printMatrix();
        //summing first column
        for(int j=matrix.length-1; j>0; j--){// not include 0 index
            matrix[j-1][0]+=matrix[j][0];
        }
        printMatrix();
        for(int n=matrix.length-1;n>0;n--){
           for(int m=0;m<matrix.length-1;m++){
               matrix[n-1][m+1]+=max(matrix[n-1][m],matrix[n][m+1]);
           }
        }
        printMatrix();
        System.out.println("Result = " + matrix[0][matrix.length-1]);
    }
}
