import java.io.*;
import java.util.StringTokenizer;

public class beautysortefficient 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N1 = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N1; i++) 
        {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        
        int N2 = Integer.parseInt(br.readLine());
        int[] arr2 = new int[N2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N2; i++) 
        {
            arr2[i] = Integer.parseInt(st.nextToken());
        }


        int i1 = 0;
        int i2 = 0;
        StringBuilder sb = new StringBuilder();

        while (i1 < N1 && i2 < N2) 
        {
            if (arr1[i1] < arr2[i2]) 
            {
                sb.append(arr1[i1++]).append(' ');
            } 
            else 
            {
                sb.append(arr2[i2++]).append(' ');
            }
        }

        while (i1 < N1) 
        {
            sb.append(arr1[i1]).append(' ');
            i1++;
        }
        while (i2 < N2) 
        {
            sb.append(arr2[i2]).append(' ');
            i2++;
        }

        System.out.println(sb);
    }
}