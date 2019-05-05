import java.io.IOException;

public class runMe {
    public static void main (String...args) throws IOException {
        //////////////////////////////////////////// BINARY TREE TESTING///////////////////////////////////////////
        binaryTree<Character> tree=new binaryTree<Character>();
        tree.insert(0,'c');
        tree.insert(-2,'a');
        tree.insert(-1,'b');
        tree.insert(1,'d');
        tree.showAllSimmetric(tree.root);
        System.out.println();
        tree.showAllBackOrder(tree.root);
        System.out.println();
        tree.showAllRightOrder(tree.root);
        tree.delete(-2);
        tree.insert(32,'j');
        tree.insert(32,'d');
        tree.showAllSimmetric(tree.root);
        tree.delete(32);
        tree.insert(-23,'A');
        tree.insert(-20,'B');
        tree.insert(5,'t');
        tree.insert(-6,'x');
        tree.insert(90,'y');
        tree.insert(-50,'z');
        tree.insert(16,'i');
        tree.insert(13,'o');
        tree.insert(-89,'p');
        tree.insert(7,'m');
        tree.showAllSimmetric(tree.root);
        tree.delete(21);
        tree.delete(888);
        tree.delete(0);
        tree.delete(7);
        tree.delete(-20);
        tree.showAllSimmetric(tree.root);
        System.out.println("\n\n\n");

        //////////////////////////////////////////// PRIORITY QUEUE TESTING///////////////////////////////////////////
        priorityQueue<Character> queue = new priorityQueue<>();
        queue.insert('C',2);
        queue.insert('B',1);
        queue.insert('A',0);
        queue.insert('D',3);
        queue.showAllElementsInPriorityOrder();
        queue.pop();
        queue.insert('D',4);
        queue.insert('z',7);
        queue.insert('x',10);
        queue.insert('t',5);
        queue.showAllElementsInPriorityOrder();
        System.out.println("\n\n\n");

        //////////////////////////////////////////// HAFFMANS COMMPRESSING TESTING///////////////////////////////////////////
        haffmanCompressor haffmanCompressor=new haffmanCompressor();
        String message = haffmanCompressor.getMessage();
        System.out.println("Message = "+message);
        binaryTree haffmanTree = haffmanCompressor.getHaffmansTree(haffmanCompressor.queueOfCharTrees(haffmanCompressor.getChastotMap(message)));
        haffmanTree.showAllSimmetric(haffmanTree.root);  // Для SUSIE SAYS IT IS EASY Древо вышло похожее (нет узла для \n, но этот символ и не писал)
        String compressedMessage = haffmanCompressor.compressMessage(haffmanTree);
        System.out.println("compressedMessage: "+compressedMessage);
        String uncompressedMessage = haffmanCompressor.unCompressMessage(compressedMessage,haffmanTree);
        System.out.println("uncompressedMessage: "+uncompressedMessage);
    }
}
