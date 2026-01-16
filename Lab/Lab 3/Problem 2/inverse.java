import java.io.*;
import java.util.*;
 
public class inverse 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
 
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int x = 0;
 
        while (st.hasMoreTokens())
        {
            arr[x] = Integer.parseInt (st.nextToken());
            x++;
        }
 
        List<Long> sortedarr = new ArrayList<Long>();
        long count = 0;
 
        for (int i = 0; i < n; i++) 
        {
            long target = (long)arr[i] * arr[i];
 
            int idx = findidx(sortedarr, target);
            count = count + (sortedarr.size() - idx);
 
            int coridx = findidx(sortedarr, arr[i]);
            sortedarr.add(coridx, (long)arr[i]);
        }
 
        System.out.println(count);
    }
 
    public static int findidx(List<Long> sortedarr, long val) 
    {
        int left = 0;
        int right = sortedarr.size();
 
        while (left < right) 
        {
            int mid = (left + right) / 2;
            if (sortedarr.get(mid) <= val) 
            {
                left = mid + 1;
            } 
            else 
            {
                right = mid;
            }
        }
 
        return left;
    }
}
