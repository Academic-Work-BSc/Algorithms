import java.io.*;
import java.util.*;

public class IndegreeOutdegreeDifference 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] uNodes = new int[M];
        int[] vNodes = new int[M];
        
        // Read all M source nodes (u)
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            uNodes[i] = Integer.parseInt(st.nextToken());
        }

        // Read all M destination nodes (v)
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) 
        {
            vNodes[i] = Integer.parseInt(st.nextToken());
        }

        // --- 2. Calculate the Difference: Indegree - Outdegree ---
        
        // diffArray[i] will store Indegree[i] - Outdegree[i]. 
        int[] diffArray = new int[N + 1]; 

        for (int i = 0; i < M; i++) {
            int u = uNodes[i]; // Source node (outgoing edge)
            int v = vNodes[i]; // Destination node (incoming edge)
            
            // For node v: Indegree increases by 1.
            diffArray[v]++;

            // For node u: Outdegree increases by 1. (Difference decreases by 1).
            diffArray[u]--;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) 
        {
            
            sb.append(diffArray[i]);
            
            if (i < N) 
            {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}
