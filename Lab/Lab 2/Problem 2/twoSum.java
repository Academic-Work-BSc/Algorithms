import java.io.*;

public class twoSum
{
    public static void main (String [] args) throws IOException
    {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));

        String L1 = br.readLine();
        String [] L1p = L1.split(" ");

        int size1 = Integer.parseInt(L1p[0]);
        int size2 = Integer.parseInt(L1p[1]);
        int target = Integer.parseInt(L1p[2]);

        String A1 = br.readLine();
        String[] A1p = A1.split(" ");
        String A2 = br.readLine();
        String[] A2p = A2.split (" ");

        int[] arr1 = new int[size1];
        int[] arr2 = new int[size2];
        
        for (int i = 0; i<size1; i++)
        {
            arr1[i] = Integer.parseInt(A1p[i]);
        }
        for (int i = 0; i<size2; i++)
        {
            arr2[i] = Integer.parseInt(A2p[i]);
        }

        int L = 0;
        int R = arr2.length - 1;
        int difference = Integer.MAX_VALUE;
        int val1 = -1;
        int val2 = -1;

        while ((L<arr1.length) && (R>=0))
        {
            int tempdiff = arr1[L] + arr2[R] - target;
            if (tempdiff == 0)
            {
                val1 = L + 1;
                val2 = R + 1;
                break;
            }
            if (tempdiff<0)
            {
                tempdiff = -tempdiff;
            }

            if (tempdiff < difference)
            {
                difference = tempdiff;
                val1 = L+1;
                val2 = R+1;
            }
            if (arr1[L] + arr2[R] - target > 0)
            {
                R = R - 1;
            }
            else if (arr1[L] + arr2[R] - target < 0)
            {
                L = L + 1;
            }
        }
        System.out.println (val1 + " " + val2);
    }
}