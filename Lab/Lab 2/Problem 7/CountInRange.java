import java.io.*;
import java.util.*;
 
public class CountInRange
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        
        long[] arr = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) 
        {
            arr[i] = Long.parseLong(st.nextToken());
        }
        
        for (int t = 0; t < T; t++) 
        {
            st = new StringTokenizer(br.readLine());
            long LL = Long.parseLong(st.nextToken());
            long UL = Long.parseLong(st.nextToken());
            
            int lower = lowerIndex(arr, LL);
            int upper = upperIndex(arr, UL);
            
            int count = 0;
            if (lower <= upper && lower < N && upper >= 0)
                count = upper - lower + 1;
            
            System.out.println(count);
        }
    }
    public static int lowerIndex(long[] arr, long LL) 
    {
        int L = 0, R = arr.length - 1, ans = arr.length;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] >= LL) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }
    public static int upperIndex(long[] arr, long UL) 
    {
        int L = 0, R = arr.length - 1, ans = -1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] <= UL) {
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return ans;
    }
}