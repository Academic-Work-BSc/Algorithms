import java.io.*;

public class twosum
{
    public static void main (String [] args) throws IOException
    {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        String inp1 = br.readLine();

        String[] inp1parts = inp1.split(" ");
        int size = Integer.parseInt(inp1parts[0]);
        int target = Integer.parseInt(inp1parts[1]);

        int[] arr = new int [size];

        String inp2 = br.readLine();

        String[] inp2parts = inp2.split(" ");

        for (int i = 0; i<size; i++)
        {
            arr[i] = Integer.parseInt(inp2parts[i]);
        }

        int idx1 = -2;
        int idx2 = -2;
        int L = 0;
        int R = arr.length - 1;

        while (L<R)
        {
            if ((arr[L] + arr[R]) == target)
            {
                idx1 = L + 1;
                idx2 = R + 1;
                break;
            }
            else if ((arr[L] + arr[R]) > target)
            {
                R = R - 1;
            }
            else if (arr[L] + arr[R] < target)
            {
                L = L + 1;
            }
        }

        if (idx1 == -2 || idx2 == -2)
        {
            System.out.println (-1);
        }
        else
        {
            System.out.println (idx1 + " " + idx2);
        }
    }
}