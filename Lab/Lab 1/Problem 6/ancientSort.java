import java.util.Scanner;
public class ancientSort
{
    public static void main(String[] args) 
    {
       Scanner sc = new Scanner (System.in);
       int N = sc.nextInt();
       sc.nextLine();

       String nums = sc.nextLine();

       String[] num = nums.split (" ");
       int[] arr = new int [num.length];
       for (int i = 0; i < num.length; i++)
       {
        arr[i] = Integer.parseInt (num[i]);
       }


       for (int i = 0; i < arr.length; i++)
       {
         int j = i;
         while ((j > 0) && (arr[j] < arr[j-1]) && ((arr[j] % 2) == (arr[j-1] % 2)))
         {
            int temp = arr[j];
            arr[j] = arr[j-1];
            arr[j-1] = temp;
            j--;
         }
       }
       for (int i = 0; i < arr.length; i++)
       {
        if (i != (arr.length - 1))
        {
            System.out.print (arr[i] + " ");
        }
        else
        {
            System.out.print (arr[i]);
        }
       }
    }
}