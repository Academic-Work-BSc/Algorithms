import java.io.*;
import java.util.*;


public class longsort
{
    public static void main (String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer (br.readLine());
        int size = Integer.parseInt(st.nextToken());
        long target = Long.parseLong(st.nextToken());

        st = new StringTokenizer (br.readLine());
        int[] arr = new int [size];
        int idx = 0;
        while (st.hasMoreTokens())
        {
            arr[idx] = Integer.parseInt(st.nextToken());
            idx++;
        }

        int start = 0;
        long sum = 0;
        int maxLength = 0;

        for (int i = 0; i < size; i++) 
        {
            sum = sum + arr[i];
            
            while (sum > target) 
            {
                sum = sum - arr[start];
                start++;
            }
            
            if ((i - start + 1) > maxLength)
            {
                maxLength = i - start + 1;
            }
        }
        
        System.out.println(maxLength);


    }
}