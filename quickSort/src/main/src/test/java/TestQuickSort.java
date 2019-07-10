import com.sun.javafx.tools.packager.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
// Test
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

public class TestQuickSort {
    private quickSort sort;
    private Random rnd;
    private static Logger LOG = Logger.getLogger(TestQuickSort.class.getName());

    @Before
    public void createQuick(){
        sort = new quickSort();
        rnd = new Random();
        LOG.info("load Random and quickSort");
    }

    @Test
    public void testWithSimpleArray(){
        int []arr=new int[200];
        arr = Arrays.stream(arr).map(s -> s+rnd.nextInt(100)).toArray();
        int[] testArr = arr.clone();

        sort.recQuickSort(testArr,0,testArr.length-1);
        Arrays.sort(arr);

        Assertions.assertArrayEquals(arr,testArr);
    }

    @Test
    public void testWithNegative(){
        int []arr=new int[200];
        arr = Arrays.stream(arr).map(s -> s-rnd.nextInt(100)).toArray();
        int[] testArr = arr.clone();

        sort.recQuickSort(testArr,0,testArr.length-1);
        Arrays.sort(arr);

        Assertions.assertArrayEquals(arr,testArr);
    }
}
