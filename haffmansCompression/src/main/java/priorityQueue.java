import java.util.LinkedList;

public class priorityQueue<typeOfObject> { //priority FIFO queue
    private Link<typeOfObject> first;
    public int numOfLinks = 0; //нужна для генерации древа Хаффмана, чтобы отследить, когда в очереди останется последнее древо(готовое)
    priorityQueue(){
        first = null;
    }
    //-------------------------------------------------------------------------------------
    Link insert(typeOfObject what,int priority){
         if(first == null){
             first = new Link<typeOfObject>(what,priority);
             System.out.println("Элемент со значением "+what+" и приоритетом "+priority+" добавлен в очередь.");
             numOfLinks++;
             return first;
         }
         Link prev = null;
         Link curr = first;
         while(curr !=null && priority > curr.priority){
             prev = curr;
             curr = curr.next;
         }
         if(prev == null){ //insert in the begining of queue
             first = new Link<typeOfObject>(what,priority);
             System.out.println("Элемент со значением "+what+" и приоритетом "+priority+" добавлен в очередь.");
             first.next = curr;
             numOfLinks++;
             return first;
         }else{
             prev.next = new Link<typeOfObject>(what,priority);
             System.out.println("Элемент со значением "+what+" и приоритетом "+priority+" добавлен в очередь.");
             prev.next.next = curr;
             numOfLinks++;
             return prev.next;
         }

    }

    //-------------------------------------------------------------------------------------
    Link pop(){
        if(first == null){
            System.out.println("Очередь пуста, взятие элемента не возможно!");
            return null;
        }
        Link tmp = first;
        first=first.next;
        System.out.println("Элемент со значением "+tmp.getValue()+" и приоритетом "+tmp.priority+" удален из очереди.");
        numOfLinks--;
        return tmp;
    }

    //-------------------------------------------------------------------------------------
    typeOfObject peek(){
        if(first == null){
            System.out.println("Очередь пуста, чтение элемента не возможно!");
            return null;
        }
        return first.getValue();
    }
    //-------------------------------------------------------------------------------------
    void showAllElementsInPriorityOrder(){
        Link<typeOfObject> curr = first;
        while(curr !=null){
            System.out.print("["+curr.getValue()+","+curr.priority+"] ");
            curr=curr.next;
        }
    }


}

//////////////////////////////////////////////////////////////////////////////////
class Link<typeOfObject> {
    Link<typeOfObject> next;
    int priority;
    private typeOfObject value;

    public typeOfObject getValue() {
        return value;
    }

    public void setValue(typeOfObject value) {
        this.value = value;
    }

    Link(typeOfObject value,int priority){
        this.value=value;
        this.priority=priority;
    }
}
