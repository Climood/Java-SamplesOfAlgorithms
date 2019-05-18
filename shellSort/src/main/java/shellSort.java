import java.util.Arrays;

public class shellSort {

    void shellSorting(int []array){
        //System.out.println(Arrays.toString(array));
        int tmpKey;
        int h=1;
        while(h<= array.length/3){
            h = h*3 + 1;//get max h in intervals
        }
        while(h>0){//completing h-sorts (h ... 13,4,1)
            for(int i=h;i<array.length;i++){
                tmpKey = array[i];
                int j = i;
                while(j > h-1 && array[j-h] >= tmpKey){
                    array[j] = array[j-h];
                    j-=h;
                }
                array[j] = tmpKey;
                //System.out.println(Arrays.toString(array));
            }
            h = (h-1) / 3;
        }
    }
}
