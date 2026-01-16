import java.util.ArrayList;
import java.util.Scanner;
public class ReverseSort
{
    public static void main (String [] args)
    {
        Scanner sc = new Scanner (System.in);
        int N = sc.nextInt();
        sc.nextLine();

        String nums = sc.nextLine();

        String[] arr1 = nums.split (" ");

        int[] arr = new int[arr1.length];

        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = Integer.parseInt (arr1[i]);
        }
        ArrayList <int[]> steps = new ArrayList<>();
        
        int[] arrodd = new int[arr.length];
        int[] arreven = new int[arr.length];
        int sizeodd = 0;
        int sizeeven = 0;
        
        for (int i = 0; i < arr.length; i++)
        {
          if (i % 2 == 0)
          {
            arreven[sizeeven] = arr[i];
            sizeeven++;
          }
          else if (i % 2 == 1)
          {
            arrodd[sizeodd] = arr[i];
            sizeodd++;
          }
        }
        
        
        for (int i = 0; i <sizeeven-1; i++)
        {
          int minindex = i;
          for (int j = i+1; j<sizeeven; j++)
          {
            if (arreven[minindex] > arreven[j])
            {
              minindex = j;
            }
          }
          while (minindex != i)
          {
            int temp = arreven[minindex - 1];
            arreven[minindex-1] = arreven[minindex];
            arreven[minindex] = temp;
            steps.add (new int[] { (((minindex-1)*2)+1), (((minindex)*2)+1)});
            minindex = minindex - 1;
          }
        }
        
        for (int i = 0; i <sizeodd-1; i++)
        {
          int minindex = i;
          for (int j = i+1; j<sizeodd; j++)
          {
            if (arrodd[minindex] > arrodd[j])
            {
              minindex = j;
            }
          }
          while (minindex != i)
          {
            int temp = arrodd[minindex - 1];
            arrodd[minindex-1] = arrodd[minindex];
            arrodd[minindex] = temp;
            steps.add (new int[] { ((((minindex-1)*2)+2)), (((minindex)*2)+2)});
            minindex = minindex - 1;
          }
        }
        
        int k = 0;
        for (int i = 0; i<arr.length; i = i + 2)
        {
          arr[i] = arreven[k];
          k = k + 1;
        }
        
        k = 0;
        for (int i = 1; i<arr.length; i = i + 2)
        {
          arr[i] = arrodd[k];
          k = k + 1;
        }

         for (int i = 0; i < arr.length - 1; i++)
         {
           for (int j = i + 1; j< arr.length; j++)
           {
             if (arr[i] > arr[j])
             {
               System.out.println ("NO");
               return;
             }
           }
         }
         System.out.println ("YES");
        
        System.out.println (steps.size());
        for (int i = 0; i < steps.size(); i++)
        {
          int [] elem = steps.get(i);
          System.out.println(elem[0] + " " + elem[1]);
        } 
    }
}