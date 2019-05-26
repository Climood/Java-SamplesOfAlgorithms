import java.util.Arrays;

public class quickSort {

    //-------------------------------------------------------------------------------------------
    void swap(int[] array,int firstIndex,int secondIndex){
        int tmp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = tmp;
    }

    //-------------------------------------------------------------------------------------------
    int partition(int[] array,int left,int right) { //return index centered median pivot
        int center = (left + right) / 2;
        //Далее сортируем 3 элемента для взятия их среднего элемента как опору (медиана из 3х)
        if(array[left] > array[center]) swap(array,left,center);
        if(array[left] > array[right]) swap(array,left,right);
        if(array[center] > array[right]) swap(array,center,right);
        swap(array,center,right-1); //ставим медианную опору в предпоследнюю ячейку (в последней всегда до этого значение больше него)
        int medianPivot = array[right-1];
        int leftPtr = left;
        int rightPtr = right-1;
        while (true) {
            while (array[++leftPtr] < medianPivot) {//из за ++ пропустим самый 1ый, но он точно < pivot (т.к ставили его в медиане)
            }
            while (array[--rightPtr] > medianPivot) {//из за -- пропустим сам пивот
            }
            if(leftPtr >= rightPtr) break;
            swap(array,leftPtr,rightPtr);
        }
        swap(array,leftPtr,right-1); //Меняем местами опору с крайним левым элементов правого подмножества (leftPtr т.к. после break цикла он будет указывать на крайныий левый элемент, больший опоры)
        return leftPtr; //возрвщаем индекс, на котором опора
    }

    //-------------------------------------------------------------------------------------------
    void recQuickSort(int[] array,int left,int right){
        int size = right - left + 1;
        if(size <= 9){
            insertionSort(array,left,right);
        }else{
            int pivotIndex = partition(array,left,right);
            recQuickSort(array,left,pivotIndex-1);
            recQuickSort(array,pivotIndex+1,right);
        }
    }

    //-------------------------------------------------------------------------------------------
    void insertionSort(int[] array,int left,int right){ //use for arrays which size <= 9
        int tmpKey;
        for(int i = left+1 ;i<=right ;i++ ) {
            int j = i;
            tmpKey = array[i];
            while (j > 0 && array[j - 1] > tmpKey) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = tmpKey;
        }
    }
}
