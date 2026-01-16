import java.io.*;
import java.util.*;
 
public class inverse 
{
 
    static long inversions = 0;
 
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int N = Integer.parseInt(br.readLine());
        StringTokenizer s1 = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int x = 0;
        while (s1.hasMoreTokens())
        {
        	arr[x] = Integer.parseInt(s1.nextToken());
        	x++;
        }
 
        mergeSort(arr, 0, N - 1);
 
        System.out.println(inversions);
 
        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
 
    public static void mergeSort(int[] arr, int left, int right) 
    {
        if (left >= right)
        {
        	return;
        }
 
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
 
    public static void merge(int[] arr, int left, int mid, int right) 
    {
        int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);
 
        int i = 0;
        int j = 0;
        int k = left;
        
        while (i < leftArr.length && j < rightArr.length) 
        {
            if (leftArr[i] <= rightArr[j])
            {
                arr[k] = leftArr[i];
                i++;
                k++;
            } 
            else 
            {
                arr[k] = rightArr[j];
                k++;
                j++;
                inversions += (leftArr.length - i);
            }
        }
 
        while (i < leftArr.length) 
        {
        	arr[k] = leftArr[i];
        	k++;
        	i++;
        }
        while (j < rightArr.length)
        {
        	arr[k] = rightArr[j];
        	k++;
        	j++;
        }
    }
    
}