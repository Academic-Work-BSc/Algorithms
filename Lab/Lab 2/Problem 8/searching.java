import java.io.*;
import java.util.*;
 
public class searching
{
    public static void main (String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
 
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t<T; t++)
        {
            StringTokenizer st = new StringTokenizer (br.readLine());
            long k = Long.parseLong(st.nextToken());
            long x = Long.parseLong(st.nextToken());
 
            System.out.println (k + ((k-1)/(x-1)));
        }
    }
}