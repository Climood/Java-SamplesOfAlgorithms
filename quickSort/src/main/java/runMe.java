import java.util.Arrays;
import java.util.Random;

public class runMe {
    public static void main (String...args){
        Random rnd = new Random();
        int []arr=new int[200];
        arr = Arrays.stream(arr).map(s -> s+rnd.nextInt(100)).toArray();
        System.out.println(Arrays.toString(arr));
        quickSort sorter= new quickSort();
        sorter.recQuickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
        int i=1;
        while(true){
            if(arr[i]<arr[i-1]){
                System.out.println("неверно");
                break;
            }
            i++;
            if(i == arr.length) break;
        }
    }
}
