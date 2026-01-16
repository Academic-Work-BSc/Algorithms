import java.io.*;
import java.util.*;

public class BFS_Traversal 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // Adjacency List: size N+1 for 1-based indexing
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        
        // Initialize the ArrayList for each node
        for (int i = 1; i <= N; i++) 
        {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) 
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            adj[u].add(v);
            adj[v].add(u);
        }

        boolean[] visited = new boolean[N + 1];

        LinkedList<Integer> queue = new LinkedList<>(); 
        ArrayList<Integer> traversalOrder = new ArrayList<>();
        
        int startNode = 1;
        
        visited[startNode] = true; 
        queue.add(startNode);      

        // BFS loop
        while (!queue.isEmpty()) 
        {
            int u = queue.removeFirst(); 
            traversalOrder.add(u);
            ArrayList<Integer> neighbors = adj[u];

            for (int i = 0; i < neighbors.size(); i++) 
            {
                int v = neighbors.get(i); 
                if (!visited[v]) 
                {
                    visited[v] = true; 
                    queue.add(v);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < traversalOrder.size(); i++) 
        {
            sb.append(traversalOrder.get(i));
            
            if (i < traversalOrder.size() - 1) 
            {
                sb.append(" ");
            }
        }

        System.out.println(sb.toString());
    }
}