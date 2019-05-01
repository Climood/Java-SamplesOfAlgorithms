import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class backpackTask {
    private static int targetWeight;
    private static int[] weigthsOfItems;
    private static int itemsWeight = 0;
    private static LinkedList<Integer> takedItems = new LinkedList<>();
    private static int currItem = 0;
    private static boolean answerFounded = false;
    private static int[] selectedItems;
    //---------------------------------------------------------------------------------------------------
    static void getTaskParametrs() throws IOException {
        //Input example:
        // 20                       :Target weight
        // 1 3 4 6 8 9              : weigths of items
        //Solution example:
        //35
        //11 7 3 9 10 15 7 8 3 4 1
        //Target weight = 35
        //weights of items = [11, 7, 3, 9, 10, 15, 7, 8, 3, 4, 1]
        //Выбранная последовательность: 11 7 3 9 4 1
        InputStreamReader inputStream= new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStream);
        targetWeight  = Integer.parseInt(reader.readLine());
        weigthsOfItems  = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
        reader.close();
    }

    //---------------------------------------------------------------------------------------------------
    static void recBackpacking(int currItem){
        while(currItem < weigthsOfItems.length) {
            itemsWeight += weigthsOfItems[currItem];
            takedItems.addLast(weigthsOfItems[currItem]);
            //System.out.println(" + "+ weigthsOfItems[currItem] + "   all: "+takedItems.toString() + " allweights ="+itemsWeight);
            if (itemsWeight == targetWeight) {
                System.out.print("Выбранная последовательность: ");
                for (int item : takedItems) {
                    System.out.print(item + " ");
                }
                System.out.println();
                targetWeight = -1;  //Чтобы остановить поиск решений и прекратить раскрутку рекурсий
                return;
            }else
            if (itemsWeight < targetWeight) {
                int insideCurrItem = currItem;
                while(insideCurrItem < weigthsOfItems.length -1) {
                    recBackpacking(insideCurrItem + 1);//Мало,смотрим дальше
                    insideCurrItem++;
                }
            }else
            if (itemsWeight > targetWeight) {
                itemsWeight -= weigthsOfItems[currItem];
                takedItems.removeLast();
                //System.out.println(" - "+ weigthsOfItems[currItem] + "   all: "+takedItems.toString() + " allweights ="+itemsWeight);
                return; //Много
            }
            itemsWeight -= weigthsOfItems[currItem];
            takedItems.removeLast();
            //System.out.println(" - "+ weigthsOfItems[currItem] + "   all: "+takedItems.toString() + " allweights ="+itemsWeight);
            currItem++;
        }
    }

    //---------------------------------------------------------------------------------------------------
    public static void main(String ...args) throws IOException {
        getTaskParametrs();
        System.out.println("Target weight = "+ targetWeight);
        System.out.println("weights of items = "+ Arrays.toString(weigthsOfItems));
        recBackpacking(0);
    }

}
