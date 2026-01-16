import java.util.*;
import java.io.*;
 
public class LongestSubarrayKDistinct 
{
    public static void main(String[] args) throws IOException
     {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer (br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] A = new int[N];
       st = new StringTokenizer (br.readLine());
       
       int idx =0;
        while (idx<N)
        {
        	A[idx] = Integer.parseInt(st.nextToken());
        	idx++;
        }
 
        Map<Integer, Integer> countMap = new HashMap<>();
        int left = 0;
        int max = 0;
        int right = 0;
        
        while (right<N)
         {
            countMap.put(A[right], countMap.getOrDefault(A[right], 0) + 1);
 
            while (countMap.size() > K) 
            {
                int leftVal = A[left];
                countMap.put(leftVal, countMap.get(leftVal) - 1);
                if (countMap.get(leftVal) == 0)
                 {
                    countMap.remove(leftVal);
                }
                left++;
            }
 
            if (right-left+1 > max)
            {
            	max = right-left+1;
            }
            right++;
        }
 
        System.out.println(max);
    }
}