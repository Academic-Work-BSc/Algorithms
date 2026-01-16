import java.util.Scanner;
public class isSorted
{
    public static void main (String [] args)
    {
        Scanner sc = new Scanner (System.in);
        int T = sc.nextInt();
        
        for (int i = 0; i < T; i++)
        {
            int N = sc.nextInt();
            sc.nextLine();

            String nums = sc.nextLine();
            Boolean b1 = true;

            String[] arr1 = nums.split(" ");

            for (int j = 0; j < arr1.length - 1; j++)
            {
                int current = Integer.parseInt (arr1[j]);
                int next = Integer.parseInt (arr1[j+1]);
                if (next < current)
                {
                    b1 = false;
                    break;
                }
            }
            if (b1 == false)
            {
                System.out.println ("NO");
            }
            else
            {
                System.out.println("YES");
            }
        }
    }
}