/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vizhenergui;
import java.util.Scanner;
/**
 *
 * @author Muduck
 */
public class GenerateMatrixVizhen {
    public char Alphabet[]={
        'А','Б','В','Г','Д','Е','Ё','Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х','Ц','Ч','Ш','Щ','Ь','Ы','Ъ','Э','Ю','Я',
        'а','б','в','г','д','е','ё','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ь','ы','ъ','э','ю','я',',','.',
        '!','?',':',' ','(',')'
    }; 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int a,b;
        System.out.print("Укажите индексы для матрицы вижинера: ");
        a=Integer.parseInt(in.nextLine());
        System.out.print(" ");
        b=Integer.parseInt(in.nextLine());
        char[][] Matrix_of_Vizhener=new char [a][b];
        System.out.println("");
        System.out.println("Укажите порядок алфавитных букв для чередовки(без повторов): ");
        String new_order_alphabet=in.nextLine();
        System.out.println("Матрица Вижена имеет вид:");
        int order_ptr=0;
        for(int i=0;i<a;i++){
            order_ptr=i;
            System.out.print("{");
            for(int j=0;j<b;j++){
                if(order_ptr==a){ order_ptr=0; }
                Matrix_of_Vizhener[i][j]=new_order_alphabet.charAt(order_ptr++);
                System.out.print("'"+Matrix_of_Vizhener[i][j]+"',");
            }
            System.out.println("},");
        }
        // TODO code application logic here
    }
    
}
