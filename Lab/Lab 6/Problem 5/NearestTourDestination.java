import java.io.*;
import java.util.*;

public class NearestTourDestination  
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Read N, M, S, Q
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S_count = Integer.parseInt(st.nextToken());
        int Q_count = Integer.parseInt(st.nextToken());

        // --- 1. Build Adjacency List (Undirected) ---
        
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) 
        {
            adj[i] = new ArrayList<>();
        }

        // Read M edges
        for (int i = 0; i < M; i++) 
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            // Undirected edge
            adj[u].add(v);
            adj[v].add(u);
        }

        // --- 2. Read Sources and Destinations ---
        
        // Read S source nodes
        int[] sources = new int[S_count];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S_count; i++) 
        {
            sources[i] = Integer.parseInt(st.nextToken());
        }

        // Read Q destination nodes
        int[] destinations = new int[Q_count];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q_count; i++)
        {
            destinations[i] = Integer.parseInt(st.nextToken());
        }

        // --- 3. Multi-Source BFS Setup ---
        
        // distance[i]: shortest distance from ANY source to node i. 
        // Initialized to -1 (unvisited/unreachable).
        int[] distance = new int[N + 1];
        
        // Explicit loop for initialization
        for (int i = 0; i < N + 1; i++) 
        {
            distance[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();

        // Seed the queue with ALL source nodes (Multi-Source Initialization)
        for (int i = 0; i < S_count; i++) 
        {
            int sourceNode = sources[i];

            distance[sourceNode] = 0;
            queue.add(sourceNode);
            
        }

        // --- 4. BFS Traversal ---
        
        while (!queue.isEmpty()) 
        {
            int u = queue.remove(); // Using remove() as instructed
            int dist = distance[u];
            
            ArrayList<Integer> neighbors = adj[u];
            
            // Use the requested index-based for loop style
            for (int i = 0; i < neighbors.size(); i++) 
            {
                int v = neighbors.get(i);
                
                // If v is unvisited
                if (distance[v] == -1) 
                {
                    distance[v] = dist + 1;
                    queue.add(v);
                }
            }
        }

        // --- 5. Output Results ---
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < Q_count; i++) 
        {
            int destinationNode = destinations[i];
            
            // The result is the pre-calculated distance
            sb.append(distance[destinationNode]).append(" ");
        }

        System.out.println(sb.toString());
    }
}
