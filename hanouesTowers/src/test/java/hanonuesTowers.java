class sterzhen{
    private int[] disks;
    private int index;

    char trueName; //for debug

    int getIndex(){
        return index;
    }
    sterzhen(int size){
        disks = new int[size];
        index=-1;
    }
    //-----------------------------------------------------------------------------------------------
    int peekDisk(){
        if(index == -1){
            System.out.println("Стержень пуст, смотреть нечего милорд!");
            return -1;
        }else{
            return disks[index];
        }

    }
    //-----------------------------------------------------------------------------------------------
    int getDisk(){
        if(index == -1){
            System.out.println("Стержень пуст, брать нечего милорд!");
            return -1;
        }else{
            return disks[index--];
        }

    }
    //-----------------------------------------------------------------------------------------------
    boolean putDisk(int disk){
        if(index == disks.length - 1){
            System.out.println("Стержень полон! умерьте амбиции!");
            return false;
        }else{
            disks[++index] = disk;
            return true;
        }
    }
    //-----------------------------------------------------------------------------------------------
    void showSterzhen(){
        int tmpIndex = index;
        while (tmpIndex != -1)
        System.out.println(disks[tmpIndex--]);
    }

}
////////////////////////////////////////////////////////////////////////////////////////////////////////
public class hanonuesTowers {
    private sterzhen sterzhenA;
    private sterzhen sterzhenB;
    private sterzhen sterzhenC;
    int numberOfDisk=0;

    hanonuesTowers(int size){
        //maximum number of elements on стержнях(перевести?)
        numberOfDisk = size;
        sterzhenA=new sterzhen(size);
        sterzhenA.trueName = 'A';
        sterzhenB=new sterzhen(size);
        sterzhenB.trueName = 'B';
        sterzhenC=new sterzhen(size);
        sterzhenC.trueName = 'C';
        int tmpIndex = size;
        while(tmpIndex != 0){
            sterzhenA.putDisk(tmpIndex--); //Заполняем стержень А дисками (тупо [size,size-1, ... , 1])
        }
    }
    void showSterzhenA(){
        sterzhenA.showSterzhen();
    }

    void showSterzhenB(){
        sterzhenB.showSterzhen();
    }

    void showSterzhenC(){
        sterzhenC.showSterzhen();
    }
    //-----------------------------------------------------------------------------------------------
    //всегда снимать можем только верхний стержень, поэтому указывать - какой именно - неуместно
    void moveDisk(sterzhen fromSterzhen,sterzhen toSterzhen){ //Рекурсия сама сделает так, что мы не будем пытаться пихнуть большие диски на меньшие, можно убрать проверки
         /*int tmp = fromSterzhen.peekDisk();
         if(tmp < toSterzhen.peekDisk()) { //тут в самом начале, при пустом С вернет tmp< 999
             tmp = fromSterzhen.getDisk();
             toSterzhen.putDisk(tmp);
         }else{
             System.out.println("Нельзя помещать большие диски на меньшие");
             return;
         }
         */
         int tmp=fromSterzhen.getDisk();
         toSterzhen.putDisk(tmp);
    }

    //-----------------------------------------------------------------------------------------------
    void completeTask(){
        recCompleteTask(numberOfDisk,sterzhenA,sterzhenB,sterzhenC); //Смотрим верхнее значение стержня А
    }
    //-----------------------------------------------------------------------------------------------
    void recCompleteTask(int numberOfDisk,sterzhen A,sterzhen B,sterzhen C){ //numberOfDisk показывает, сколько дисков в текущей подзадаче
        if(numberOfDisk == 1){ // в текущей подзадачи перемещаем всего 1 диск => можно спокойно перемещать
            System.out.println("Диск "+A.peekDisk()+" со стержня "+A.trueName+" перемещен на стержень "+C.trueName);
            moveDisk(A,C); //Остался последний диск на А, перемещаем его на С
        }else {
            recCompleteTask(numberOfDisk -1,A, C, B);
            System.out.println("Диск "+A.peekDisk()+" со стержня "+A.trueName+" перемещен на стержень "+C.trueName);
            moveDisk(A,C);
            recCompleteTask(numberOfDisk -1,B, A, C);
        }
    }
}
