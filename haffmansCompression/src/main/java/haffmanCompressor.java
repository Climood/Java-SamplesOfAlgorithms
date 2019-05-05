
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class haffmanCompressor {
    String inputMessage = null;
    String compressedMessage = null;
    String uncompressedMessage = null;



    private HashMap<Character,String> haffmansCodingTable; //Кодовая таблица хаффмана,
    // решил сделать ее под алфавит сообщения, а не ASCII или UNICODE
    // => для правильного расжатия сообщения таблица должна быть заранее сохранена,
    // т.к. нужна та же, что была сгенерирована при сжатии

    public HashMap<Character, String> getHaffmansCodingTable() {
        return haffmansCodingTable;
    }

    public void setHaffmansCodingTable(HashMap<Character, String> haffmansCodingTable) {
        this.haffmansCodingTable = haffmansCodingTable;
    }

    //------------------------------------------------------------------------------------
    String getMessage() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder input = new StringBuilder();
        String current;
        while( (current = br.readLine()) != null){
            if(current.trim().isEmpty()) {
                break;
            }
            input.append(current);
        }
        inputMessage = input.toString();
        return inputMessage;
    }

    //------------------------------------------------------------------------------------
    HashMap getChastotMap(String message){
        HashMap<Character,Integer> chastotMap = new HashMap<>();
        for(char c:message.toCharArray()){  //new Java 8 method, it looks good :)
            chastotMap.merge(c, 1, (a, b) -> a + b); //Проверяет есть ли значение с таким ключом, если нету, создает новое, если есть обновляет значение (частоту)
        }
        return chastotMap;
    }

    //------------------------------------------------------------------------------------
    priorityQueue queueOfCharTrees(HashMap chastotMap){
        priorityQueue<binaryTree> queueOfCharTrees=new priorityQueue<>();
        Iterator<HashMap.Entry<Character,Integer>> iterator = chastotMap.entrySet().iterator(); //создаем итератор по проходам пар значений символ-частота
        while(iterator.hasNext()){
            HashMap.Entry<Character,Integer> tmpEntry = iterator.next(); //Временная сущность для пары
            binaryTree<Character> tmpTree = new binaryTree<>(); //Временная ссылка на очередное древо
            tmpTree.insert(tmpEntry.getValue(),tmpEntry.getKey()); //создаем первый узел, который станет корнем
            queueOfCharTrees.insert(tmpTree,tmpEntry.getValue()); //Вставляем очередное древо в приоритетную очередь, приоритетом будет частота символа
        }
        return queueOfCharTrees; //Возвращаем заполненную очередь с деревьями по 1 узлу(корню)
    }

    //------------------------------------------------------------------------------------
    binaryTree getHaffmansTree(priorityQueue queueOfCharTrees){
        while(queueOfCharTrees.numOfLinks != 1){
            binaryTree tmpLeftTree = (binaryTree) queueOfCharTrees.pop().getValue();
            binaryTree tmpRightTree = (binaryTree) queueOfCharTrees.pop().getValue();
            binaryTree tmpMergedTree = new binaryTree<>();
            tmpMergedTree = tmpMergedTree.mergeTreeWithoutSorting(tmpLeftTree,tmpRightTree);
            System.out.println("Древо со значением корня "+tmpMergedTree.root.getValue()+" и значенем частоты "+ tmpMergedTree.root.getKey()+" длбавлено в очередь.");
            queueOfCharTrees.insert(tmpMergedTree,tmpMergedTree.root.getKey());
        }
        return (binaryTree) queueOfCharTrees.pop().getValue();
    }

    //------------------------------------------------------------------------------------
    //Метод будет рекурсивно проходит по всем узлам дерева, записывая путь и при встрече символа,
    // который есть в сообщении,
    // он будет возвращать соответсвующую пару (символ,путь) для записи в таблицу кодов хаффмана
    void recGetHaffmansCodeForSymbols(Node currNodeOfHaffmansTree, String path){
        if( (Character)currNodeOfHaffmansTree.getValue() != Character.MAX_VALUE){ //Charater.MAX_VALUE я вставлял как value в связующие узлы древа хаффмана
            haffmansCodingTable.put( (Character)currNodeOfHaffmansTree.getValue() , path );
            return;
        }
        recGetHaffmansCodeForSymbols(currNodeOfHaffmansTree.leftChild,path+"0");
        recGetHaffmansCodeForSymbols(currNodeOfHaffmansTree.rightChild,path+"1");
    }

    //------------------------------------------------------------------------------------
    String compressMessage(binaryTree haffmansTree){
        haffmansCodingTable = new HashMap<>();
        recGetHaffmansCodeForSymbols(haffmansTree.root,"");
        System.out.println("Таблица кодов хаффмана имеет следующий вид :"); //for debugging
        for (HashMap.Entry<Character, String> tmpEntry : haffmansCodingTable.entrySet()) {
            System.out.println(tmpEntry.getKey() + " - " + tmpEntry.getValue());
        }
        StringBuilder buffString = new StringBuilder();
        for(char s:inputMessage.toCharArray()){
            buffString.append(haffmansCodingTable.get(s));
        }
        compressedMessage = buffString.toString();
        System.out.println("Сжатое с помощью кодов хаффмана исходное сообщение : "+compressedMessage);
        return compressedMessage;
    }

    //------------------------------------------------------------------------------------
    String unCompressMessage(String compressedMessage, binaryTree haffmansTree){
        if(haffmansTree == null){
            System.out.println("Сначала укажите кодовое древо хаффмана!");
            return null;
        }
        StringBuilder buffString = new StringBuilder();
        Node current = haffmansTree.root;
        for(char s:compressedMessage.toCharArray()){
            if(s == '0'){
                current=current.leftChild;
                if( (Character)current.getValue() != Character.MAX_VALUE){ //Если мы обнаружили узел, несущий символ, а не маркер, выводим символ в строку и перемещаем ссылку на корень древа
                    buffString.append(current.getValue());
                    current = haffmansTree.root;
                }
            }else{
                current=current.rightChild;
                if( (Character)current.getValue() != Character.MAX_VALUE){ //Если мы обнаружили узел, несущий символ, а не маркер, выводим символ в строку и перемещаем ссылку на корень древа
                    buffString.append(current.getValue());
                    current = haffmansTree.root;
                }
            }
        }
        uncompressedMessage = buffString.toString();
        return uncompressedMessage;
    }

}
