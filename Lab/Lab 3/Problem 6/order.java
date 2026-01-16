import java.io.*;
import java.util.*;
 
public class order {
 
    static List<Integer> finalOrder;
 
   
    public static void generateOrder(int[] arr, int start, int end) 
    {
        if (start > end) {
            return;
        }
 
        int mid = start + (end - start) / 2;
 
        finalOrder.add(arr[mid]);
 
        
        generateOrder(arr, start, mid - 1);
 
    
        generateOrder(arr, mid + 1, end);
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
 
       
        finalOrder = new ArrayList<>();
        generateOrder(A, 0, N - 1);
 
       
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < finalOrder.size(); i++) {
            sb.append(finalOrder.get(i));
            if (i < finalOrder.size() - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}