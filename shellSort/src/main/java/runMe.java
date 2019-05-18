import java.util.Arrays;

public class runMe {
    public static void main(String...args){
        shellSort shellSorter = new shellSort();
        int []mas={ 9,8,7,6,5,4,3,2,1,0 };
        shellSorter.shellSorting(mas);
        System.out.println(Arrays.toString(mas));
    }
}
