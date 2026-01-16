import java.io.*;
import java.util.*;
 
public class EulerianPathChecker 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
 
        // Handle the case of an empty graph
        if (M == 0) {
            System.out.println("YES");
            return;
        }
 
        // Arrays to store edge endpoints
        int[] uNodes = new int[M];
        int[] vNodes = new int[M];
        
        // Read all M source nodes (u)
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) 
        {
            uNodes[i] = Integer.parseInt(st.nextToken());
        }
 
        // Read all M destination nodes (v)
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) 
        {
            vNodes[i] = Integer.parseInt(st.nextToken());
        }
 
        
        // Degrees for nodes 1 to N
        int[] degrees = new int[N + 1]; 
 
        for (int i = 0; i < M; i++) 
        {
            int u = uNodes[i];
            int v = vNodes[i];
            
            degrees[u]++;
            degrees[v]++;
        }
 
        int oddDegreeCount = 0;
        
        for (int i = 1; i <= N; i++) 
        {
            if (degrees[i] % 2 != 0) 
            {
                oddDegreeCount++;
            }
        }
 
        if (oddDegreeCount == 0 || oddDegreeCount == 2) 
        {
            System.out.println("YES");
        } 
        else 
        {
            System.out.println("NO");
        }
    }
}
