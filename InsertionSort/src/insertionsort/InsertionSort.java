/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insertionsort;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author Muduck
 */
public class InsertionSort {
    public static void SelectionSort(int arr[]){
        for(int a=0;a<arr.length;a++){
            int temp;
            int key=a;
            for(int i=a+1;i<arr.length;i++){
                if(arr[i]<arr[key]){
                    key=i;
                }
            }
            temp=arr[a];
            arr[a]=arr[key];
            arr[key]=temp;
        }
        
    }
    public static void Sort(int arr[],int size){
        int key=0;
        int i=0;
        for(int j=1;j<size;j++){
            key=arr[j];
            i=j-1;
            while(i>=0&&arr[i]>key){
                arr[i+1]=arr[i];
                i--;
            }
            arr[i+1]=key;
        }
    }
    public static void DownSort(int arr[],int size){
        int key=0;
        int i=0;
        for(int j=1;j<size;j++){
            key=arr[j];
            i=j-1;
            while(i>=0&&arr[i]<key){
                arr[i+1]=arr[i];
                i--;
            }
            arr[i+1]=key;
        }
    }
    public static int SearchTask(int arr[],int what){
        int index=0;
        for(int i:arr){
            if(i==what){
                return index;
            }else{
                index++;
            }
        }
        return 0;
    }
    public static void bitsumm(int A[],int B[]){
        if(A.length!=B.length){ System.out.println("Длины входных битовых чисел не равны"); return; }
        int[] C=new int[A.length+1];
        for(int i=0;i<C.length;i++){
            C[i]=0;
        }
        for(int i=0;i<A.length;i++){
            switch(A[i]+B[i]+C[i]){
                /*case 0:
                    C[i]=0;
                    break;*/
                case 1:
                    C[i]=1;
                    break;
                case 2:
                    C[i]=0;
                    C[i+1]=1;
                    break;
                case 3:
                    C[i]=1;
                    C[i+1]=1;
                    break;
            
            }
            
        }
        System.out.println("   Результат: ");
        for(int i=C.length-1;i>=0;i--){             
            System.out.print(C[i]);
            
        } 
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Double Arr[][]=new Double [2][3];
        int size=0;
        Scanner In=new Scanner(System.in);
        System.out.println("Введите размер массива для сортировки.");
        //try//ДОДЕЛАТЬ ПОТОМ ОБРАБОТКУ ИСКЛЮЧЕНИЙ ЕСЛИ ВВЕЛ НЕ ИНТ ЦИФРЫ
            size=In.nextInt();
        int Arr[]=new int[size];
        Random rnd=new Random();
        System.out.print("Массив вида : ");
        for(int i=0;i<size;i++){
            Arr[i]=rnd.nextInt(40);
            System.out.print(Arr[i]+" ");
        }
        System.out.println("Элемент 20 находится на позиции "+SearchTask(Arr,20));
        Sort(Arr,size);
        System.out.print("Отсортированный : ");
        for(int i=0;i<size;i++){
            System.out.print(Arr[i]+" ");
        }
        DownSort(Arr,size);
        System.out.print("Отсортированный : ");
        for(int i=0;i<size;i++){
            System.out.print(Arr[i]+" ");
        } 
        SelectionSort(Arr);
        System.out.print("Отсортированный : ");
        for(int i=0;i<size;i++){
            System.out.print(Arr[i]+" ");
        } 
        System.out.println("Задача по сложению битовых чисел");
        int[] A=new int[10];
        int[] B=new int[10];
        System.out.println("Число А: ");
        for(int i=A.length-1;i>=0;i--){
            A[i]=rnd.nextInt(2);
            System.out.print(A[i]);
        }
        
        System.out.println("   Число Б: ");
        for(int i=B.length-1;i>=0;i--){
            B[i]=rnd.nextInt(2);
            System.out.print(B[i]);
            
        }
        bitsumm(A,B);
    }
    
}
