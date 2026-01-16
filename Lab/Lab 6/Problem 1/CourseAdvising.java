import java.io.*;
import java.util.*;

public class CourseAdvising 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Read N and M
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // --- 1. Initialize Graph and In-Degree Array ---
        
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        int[] inDegree = new int[N + 1];

        for (int i = 0; i < N + 1; i++) 
        {
            adj[i] = new ArrayList<>();
        }

        // Read M prerequisites
        for (int i = 0; i < M; i++) 
        {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            adj[A].add(B);
            inDegree[B]++; // B has one more prerequisite
        }

        // --- 2. Topological Sort (Kahn's Algorithm) ---
        
        Queue<Integer> queue = new LinkedList<>();
        
        // Initialization: Add all nodes with in-degree 0
        for (int i = 1; i < N + 1; i++) 
        {
            if (inDegree[i] == 0) 
            {
                queue.add(i);
            }
        }

        ArrayList<Integer> sortedOrder = new ArrayList<>();
        
        while (!queue.isEmpty()) 
        {
            int U = queue.remove(); 
            sortedOrder.add(U);
            
            ArrayList<Integer> neighbors = adj[U];
            
            for (int i = 0; i < neighbors.size(); i++) 
            {
                int V = neighbors.get(i);
                
                inDegree[V]--;
                
                if (inDegree[V] == 0) 
                {
                    queue.add(V);
                }
            }
        }

        // --- 3. Cycle Check and Output (Fast Output) ---
        
        if (sortedOrder.size() < N) 
        {
            System.out.println("-1");
        } 
        else 
        {
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < sortedOrder.size(); i++) 
            {
                sb.append(sortedOrder.get(i)).append(" ");
            }
            System.out.println(sb.toString());
        }
    }
}
